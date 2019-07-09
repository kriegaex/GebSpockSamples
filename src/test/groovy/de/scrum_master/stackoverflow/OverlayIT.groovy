package de.scrum_master.stackoverflow

import geb.spock.GebReportingSpec
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import spock.lang.IgnoreIf

@IgnoreIf({
  sys["geb.env"] in [
    "ie",         // Selenium IE Driver does not support 'file://' URLs
    "phantomjs",  // PhantomJS does not throw "not clickable" exception when element is overlayed
    "html_unit"   // HTMLUnit's JS support is not fancy enough for overlays
  ]
})
class OverlayIT extends GebReportingSpec {
  def "Overlay page"() {
    given:
    go getClass().getResource("/overlay-menu-page.html").toString()
    expect:
    !$("#overlay-content").$("a")[0].displayed
    report "No initial overlay"

    when:
    $("span").findAll { it.text().contains("open") }[0].click()
    then:
    noExceptionThrown()
    waitFor 3, { $(".closebtn").displayed }
    ExpectedConditions.elementToBeClickable $("span").findAll { it.text().contains("open") }[0].firstElement()
    report "Overlay visible"

    when:
    $("span").findAll { it.text().contains("open") }[0].firstElement().click()
    then:
    WebDriverException exception = thrown()
    exception.message =~ /not clickable|obscured/
    report "Cannot click 'open'"
    when:
    $(".closebtn").click()
    then:
    waitFor 3, { !$(".closebtn").displayed }
    report "Overlay is gone again"

    when:
    $("span").findAll { it.text().contains("open") }[0].click()
    then:
    noExceptionThrown()
    report "Can click 'open' again"
  }
}
