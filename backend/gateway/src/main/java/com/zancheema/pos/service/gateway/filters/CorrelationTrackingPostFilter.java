package com.zancheema.pos.service.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.zancheema.pos.service.gateway.util.FilterOrderType.POST;

@Slf4j
@Component
public class CorrelationTrackingPostFilter implements GlobalFilter, Ordered {
    public static final String CORRELATION_ID = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Injecting correlation into response...");

        String correlationId = getCorrelationId(exchange.getRequest().getHeaders());
        exchange.getResponse().getHeaders().add(CORRELATION_ID, correlationId);
        return chain.filter(exchange);
    }

    private String getCorrelationId(HttpHeaders headers) {
        return Objects.requireNonNull(headers.get(CORRELATION_ID)).iterator().next();
    }

    @Override
    public int getOrder() {
        return POST.getOrder();
    }
}
