package de.scrum_master.stackoverflow.q53677237

import geb.spock.GebReportingSpec
import org.openqa.selenium.interactions.Actions
import spock.lang.IgnoreIf
import spock.lang.Retry

/**
 * See https://stackoverflow.com/a/53908366/1082681
 */
@IgnoreIf({
  // TODO: This test is flaky on GitHub under MacOS, no idea why.
  os.macOs
})
@Retry
class DoubleClickIT extends GebReportingSpec {
  def "double-click via Geb interaction"() {
    given:
    def page = to ArtOfTestingPage

    expect:
    withAlert {
      interact {
        doubleClick(page.doubleClickButton)
      }
    } == "You double clicked me.. Thank You.."
  }

  def "double-click via Selenium action"() {
    given:
    def page = to ArtOfTestingPage
    def doubleClickAction = new Actions(driver)
      .doubleClick(page.doubleClickButton.singleElement())
      .build()

    expect:
    withAlert(wait: 5) {
      doubleClickAction.perform()
    } == "You double clicked me.. Thank You.."
  }
}
