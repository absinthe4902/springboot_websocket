package com.example.websocket.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@org.springframework.stereotype.Controller
public class BasicController {

    Logger log = LoggerFactory.getLogger(BasicController.class);

    @GetMapping("/")
    public ModelAndView first() {
        ModelAndView view = new ModelAndView();
        view.setViewName("first");
        log.info("mapping by / successfully");

        return view;
    }

    @GetMapping("chatroom")
    public ModelAndView chatroom(@RequestParam(value = "name")String name) {

        ModelAndView view = new ModelAndView();
        view.setViewName("newIndex");
        view.addObject("userName", name);
        log.info("mapping by chatroom successfully");
        log.info(name);

        return view;
    }
}
