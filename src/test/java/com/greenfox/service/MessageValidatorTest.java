package com.greenfox.service;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.Receive;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageValidatorTest {

  @Test
  public void Two_MissingParams_Null_And_Not_Defined() throws Exception {
    MessageValidator messageValidator = new MessageValidator();
    Message message = new Message();
    message.setUsername(null);
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    assertEquals(2, messageValidator.findMissingParams(receivedMessage).size());
  }

  @Test
  public void missingParams_Empty() throws Exception {
    MessageValidator messageValidator = new MessageValidator();
    Message message = new Message("How you doin'?", "EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    assertEquals(0, messageValidator.findMissingParams(receivedMessage).size());
  }

  @Test
  public void missingParams_Client_Id() throws Exception {
    MessageValidator messageValidator = new MessageValidator();
    Message message = new Message("How you doin'?", "EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    Client client = new Client();
    Receive receivedMessage = new Receive(message,client);
    assertEquals("client.id", messageValidator.findMissingParams(receivedMessage).get(0));
  }

  @Test
  public void buildResponse_OK() throws Exception {
    MessageValidator messageValidator = new MessageValidator();
    ObjectMapper mapper = new ObjectMapper();
    List<String> missingParams = new ArrayList<>();
    assertTrue(mapper.writeValueAsString(messageValidator.buildResponse(missingParams)).contains("ok"));
    System.out.println(mapper.writeValueAsString(messageValidator.buildResponse(missingParams)));
  }

  @Test
  public void buildResponse_ERROR() throws Exception {
    MessageValidator messageValidator = new MessageValidator();
    ObjectMapper mapper = new ObjectMapper();
    List<String> missingParams = Arrays.asList("client.id");
    assertTrue(mapper.writeValueAsString(messageValidator.buildResponse(missingParams)).contains("error"));
    System.out.println(mapper.writeValueAsString(messageValidator.buildResponse(missingParams)));

  }

}