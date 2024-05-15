package com.zancheema.pos.service.billing.filters;

import com.zancheema.pos.service.billing.util.UserContext;
import com.zancheema.pos.service.billing.util.UserContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String correlationId = request.getHeader(UserContext.CORRELATION_ID);
        UserContextHolder.getContext()
                .setCorrelationId(correlationId);
        // this filter does not modify the request or response objects
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
