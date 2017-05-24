package com.greenfox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.ChatApplication;
import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.Receive;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class MainRestControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void withExample_Status_OK() throws Exception {
    Message message = new Message("How you doin'?", "EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(status().isOk());
  }

  @Test
  public void withExampleJSON_OK() throws Exception {
    Message message = new Message("How you doin'?", "EggDice");
        message.setId(7655482);
        message.setTimestamp(new Timestamp(1322018752992l));
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("ok")));
  }

  @Test
  public void with_Empty_String_As_Text() throws Exception {
    Message message = new Message();
    message.setUsername("EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    message.setText("");
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", containsString("message.text")));
  }

  @Test
  public void without_Text() throws Exception {
    Message message = new Message();
    message.setUsername("EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", containsString("message.text")));
  }

  @Test
  public void with_Null_Text() throws Exception {
    Message message = new Message();
    message.setUsername("EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    message.setText(null);
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", containsString("message.text")));
  }

  @Test
  public void with_Empty_String_As_Username() throws Exception {
    Message message = new Message();
    message.setUsername("");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    message.setText("How you doin'?");
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", containsString("message.username")));
  }

  @Test
  public void without_Username() throws Exception {
    Message message = new Message();
    message.setUsername("EggDice");
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    message.setText("How you doin'?");
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("ok")));
  }

  @Test
  public void with_Null_Username() throws Exception {
    Message message = new Message();
    message.setUsername(null);
    message.setId(7655482);
    message.setTimestamp(new Timestamp(1322018752992l));
    message.setText("How you doin'?");
    Client client = new Client(("EggDice"));
    Receive receivedMessage = new Receive(message,client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", containsString("message.username")));
  }

}