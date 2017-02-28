package de.scrum_master.testing

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec

/**
 * See http://stackoverflow.com/questions/42069291
 */
class RestartBrowserIT extends GebReportingSpec {
  def "Search web site Scrum-Master.de"() {
    when:
    go "https://scrum-master.de"
    report "welcome page"

    then:
    $("h2").text().startsWith("Herzlich Willkommen bei Scrum-Master.de")

    when: "browser is reset"
    resetBrowser()
    CachingDriverFactory.clearCacheAndQuitDriver()

    and: "download page is opened in new browser"
    go "https://scrum-master.de/Downloads"
    report "download page"

    then: "expected text is found on page"
    $("h2").text().startsWith("Scrum on a Page")
  }
}
