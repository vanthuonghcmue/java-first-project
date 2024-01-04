package com.example.demo.websocket;

import com.example.demo.redis.Publisher;
import com.example.demo.redis.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    final
    WebSocketSessionManager webSocketSessionManager;

    final
    Publisher redisPublisher;

    final
    Subscriber redisSubscriber;

    public WebSocketConfig(WebSocketSessionManager webSocketSessionManager, Publisher redisPublisher, Subscriber redisSubscriber) {
        this.webSocketSessionManager = webSocketSessionManager;
        this.redisPublisher = redisPublisher;
        this.redisSubscriber = redisSubscriber;
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketTextHandler(this.webSocketSessionManager, this.redisPublisher, this.redisSubscriber), "/rooms/*").
                addInterceptors(getParametersInterceptors()).
                setAllowedOriginPatterns("*");
    }

    @Bean
    public HandshakeInterceptor getParametersInterceptors() {
        return new HandshakeInterceptor() {
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                           WebSocketHandler wsHandler, Map<String, Object> attributes) {

                String path = request.getURI().getPath();
                MultiValueMap<String, String> queryParams = WebSocketHelper.getParameterFormRequest(request);
                String roomId = WebSocketHelper.getRoomIdFromUrl(path);
                attributes.put(WebSocketHelper.roomIdKey, roomId);
                attributes.put(WebSocketHelper.usernameKey, queryParams.getFirst("username"));
                return true;
            }

            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Exception exception) {
                // Nothing to do after handshake
            }
        };
    }
}
