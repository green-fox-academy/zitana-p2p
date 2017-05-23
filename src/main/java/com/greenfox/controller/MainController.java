package com.greenfox.controller;

import com.greenfox.model.Client;
import com.greenfox.model.Log;
import com.greenfox.model.Message;
import com.greenfox.model.Receive;
import com.greenfox.model.Response;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  MessageRepository messageRepository;


  @GetMapping("/")
  public String main(HttpServletRequest request,Model model) {
    model.addAttribute("user", userRepository.findOne(1l));
    model.addAttribute("messages", messageRepository.findAllByOrderByTimestampAsc());
    if (userRepository.count() == 0) {
      return "redirect:/enter";
    } else {
      System.out.println(new Log(request.getMethod(), request.getRequestURI(), ""));
      return "main";
    }
  }

  @GetMapping("/enter")
  public String enter(HttpServletRequest request, Model model) {
    if (userRepository.count() == 0) {
      System.out.println(new Log(request.getMethod(), request.getRequestURI(), ""));
      messageRepository.save(new Message(" Hi there! Submit your message using the send button!", "App"));
      return "enter";
    } else {
      System.out.println(new Log(request.getMethod(), request.getRequestURI(), ""));
      return "redirect:/";
    }
  }

  @RequestMapping("/enter/add")
  public String create(HttpServletRequest request,@RequestParam(value = "username") String username, Model model) {
    System.out.println(new Log(request.getMethod(), request.getRequestURI(), "username=" + username));
    User user = new User(username);
    userRepository.save(user);
    return "redirect:/";
  }

  @RequestMapping("/updateUsername")
  public String update(HttpServletRequest request, @RequestParam(value = "username") String username, Model model) {
    System.out.println(new Log(request.getMethod(), request.getRequestURI(), "new username=" + username));
    userRepository.findOne(1l).setUsername(username);
    userRepository.save(userRepository.findOne(1l));
    return "redirect:/";
  }

  @RequestMapping("/send")
  public String send(HttpServletRequest request, @RequestParam(value = "message") String message, Model model) {
    System.out.println(new Log(request.getMethod(), request.getRequestURI(), "new message=" + message));
    Message clientMessage = new Message(message, userRepository.findOne(1l).getUsername());
    messageRepository.save(clientMessage);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForObject(System.getenv("CHAT_APP_PEER_ADDRESS") + "/api/message/receive", new Receive(clientMessage, new Client(System.getenv("CHAT_APP_UNIQUE_ID"))), Response.class);
    return "redirect:/";
  }

}
