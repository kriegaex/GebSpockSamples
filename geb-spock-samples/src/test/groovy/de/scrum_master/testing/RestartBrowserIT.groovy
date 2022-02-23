package de.scrum_master.testing

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import spock.lang.Retry

/**
 * See http://stackoverflow.com/questions/42069291
 */
@Retry
class RestartBrowserIT extends GebReportingSpec {
  def "Search web site Scrum-Master.de"() {
    when:_ "download page is opened"
    go "https://scrum-master.de"
    report "welcome page"

    then:_ "expected text is found on page"
    $("h2").text().startsWith("Herzlich Willkommen bei Scrum-Master.de")

    when:_ "browser is reset"
    testManager.resetBrowser()
    CachingDriverFactory.clearCacheAndQuitDriver()

    and:_ "download page is opened again in new browser"
    go "https://scrum-master.de/Downloads"
    report "download page"

    then:_ "expected text is found on page"
    $("h2").text().startsWith("Scrum on a Page")
  }
}
