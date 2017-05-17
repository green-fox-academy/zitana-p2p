package com.greenfox.controller;

import com.greenfox.model.Log;
import com.greenfox.model.Message;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;


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

  @RequestMapping("/create")
  public String create(@RequestParam("username") String username) {
    userRepository.save(new User(username));
    return "redirect:/";
  }

}
