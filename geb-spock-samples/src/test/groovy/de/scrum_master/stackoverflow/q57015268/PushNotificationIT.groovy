package de.scrum_master.stackoverflow.q57015268

import geb.spock.GebReportingSpec
import spock.lang.Ignore

/**
 * This test just opens NZZ web site in the browser so you can visually check whether a push notification request pop-up
 * is displayed or not. If in dependency de.scrum-master:test-resources the browser settings in GebConfig.groovy are
 * correct, there should not be any pop-ups.
 *
 * For the negative test (pop-up shown with deactivated special browser settings) please make sure that for nzz.ch the
 * pop-up was not blocked yet in the browser settings, otherwise please reset it before running the test.
 *
 * Currently there are special driver settings for Chrome, Chrome Headless and Opera. Edge does not show any pop-ups,
 * PhantomJS works normally,too.
 */

@Ignore
class PushNotificationIT extends GebReportingSpec {
  def "Check if push notification is shown"() {
    given:
    go "https://www.nzz.ch"

    when: "contact link is clicked"
    $("a.personal__link")[1].click()

    then: "contact form is opened, no matter if pop-up on main page was displayed or not"
    waitFor(5) {
      $("h1").firstElement().text.contains("Kontakt")
    }
  }
}
