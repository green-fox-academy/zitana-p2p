package com.greenfox.model;

import java.sql.Timestamp;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

  @Id
  private int id;
  private String text;
  private String username;
  private Timestamp timestamp;

  public Message() {
  }

  public Message(String text, String username) {
    this.text = text;
    this.username = username;
    this.id = generateId();
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  private int generateId() {
    Random rand = new Random();
    return rand.nextInt(9000000) + 1000000;
  }
}
