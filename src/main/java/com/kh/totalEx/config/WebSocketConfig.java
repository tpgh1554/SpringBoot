package com.kh.totalEx.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // Spring의 설정 클래스로 Bean정의와 설정을 포함
@RequiredArgsConstructor
@EnableWebSocket // 웹 소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer { // 웹 소켓 핸들러 구성
    private final WebSocketHandler webSocketHandler; // 필드를 초기화하는 생성자가 자동 생성

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) { // 인터페이스 구현
        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*");
    }
}