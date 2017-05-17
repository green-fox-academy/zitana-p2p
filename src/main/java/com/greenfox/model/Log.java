package com.greenfox.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Log {
  Date time;
  String level;
  String method;
  String path;
  String requestData;

  public Log(String method, String path, String requestData) {
    this.time = new Date();
    this.level = System.getenv("CHAT_APP_LOGLEVEL");
    this.method = method;
    this.path = path;
    this.requestData = requestData;
  }

  @Override
  public String toString() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
    return format.format(time) +
        " " + level +
        " " + method +
        " Request " + path +
        " text=" + requestData;
  }
}
