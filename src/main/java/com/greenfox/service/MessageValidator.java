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
    List<String> missingParameters = findMissingParams(receive);
    return buildResponse(missingParameters);
  }

  private List<String> findMissingParams(Receive receive) {
    List<String> missing = new ArrayList<>();

    if (receive.getMessage().getText() == null) {
      missing.add("message.text");
    } else if (receive.getMessage().getText().isEmpty()) {
      missing.add("message.text");
    }

    if (receive.getMessage().getUsername() == null) {
      missing.add("message.username");
    } else if (receive.getMessage().getUsername().isEmpty()) {
      missing.add("message.username");
    }

    if (receive.getClient().getId() == null) {
      missing.add("client.id");
    } else if (receive.getClient().getId().isEmpty()) {
      missing.add("client.id");
    }

    if (receive.getMessage().getId() == 0) {
      missing.add("message.id");
    }
    if (receive.getMessage().getTimestamp().equals(null)) {
      missing.add("message.timestamp");
    }

    return missing;
  }

  private Response buildResponse(List<String> missingParameters) {
    if (missingParameters.size() == 0) {
      return new Response("ok");
    } else {
      String errorMessage = "Missing field(s): ";
      for (String error : missingParameters) {
        errorMessage += error + " ";
      }
      return new Response("error", errorMessage);
    }
  }
}
