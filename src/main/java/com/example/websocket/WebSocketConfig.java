package com.example.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //client가 받는 부분 --> 메모리 베이스 브로커, controller에서 돌려주는 greeting 메세지를 topic이라는 목적지 주소를 달고 클라이언트에게 돌려줌
       config.enableSimpleBroker("/topic");
       //controller 의 messagemapping과 관련이 있는 부분 controller로 보내는 부분
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //여긴 사실 websocket이 안되면 이용할 다른 예비군 코드였나봄. 현재 사용가능한 websocket, xhr-streaming 등 있는거 찾아서 연결
        //근데 자바스크립트에서 여기다 연결을 하는 것 을 보면 역시 endpoint가 websocket의 시작점이었나봄, 그래서 시작점인 부분에 연결을 한 거겠지
        //sockjs server가 연결을 기다리고 있는 부분이 /gs-guide-websocket임
        registry.addEndpoint("/gs-guide-websocket").withSockJS();

    }
}
