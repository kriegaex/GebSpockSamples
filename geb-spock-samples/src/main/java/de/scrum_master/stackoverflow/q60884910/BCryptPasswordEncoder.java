package de.scrum_master.stackoverflow.q60884910;

import java.util.Base64;

public class BCryptPasswordEncoder {
  public String encode(String password) {
    return Base64.getEncoder().encodeToString(password.getBytes());
  }
}
