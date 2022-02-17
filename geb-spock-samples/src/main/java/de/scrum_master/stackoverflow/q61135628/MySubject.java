package de.scrum_master.stackoverflow.q61135628;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class MySubject {
  private final RestOperations rest;

  public MySubject(RestOperations rest) {
    this.rest = rest;
  }

  public void doStuff() {
    HttpEntity<String> httpEntity = new HttpEntity<>("whatever");
    rest.exchange("https://test.com", HttpMethod.POST, httpEntity, String.class);
  }
}
