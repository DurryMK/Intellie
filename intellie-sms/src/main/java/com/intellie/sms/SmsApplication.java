package com.intellie.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
