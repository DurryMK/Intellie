package com.intellie.gateway.core.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


/**
 * @author durry
 * @version 1.0
 * @date 2021/3/1 15:04
 * @describe:全局过滤器 在请求中添加身份识别的token
 */
@Component
public class IntellieGlobalFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(IntellieGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //打印访问日志
        ServerHttpRequest request = printLog(exchange);
//        //原URI
//        URI uri = request.getURI();
//        logger.debug("oldURL=>{}",uri);
//        //添加token
//        StringBuilder queryBuilder = new StringBuilder(uri.toString());
//        queryBuilder.append("&")
//                .append(CacheTag.GATEWAY_TOKEN)
//                .append("=")
//                .append(CacheTag.GATEWAY_TOKEN);
//        logger.debug("newURL=>{}",queryBuilder);
//        //构造新的URI
//        URI newUri = UriComponentsBuilder.fromUri(uri)
//                .replaceQuery(queryBuilder.toString())
//                .build(true)
//                .toUri();
//        //替换原URI
//        ServerHttpRequest newRequest = request.mutate().uri(newUri).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1000;
    }

    /**
     * 输出日志
     */

    private ServerHttpRequest printLog(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String originHost = uri.getHost();
        String path = uri.getPath();
        logger.debug("访问来源==>{},目标路径==>{}", originHost, path);
        return request;
    }
}
