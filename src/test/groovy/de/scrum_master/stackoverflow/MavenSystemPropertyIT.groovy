package de.scrum_master.stackoverflow

import spock.lang.Specification

class MavenSystemPropertyIT extends Specification {
  def "Port is being set correctly"() {
    expect:
    println System.properties.getProperty('test.server.port')
    System.properties.getProperty('test.server.port') != null
  }
}
