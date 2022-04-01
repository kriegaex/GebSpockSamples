package de.scrum_master.stackoverflow

import geb.spock.GebReportingSpec
import org.openqa.selenium.Cookie

class GitHubHealthCheckIT extends GebReportingSpec {
  def "GitHub health check"() {
    given:
    go "https://www.githubstatus.com/api/v2/status.json"

    expect:
    driver.pageSource =~ /githubstatus.com",.*,"status":/
  }
}
