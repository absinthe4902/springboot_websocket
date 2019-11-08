package com.example.websocket.DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ChatRoom {
    private String roomId;
    private String roomName;
    private String leaderName;
    private String time;

    public ChatRoom() {}


    //TODO: 채팅방 생성하기
    public static ChatRoom create(String roomName, String leaderName) {
        String randomId = UUID.randomUUID().toString(); //임이의 아이디 만들어주기
        String randomTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); //현재 시간 넣어주기

        return new ChatRoom(randomId, roomName, leaderName, randomTime);
    }

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }
    public ChatRoom(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }


    //생각해보니까 채팅방의 아이디를 받아서 생성하기는 힘들겠다. 식별자니까 내가 알아서 만들어주는게 좋은 쪽이라고 생각한다.
    public ChatRoom(String roomId, String roomName, String leaderName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.leaderName = leaderName;
    }

    public ChatRoom(String roomId, String roomName, String leaderName, String time) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.leaderName = leaderName;
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
 }
