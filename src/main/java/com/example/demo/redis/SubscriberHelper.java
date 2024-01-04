package com.example.demo.redis;

import com.example.demo.websocket.WebSocketSessionManager;
import io.lettuce.core.pubsub.RedisPubSubListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

public class SubscriberHelper implements RedisPubSubListener<String, String> {
    private final WebSocketSessionManager webSocketSessionManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberHelper.class);

    public SubscriberHelper(WebSocketSessionManager webSocketSessionManager){
        this.webSocketSessionManager = webSocketSessionManager;
    }
    @Override
    public void message(String channel, String message) {
        LOGGER.info("got the message on redis {} and {}", channel, message);
        var webSocketSessions = this.webSocketSessionManager.getWebSocketSessions(channel);

        for (Map.Entry<String,WebSocketSession> entry : webSocketSessions.entrySet()) {
            WebSocketSession ws = entry.getValue();
            try {
                ws.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void message(String s, String k1, String s2) {

    }

    @Override
    public void subscribed(String s, long l) {

    }

    @Override
    public void psubscribed(String s, long l) {

    }

    @Override
    public void unsubscribed(String s, long l) {

    }

    @Override
    public void punsubscribed(String s, long l) {

    }
}
