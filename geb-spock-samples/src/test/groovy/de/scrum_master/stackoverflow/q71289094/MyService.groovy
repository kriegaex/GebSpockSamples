package de.scrum_master.stackoverflow.q71289094

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import javax.annotation.Resource

@Service
class MyService {
  @Resource
  RestTemplate restTemplate

  MyService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate
  }

  def callEndPoint(String arg) {
    def response = restTemplate.postForEntity(
      "http://url",
      new HttpEntity<>(new HttpHeaders(["content-type": "application/type"])),
      String.class
    )

    ObjectMapper objectMapper = new ObjectMapper()
    Map result = objectMapper.readValue(response.body, Map.class)
    return result ["data"]["root"]["username"];
  }
}
