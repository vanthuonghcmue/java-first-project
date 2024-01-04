package com.example.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component

public class WebSocketSessionManager {
    private final Map<String, Map<String, WebSocketSession>> webSocketSessionByRoomId = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketSessionManager.class);

    public void addWebSocketSession(WebSocketSession webSocketSession) {
        String roomId = WebSocketHelper.getRoomIdFromSessionAttribute(webSocketSession);
        String username = WebSocketHelper.getUsernameFromSessionAttribute(webSocketSession);
        LOGGER.info("got request to add session id {} for room id {} of user {} ", webSocketSession.getId(), roomId, username);
        this.webSocketSessionByRoomId.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>()).put(username, webSocketSession);
        LOGGER.info("added session id {} of user {} for room id {}", webSocketSession.getId(), username, roomId);
        LOGGER.info("this.webSocketSessionByRoomId {}", this.webSocketSessionByRoomId);
    }

    public void removeWebSocketSession(WebSocketSession webSocketSession) {
        String roomId = WebSocketHelper.getRoomIdFromSessionAttribute(webSocketSession);
        String username = WebSocketHelper.getUsernameFromSessionAttribute(webSocketSession);
        LOGGER.info("got request to remove session id {} of user {} for room id {}", webSocketSession.getId(), username, roomId);
        Map<String, WebSocketSession> sessionsInRoom = webSocketSessionByRoomId.get(roomId);
        sessionsInRoom.remove(username);

        LOGGER.info("removed session id {} of user {} for room id {}", webSocketSession.getId(), username, roomId);
    }

    public Map<String, WebSocketSession> getWebSocketSessions(String roomId) {
        return this.webSocketSessionByRoomId.get(roomId);
    }
}
