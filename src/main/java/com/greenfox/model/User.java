package com.greenfox.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
  private String username;

  public User(String username) {
    this.username = username;
  }
}
