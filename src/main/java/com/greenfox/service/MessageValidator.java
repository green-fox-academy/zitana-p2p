package com.greenfox.service;

import com.greenfox.model.Receive;
import com.greenfox.model.Response;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageValidator {

  public MessageValidator() {
  }

  public Response validate(Receive receive) {
    List<String> missing = new ArrayList<>();
    if (receive.getMessage().getId() == 0) {
      missing.add("message.id");
    } else if (receive.getMessage().getTimestamp().equals(null)) {
      missing.add("message.timestamp");
    } else if (receive.getMessage().getText().isEmpty() || receive.getMessage().getText() == null) {
      missing.add("message.text");
    } else if (receive.getMessage().getUsername().isEmpty() || receive.getMessage().getUsername() == null) {
      missing.add("message.username");
    } else if (receive.getClient().getId().isEmpty() || receive.getClient().getId() == null) {
      missing.add("client.id");
    }

    if (missing.size() == 0) {
      return new Response("ok");
    } else {
      String errorMessage = "Missing field(s): ";
      for (String error : missing) {
        errorMessage += error + " ";
      }
      return new Response("error", errorMessage);
    }
  }

}
