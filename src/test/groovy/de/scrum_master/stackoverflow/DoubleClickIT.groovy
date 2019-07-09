package de.scrum_master.stackoverflow


import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import org.openqa.selenium.interactions.Actions

/**
 * See https://stackoverflow.com/a/53908366/1082681
 */
class DoubleClickIT extends GebReportingSpec {
  def "double-click via Geb interaction"() {
    given:
    def page = to ArtOfTestingPage

    expect:
    withAlert {
      interact {
        doubleClick(page.doubleClickButton)
      }
    } == "Hi! Art Of Testing, Here!"
  }

  def "double-click via Selenium action"() {
    given:
    def page = to ArtOfTestingPage
    def doubleClickButton = driver.findElement(By.id("dblClkBtn"))
    def doubleClick = new Actions(driver).doubleClick(doubleClickButton).build()

    expect:
    withAlert {
      doubleClick.perform()
    } == "Hi! Art Of Testing, Here!"
  }
}
