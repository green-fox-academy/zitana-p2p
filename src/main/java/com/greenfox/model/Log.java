package com.greenfox.model;

import java.sql.Time;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Log {
  @DateTimeFormat
  Time time;
  String level;
  String method;
  String path;
  String requestData;

  public Log(Time time, String level, String method, String path, String requestData) {
    this.time = time;
    this.level = System.getenv("CHAT_APP_LOGLEVEL");
    this.method = method;
    this.path = path;
    this.requestData = requestData;
  }

  @Override
  public String toString() {
    return time +
        " " + level +
        " " + method +
        " '" + path +
        ", text='" + requestData;
  }
}
