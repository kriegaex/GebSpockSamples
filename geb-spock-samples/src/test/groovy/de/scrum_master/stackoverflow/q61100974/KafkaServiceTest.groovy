package de.scrum_master.stackoverflow.q61100974

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.util.concurrent.SettableListenableFuture
import spock.lang.Specification

class KafkaServiceTest extends Specification {

  KafkaTemplate<String, String> kafkaTemplate = Mock()
  ListenableFutureCallback callback = Mock()

  // Inject mock template into spy (wrapping the real service) so we can verify interactions on it later
  KafkaService kafkaService = Spy(constructorArgs: [kafkaTemplate]) {
    // Make newly created helper method return mock callback so we can verify interactions on it later
    createCallback() >> callback
  }

  SendResult<String, String> sendResult = Stub()
  String topicName = "test.topic"
  String message = "test message"
  ListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>()

  def "sending message succeeds"() {
    given:
    future.set(sendResult)

    when:
    kafkaService.sendMessage(topicName, message)

    then:
    1 * kafkaTemplate.send(topicName, message) >> future
    1 * callback.onSuccess(_)
  }

  def "sending message fails"() {
    given:
    future.setException(new Exception("uh-oh"))

    when:
    kafkaService.sendMessage(topicName, message)

    then:
    1 * kafkaTemplate.send(topicName, message) >> future
    1 * callback.onFailure(_)
  }
}
