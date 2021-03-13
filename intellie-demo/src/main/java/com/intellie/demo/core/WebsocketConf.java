package com.intellie.demo.core;

import com.intellie.demo.websocktTest.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/1 20:56
 */
@Configuration
@EnableWebSocket
public class WebsocketConf  implements WebSocketConfigurer {
    Logger logger = LoggerFactory.getLogger(WebsocketConf.class);

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        logger.info("ServerEndpointExporter init");
        ServerEndpointExporter exporter = new ServerEndpointExporter();
        return exporter;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info("WebSocketHandlerRegistry init");
        registry.addHandler(new MyHandler(), "/websocket").setAllowedOrigins("http://localhost:7777");
    }
}
