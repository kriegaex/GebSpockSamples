package de.scrum_master.testing.linkedin

import geb.spock.GebReportingSpec
import spock.lang.Requires

@Requires({ user && password })
class ScrapeLinkedInGroupIT extends GebReportingSpec {
  private static String user = System.getProperty('linkedInUser')
  private static String password = System.getProperty('linkedInPW')

  def test() {
    when:
    go 'https://www.linkedin.com/groups/3038830/'
    report 'login page'
    $('button[action-type="ACCEPT"]').click()
    report 'cookies accepted'
    $('a.main__sign-in-link').click()
    $('input#username').value(user)
    $('input#password').value(password)
    report 'login data entered'
    $('button[aria-label="Einloggen"]').click()
    report 'signed in'
    $('aside#msg-overlay button>li-icon[type="chevron-down"]').click()
    report 'message pane hidden'

    then:
    $('h1.groups-entity__name').text().contains('Freiberufler Projektmarkt')
  }
}
