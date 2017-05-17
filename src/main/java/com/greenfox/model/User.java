package com.greenfox.model;


import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
  private String username;

}
