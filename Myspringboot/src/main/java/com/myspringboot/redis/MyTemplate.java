package com.myspringboot.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyTemplate {

    @Bean
    public StringRedisTemplate getMyTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return stringRedisTemplate;
    }
}
