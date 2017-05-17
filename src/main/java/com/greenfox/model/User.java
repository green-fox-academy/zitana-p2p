package com.greenfox.model;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
  private String username;

  public User(String username) {
    this.username = username;
  }
}
