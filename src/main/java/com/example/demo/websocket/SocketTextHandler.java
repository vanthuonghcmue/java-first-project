package com.example.demo.websocket;
import com.example.demo.redis.Publisher;
import com.example.demo.redis.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
    private final WebSocketSessionManager webSocketSessionManager;

    private final Publisher redisPublisher;

    private final Subscriber redisSubscriber;

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketTextHandler.class);

    public SocketTextHandler(WebSocketSessionManager webSocketSessionManager, Publisher redisPublisher, Subscriber redisSubscriber) {
        this.webSocketSessionManager = webSocketSessionManager;
        this.redisPublisher = redisPublisher;
        this.redisSubscriber = redisSubscriber;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.webSocketSessionManager.addWebSocketSession(session);
        String roomId = WebSocketHelper.getRoomIdFromSessionAttribute(session);
        this.redisSubscriber.subscribe(roomId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        this.webSocketSessionManager.removeWebSocketSession(session);
        String roomId = WebSocketHelper.getRoomIdFromSessionAttribute(session);
        this.redisSubscriber.unsubscribe(roomId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        String roomId = WebSocketHelper.getRoomIdFromSessionAttribute(session);
        String username = WebSocketHelper.getUsernameFromSessionAttribute(session);

        if (payload.contains("->")) {
            String[] payLoadSplit = payload.split("->");
            String targetRoomId  = payLoadSplit[0].trim();
            String messageToBeSent = payLoadSplit[1].trim();
            LOGGER.info("got the payload {} and going to send to channel {}", payload, targetRoomId);
            this.redisPublisher.publish(targetRoomId, roomId + ":" + username + ":" + messageToBeSent);
        }
        else {
            this.redisPublisher.publish(roomId, username + ":" + payload);
        }
    }
}
