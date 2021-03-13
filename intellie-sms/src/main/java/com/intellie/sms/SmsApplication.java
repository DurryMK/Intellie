package com.intellie.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//@EnableRedisHttpSession
@SpringBootApplication
@EnableEurekaClient
public class SmsApplication {
    /**
     * 信息转发中心
     */
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
