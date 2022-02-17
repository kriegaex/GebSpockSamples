package de.scrum_master.stackoverflow

import geb.spock.GebReportingSpec
import org.openqa.selenium.Cookie

class SetCookieIT extends GebReportingSpec {
  def "Cookie example"() {
    given:
    def options = driver.manage()

    when:
    go "https://www.wikipedia.org/"
    then:
    !options.getCookieNamed("my-geb-cookie")

    when:
    options.addCookie(new Cookie("my-geb-cookie", "foobar", ".wikipedia.org", "/", null))
    go "https://www.wikipedia.org/"
    then:
    title == "Wikipedia"
    options.getCookieNamed("my-geb-cookie").value == "foobar"
  }
}
