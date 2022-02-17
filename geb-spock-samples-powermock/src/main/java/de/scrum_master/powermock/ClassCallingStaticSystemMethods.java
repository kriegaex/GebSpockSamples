package de.scrum_master.powermock;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ClassCallingStaticSystemMethods {
  public String genUUID() {
    return UUID.randomUUID().toString();
  }

  public Date genDate(String date) {
    return Date.valueOf(date);
  }

  public void shuffleCollection(List<?> list) {
    Collections.shuffle(list);
  }

  public Timestamp genTimestamp(String timestamp) {
    return Timestamp.valueOf(timestamp);
  }
}
