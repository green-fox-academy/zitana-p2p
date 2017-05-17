package com.greenfox.controller;

import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  MessageRepository messageRepository;

  @RequestMapping("/")
  public String main() {
    return "main";
  }

  @RequestMapping(value = "/")
  public String log(Model model){
    System.out.println(System.getenv("CHAT_APP_LOGLEVEL"));
    return "main";
  }
}