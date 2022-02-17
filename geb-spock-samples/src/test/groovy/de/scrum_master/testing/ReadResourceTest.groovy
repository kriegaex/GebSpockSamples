package de.scrum_master.testing

import de.scrum_master.app.Application

import spock.lang.IgnoreIf
import spock.lang.Specification

@IgnoreIf({ properties.IGNORE_REDIS == 'true' })
class ReadResourceTest extends Specification {
  def "read production resource"() {
    given:
    def resourceStream = Application.classLoader.getResourceAsStream("resource.txt")
    println properties.IGNORE_REDIS

    expect:
    resourceStream.text.contains("resource")
  }
}
