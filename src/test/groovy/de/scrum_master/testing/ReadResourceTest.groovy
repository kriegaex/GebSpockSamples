package de.scrum_master.testing

import de.scrum_master.app.Application
import spock.lang.Specification

class ReadResourceTest extends Specification {
  def "read production resource"() {
    given:
    def resourceStream = Application.classLoader.getResourceAsStream("resource.txt")

    expect:
    resourceStream.text.contains("resource")
  }
}
