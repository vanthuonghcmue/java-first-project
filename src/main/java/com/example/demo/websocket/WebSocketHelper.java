package com.example.demo.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;

public class WebSocketHelper {
    public static String roomIdKey = "roomId";
    public static String usernameKey = "username";

    public static String getRoomIdFromSessionAttribute(WebSocketSession webSocketSession) {
        return (String) webSocketSession.getAttributes().get(roomIdKey);
    }

    public static String getUsernameFromSessionAttribute(WebSocketSession webSocketSession) {
        return (String) webSocketSession.getAttributes().get(usernameKey);
    }

    public static String getRoomIdFromUrl(String path) {
        return path.substring(path.lastIndexOf('/') + 1);
    }

    public static MultiValueMap<String, String> getParameterFormRequest(ServerHttpRequest request) {
        return UriComponentsBuilder.fromUri(request.getURI()).build().getQueryParams();
    }
}
