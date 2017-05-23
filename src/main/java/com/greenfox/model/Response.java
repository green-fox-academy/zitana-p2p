package com.greenfox.model;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize(include= NON_NULL)
public class Response {
  private String status;
  @JsonSerialize(include= NON_NULL)
  private String message;

  public Response(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public Response(String status) {
    this.status = status;
  }

  public Response() {
  }
}
