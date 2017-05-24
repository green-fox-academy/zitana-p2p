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
  MessageValidator messageValidator;

  @Autowired
  MessageRepository messageRepository;

  @RequestMapping("/api/message/receive")
  @CrossOrigin("*")
  public Response receive(HttpServletRequest request, @RequestBody Receive receive) {
    System.out.println(new Log(request.getMethod(), request.getRequestURI(), "received message=" + receive));
    if (receive.getClient().getId() != "zitana") {
      RestTemplate restTemplate = new RestTemplate();
      restTemplate
          .postForObject(System.getenv("CHAT_APP_PEER_ADDRESS") + "/api/message/receive", receive,
              Response.class);
      messageRepository.save(receive.getMessage());
    }
    return messageValidator.validate(receive);
  }
}
