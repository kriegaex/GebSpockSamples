package de.scrum_master.stackoverflow.q71650622

import spock.lang.Specification
import spock.lang.Subject

class SonarqubeMaintenanceMainTest extends Specification {
  @Subject
  UnderTest subject

  def setup() {
    subject = new UnderTest()
    GroovySpy(File, constructorArgs: ["x"], global: true) {
      exists() >> {
        delegate.mockObject.instance.name == "." ? false : callRealMethod()
      }
    }
  }

  def "Test execute with settings"() {
    given:
    File file = new File("src/test/resources/test.txt")

    expect:
    !subject.isFound()
    file.exists()
    file.text.startsWith("Lorem ipsum")
  }
}

class UnderTest {
  boolean isFound() {
    // Current directory should always exist -> true
    new File(".").exists()
  }
}
