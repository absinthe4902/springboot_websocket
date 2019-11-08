package com.example.websocket.Repository;

import com.example.websocket.DTO.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap; //원래 repository 에 mybatis면 mapper 들어가고 그러는데 여기는 디비를 끼지 않아서 그냥 map사용

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    //이름별로 안 찾고 아이디별로 찾네..? 아마 시스템적으로 찾으려나봄 --> 맵에 이름을 저장을 안 해서 그럼
    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    //나중에 중복되는 이름이 있으면 완전 망하는거네 이건...
    public ChatRoom findRoomByRoomName(String roomName ) {
        return chatRoomMap.get(roomName);
    }

    public ChatRoom createChatRoom(String name, String leaderName) {
        ChatRoom chatRoom = ChatRoom.create(name, leaderName);
        chatRoomMap.put(chatRoom.getRoomName(), chatRoom); //새로 만들고 아이디랑 매핑해서 룸에 넣어줬네

        return chatRoom;
    }

}
