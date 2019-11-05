package com.example.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    //클라이언트가 app/hello로 달고 오쳥을 보내면 controller가 messagemapping으로 잡는거겠지?
    @MessageMapping("/hello")
    //client가 보낸 메세지를 /topic/greetings를 구독하는 다른 클라이언트들에게 보내준다 (broadcasting 해준다)


    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
