package com.example.demo.redis;

import com.example.demo.config.RedisConfig;
import io.lettuce.core.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    RedisClient client;

    final RedisConfig redisConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    public Publisher(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        this.client = RedisClient.create("redis://" + redisConfig.getRedisHost() + ":" + redisConfig.getRedisPort());
    }

    public void publish(String channel, String message) {
        LOGGER.info("going to publish the message to channel {} and message = {}", channel, message);
        var connection = this.client.connect();
        connection.sync().publish(channel, message);
    }

}
