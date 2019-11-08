package com.example.websocket.DTO;


public class FullGreeting {
    private String name;
    private String content;
    private String roomId;

    //뭘 쓸지 모르니까 일단 생성자 많이 만들자~
    public FullGreeting() {}

    public FullGreeting(String name) {
        this.name = name;
    }

    //얘를 쓸 확률이 제일 높겠지
    public FullGreeting(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public FullGreeting(String name, String content, String roomId) {
        this.name = name;
        this.content = content;
        this.roomId = roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
