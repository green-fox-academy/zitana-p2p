package com.greenfox.controller;

import com.greenfox.model.Log;
import com.greenfox.model.User;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;


  @GetMapping("/")
  public String main(Model model) {
    model.addAttribute("user", userRepository.findOne(1l));
    if (userRepository.count() == 0) {
      return "redirect:/enter";
    } else {
      System.out.println(new Log("GET", "/", "main page"));
      return "main";
    }
  }

  @GetMapping("/enter")
  public String enter(Model model) {
    if (userRepository.count() == 0) {
      System.out.println(new Log("GET", "/enter", ""));
      return "enter";
    } else {
      System.out.println(new Log("GET", "redirect: /", ""));
      return "redirect:/";
    }
  }

  @RequestMapping("/enter/add")
  public String create(@RequestParam(value = "username") String username, Model model) {
    System.out.println(new Log("POST", "/enter/add ", "username=" + username));
    User user = new User(username);
    userRepository.save(user);
    return "redirect:/";
  }

}
