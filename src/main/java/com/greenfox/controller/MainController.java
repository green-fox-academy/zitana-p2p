package com.greenfox.controller;

import com.greenfox.model.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping("/")
  public String main() {
    Log log = new Log("GET", "/", "main page");
    System.out.println(log);
    return "main";
  }

  @RequestMapping("/enter")
  public String enter() {
    return "enter";
  }


}
