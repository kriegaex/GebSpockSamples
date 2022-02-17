package de.scrum_master.stackoverflow.q61135628

import org.springframework.http.HttpMethod
import org.springframework.web.client.RestOperations
import spock.lang.Specification

class MyTest extends Specification {
  RestOperations restOperations = Mock()
  MySubject subject = new MySubject(restOperations)

  def "test"() {
    when:
    subject.doStuff()

    then:
    1 * restOperations.exchange("https://test.com", HttpMethod.POST, _, String, _)
    // Or if you want to be more specific:
//  1 * restOperations.exchange("https://test.com", HttpMethod.POST, _, String, [])
  }
}
