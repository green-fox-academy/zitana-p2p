package com.greenfox.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
  private String status;
  @JsonInclude(Include.NON_NULL)
  private String message;

  public Response(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public Response(String status) {
    this.status = status;
  }
}
