package com.greenfox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping(value = "/")
  public String log(Model model){
    System.out.println(System.getenv("CHAT_APP_LOGLEVEL"));
    return "index";
  }
}
