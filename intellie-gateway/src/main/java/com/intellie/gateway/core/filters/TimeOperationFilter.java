package com.intellie.gateway.core.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/2 9:27
 */
public class TimeOperationFilter implements GatewayFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(TimeOperationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("enterTime", System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long enterTime = exchange.getAttribute("enterTime");
                    if (enterTime != null) {
                        logger.debug(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - enterTime) + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
