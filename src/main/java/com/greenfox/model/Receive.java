package com.greenfox.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Receive {
  private Message message;
  private Client client;

  public Receive() {
  }
}
