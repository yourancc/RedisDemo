package com.yourancc.redisdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;


@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 字符串key序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 字符串value序列化方式
        redisTemplate.setValueSerializer(RedisSerializer.string());
        // Hash key序列化方式
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // Hash value序列化方式
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        // 设置序列化方式, 前面只是声明, 该方法才实际注入序列化方式
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}