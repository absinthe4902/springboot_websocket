package com.example.websocket;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class WebsocketApplicationTests {

    @Test
    void contextLoads() {

        LocalDateTime currnetDateTime = LocalDateTime.now();
        System.out.println(currnetDateTime);

        String local = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(local);
    }

}
