package com.intellie.user.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/21 9:27
 */
@Configuration
public class SessionConf {
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        //初始化一个redisCacheWriter
//        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
//        //初始化序列化方式为json
//        RedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer();
//        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer);
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
//
//        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
//    }
}
