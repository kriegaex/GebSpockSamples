package de.scrum_master.stackoverflow.q58326300

import geb.spock.GebReportingSpec

class HttpBasicAuthenticationIT extends GebReportingSpec {
  def test() {
    when:
    to HttpBasicAuthenticationPage
    report "password-protected image"

    then:
    true
  }
}
