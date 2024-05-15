package com.zancheema.pos.service.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.zancheema.pos.service.gateway.util.FilterOrderType.PRE;

@Slf4j
@Component
public class CorrelationTrackingPreFilter implements GlobalFilter, Ordered {
    public static final String CORRELATION_ID = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Tracking filter invoked...");

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        if (hasCorrelationId(headers)) {
            log.info(String.format("Tracked request with correlation id %s", headers.get(CORRELATION_ID)));
        } else {
            request = request.mutate()
                    .header(CORRELATION_ID, generateCorrelationId())
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        }

        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    private boolean hasCorrelationId(HttpHeaders headers) {
        return headers.containsKey(CORRELATION_ID);
    }

    @Override
    public int getOrder() {
        return PRE.getOrder();
    }
}
