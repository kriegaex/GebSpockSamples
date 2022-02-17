package de.scrum_master.stackoverflow.q61100974;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaService {
  private KafkaTemplate<String, String> kafkaTemplate;

  public KafkaService(KafkaTemplate kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topicName, String message) {
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
    future.addCallback(createCallback());
  }

  protected ListenableFutureCallback<SendResult<String, String>> createCallback() {
    return new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {
        System.out.print("Success -> " + result);
      }

      @Override
      public void onFailure(Throwable ex) {
        System.out.print("Failed -> " + ex);
      }
    };
  }
}
