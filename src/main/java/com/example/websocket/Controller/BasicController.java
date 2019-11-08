package com.example.websocket.Controller;

import com.example.websocket.DTO.ChatRoom;
import com.example.websocket.Repository.ChatRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@org.springframework.stereotype.Controller
public class BasicController {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    Logger log = LoggerFactory.getLogger(BasicController.class);

    @GetMapping("/")
    public ModelAndView first() {
        ModelAndView view = new ModelAndView();
        view.setViewName("first");
        log.info("mapping by / successfully");

        return view;
    }


    @GetMapping("chatroom")
    public ModelAndView chatroom(@RequestParam(value = "name")String name,
                                 @RequestParam(value = "roomName")String roomName,
                                 @RequestParam(value = "roomId")String roomId) {

        ModelAndView view = new ModelAndView();
        view.setViewName("newIndex");
        view.addObject("userName", name);
        view.addObject("roomName", roomName);
        view.addObject("roomId", roomId);
        log.info("mapping by chatroom successfully");
        log.info(name+ "  " + roomId +"   " + roomName);

        return view;
    }

    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "name")String name) {

        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("userName", name);
        log.info("mapping to chat room list successfully");

        List<ChatRoom> chatList = chatRoomRepository.findAllRoom();
        log.info("채팅방 길이" + Integer.toString(chatList.size()));

        view.addObject("chatList", chatList);
        view.addObject("length", chatList.size());
        view.addObject("userName", name);
        return view;
    }

    @GetMapping("prepareroom")
    public ModelAndView prepareRoom(@RequestParam(value = "name")String name) {

        ModelAndView view = new ModelAndView();
        view.setViewName("popupRoom");

        log.info("prepareroom controller called successfully");

        view.addObject("userName", name);

        return view;

    }

    @ResponseBody
    @PostMapping("makeroom")
    public ChatRoom makeRoom(@RequestBody ChatRoom chatRoom) {
       String roomName = chatRoom.getRoomName();
       String leaderName = chatRoom.getLeaderName();

       ChatRoom newRoom = chatRoomRepository.createChatRoom(roomName, leaderName);
       log.info("made new chat room successfully");
       log.info(roomName +"   " + leaderName);

       return newRoom;
    }

    @ResponseBody
    @PostMapping("findroom")
    public ChatRoom findRoom(@RequestBody ChatRoom chatRoom) {
        String roomName = chatRoom.getRoomName();
        ChatRoom result = chatRoomRepository.findRoomByRoomName(roomName);
        log.info(roomName);
        log.info("findroom controller called successfully");
        log.info(result.getRoomId() + "   " + result.getRoomName() + "  " + result.getLeaderName());

        return result;
    }
}
