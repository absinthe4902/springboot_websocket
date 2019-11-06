package com.example.websocket.Controller;

import com.example.websocket.DTO.FullGreeting;
import com.example.websocket.DTO.Greeting;
import com.example.websocket.DTO.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    Logger log = LoggerFactory.getLogger(GreetingController.class);

    //클라이언트가 app/hello로 달고 오쳥을 보내면 controller가 messagemapping으로 잡는거겠지?
    @MessageMapping("/hello")
    //client가 보낸 메세지를 /topic/greetings를 구독하는 다른 클라이언트들에게 보내준다 (broadcasting 해준다)
    @SendTo("/topic/greetings")
    public FullGreeting greeting(FullGreeting input) throws Exception {
        log.info("입력된 이름: " + input.getName() +" 입력된 내용: " + input.getContent() );

        return new FullGreeting(input.getName(), input.getContent());
    }

    @MessageMapping("/typing")
    @SendTo("/topic/waiting")
    public FullGreeting waiting(FullGreeting input) throws Exception {
        log.info("누군가 waiting controller 호출함");
        //TODO: 나중에 직접 문자열을 보내주기
        return new FullGreeting(input.getName(), input.getContent());
    }



}
