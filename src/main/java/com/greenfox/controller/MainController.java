package com.greenfox.controller;

import com.greenfox.model.Log;
import com.greenfox.model.User;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;


  @GetMapping("/")
  public String main() {
    if (userRepository.count() == 0) {
      return "redirect:/enter";
    } else {
      System.out.println(new Log("GET", "/", "main page"));
      return "main";
    }
  }

  @GetMapping("/enter")
  public String enter() {
    System.out.println(new Log("GET", "/enter", ""));
    return "enter";
  }

  @RequestMapping("/enter/add")
  public String create(@RequestParam(value = "username") String username) {
    System.out.println(new Log("POST", "/enter/add ", "username=" + username));
    userRepository.save(new User(username));
    return "redirect:/";
  }

}
