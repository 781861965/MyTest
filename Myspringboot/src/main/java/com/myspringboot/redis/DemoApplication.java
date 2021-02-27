package com.myspringboot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);
        RedisTest redisTest = configurableApplicationContext.getBean(RedisTest.class);
        redisTest.testRedis();
    }

}
