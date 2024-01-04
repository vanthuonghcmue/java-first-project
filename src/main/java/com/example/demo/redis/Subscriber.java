package com.example.demo.redis;

import com.example.demo.config.RedisConfig;
import com.example.demo.websocket.WebSocketSessionManager;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    final WebSocketSessionManager webSocketSessionManager;

    private RedisPubSubCommands<String, String> sync;

    public Subscriber(WebSocketSessionManager webSocketSessionManager, RedisConfig redisConfig) {
        this.webSocketSessionManager = webSocketSessionManager;
        RedisClient client = RedisClient.create("redis://" + redisConfig.getRedisHost() + ":" + redisConfig.getRedisPort());
        StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();
        var redisListner = new SubscriberHelper(this.webSocketSessionManager);
        connection.addListener(redisListner);
        this.sync = connection.sync();


    }

    public void subscribe(String channel) {
        this.sync.subscribe(channel);
    }

    public void unsubscribe(String channel) {
        this.sync.unsubscribe(channel);
    }
}
