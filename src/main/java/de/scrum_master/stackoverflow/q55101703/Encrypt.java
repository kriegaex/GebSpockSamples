package de.scrum_master.stackoverflow.q55101703;

import java.sql.Date;
import java.util.UUID;

public class Encrypt {
  public String genUUID() {
    return UUID.randomUUID().toString();
  }
  public Date genDate(String date) {
    return Date.valueOf(date);
  }
}
