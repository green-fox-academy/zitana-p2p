package com.greenfox.controller;

import com.greenfox.model.Log;
import com.greenfox.model.Receive;
import com.greenfox.model.Response;
import com.greenfox.repository.MessageRepository;
import com.greenfox.service.MessageValidator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainRestController {

  @Autowired
  private MessageValidator messageValidator;

  @Autowired
  private MessageRepository messageRepository;

  @RequestMapping("/api/message/receive")
  @CrossOrigin("*")
  public Response receive(HttpServletRequest request, @RequestBody Receive receive) {
    System.out.println(new Log(request.getMethod(), request.getRequestURI(), "received message=" + receive));
    try {
      if (!receive.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
        messageRepository.save(receive.getMessage());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
            .postForObject(System.getenv("CHAT_APP_PEER_ADDRESS") + "/api/message/receive", receive,
                Response.class);
      }
    } catch (NullPointerException e) {
      System.err.println(e.fillInStackTrace().toString());
    }
    return messageValidator.validate(receive);
  }
}
