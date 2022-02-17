package de.scrum_master.stackoverflow.q61667088

import spock.lang.Specification

class PublishSubscribeTest extends Specification {

  def "test publish - subscribe protocol with global Groovy mock"() {
    GroovyMock(Subscriber, global: true)
    def subscriber = Mock(Subscriber)
    new Subscriber() >> subscriber

    def publisher = new Publisher()
    publisher << new Subscriber() << new Subscriber()


    when:
    publisher.publish("message")

    then:
    2 * subscriber.receive("message")
  }

  static class Subscriber {
    void receive(String message) {
      println "received: $message"
    }
  }

  static class Publisher {
    List<Subscriber> subscribers = new ArrayList<>()

    Publisher leftShift(Subscriber subscriber) {
      subscribers << subscriber
      this
    }

    void publish(String message) {
      subscribers.each { it.receive(message) }
    }
  }

}
