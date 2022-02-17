package de.scrum_master.stackoverflow.q61004395

import groovy.json.JsonOutput
import spock.lang.Specification

class SomeTest extends Specification {
  Map message
  String topicArn = "aws::fake-sns-topic"
  SNSClient snsClient = Mock()
  API api = new API(snsClient: snsClient, topic: topicArn)

  def 'new message gets published'() {
    given: 'a new message'
    message = [ownerToken: null, status: "NEW"]

    when: 'the message is received'
    api.doHandleRequest(message)

    then: 'it gets published'
    1 * snsClient.publish(topicArn, JsonOutput.toJson(message))
  }

  static class API {
    SNSClient snsClient
    String topic

    def doHandleRequest(Map<String, String> message) {
      snsClient.publish(topic, JsonOutput.toJson(message))
    }
  }

  static class SNSClient {
    def publish(String topic, String json) {}
  }
}
