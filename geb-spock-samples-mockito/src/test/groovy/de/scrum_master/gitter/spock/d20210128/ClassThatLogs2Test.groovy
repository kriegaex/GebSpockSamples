package de.scrum_master.gitter.spock.d20210128

import spock.lang.Specification
import spock.lang.Unroll

import static java.nio.charset.StandardCharsets.UTF_8

class ClassThatLogs2Test extends Specification {
  static final PrintStream originalSysOut = System.out
  PrintStream mockSysOut = Mock()

  def setup() {
    System.out = mockSysOut
  }

  def cleanup() {
    System.out = originalSysOut
  }

  @Unroll
  def "Vulcan greeting for #person"() {
    when:
    new ClassThatLogs().logSomethingWithInfo(person)

    then:
    1 * mockSysOut.println({ it.contains("Live long and prosper, $person") })
//    1 * mockSysOut.write({ byte[] bytes ->
//      new String(bytes, UTF_8).contains("Live long and prosper, $person")
//    })

    where:
    person << ["Jim", "Scottie", "Bones"]
  }
}
