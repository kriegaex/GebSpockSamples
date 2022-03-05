package de.scrum_master.stackoverflow.q71289094

import groovy.json.JsonBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class MyServiceTest extends Specification {
  String url = "http://url"
  JsonBuilder json = new JsonBuilder()
    .data {
      root {
        username "admin"
      }
    }
  RestTemplate restTemplate = Mock() {
    postForEntity(url, _, String) >>
      new ResponseEntity(json.toPrettyString(), HttpStatus.OK)
  }
  MyService myService = new MyService(restTemplate)

  def "restTemplate"() {
    expect:
    this.myService.callEndPoint("dummy") == "admin"
  }
}
