package de.scrum_master.stackoverflow.q53677237

import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.IgnoreIf

import static spock.util.matcher.HamcrestMatchers.closeTo

/**
 * https://groups.google.com/g/geb-user/c/ROY9qcNm3E8/m/ioX0GZtBEAAJ
 * https://bugs.chromium.org/p/chromedriver/issues/detail?id=841
 * https://stackoverflow.com/a/54020579/1082681
 */
class VisJsExampleIT extends GebReportingSpec {

  @IgnoreIf({
    sys["geb.env"] in [
      "ie",        // time graph panel is not displayed at all
      "html_unit"  // JS capabilities are not good enough
    ]
  })
  def "dragging vis-js module"() {
    given:
    def page = to VisJsExamplePage

    and:
    // Dummy resize to make time graph visible on Chromium browsers after 2nd resize (OMG!)
    driver.manage().window().size = new Dimension(200, 200)
    // Resize to make DnD distance more predictable
    driver.manage().window().size = new Dimension(1024, 768)
    def x = page.item4.x
    def y = page.item4.y

    when:
    report "Before activation click"
    page.item4.click()

    then:
    page.draggable.displayed

    when:
    report "After activation click"
    interact {
      if (System.getProperty("geb.env") in ["chrome", "chrome_headless", "edge"]) {
        clickAndHold(page.item4)
        // Workaround for first mouse movement being ignored on Chromium-based browsers:
        // Dummy-move once before doing the movement we actually want.
        moveByOffset(-100, 0)
        moveByOffset(200, 0)
        release()
      }
      else {
        // This does not work on Chromium-based browsers, because after the mouse click
        // there should be a dummy mouse movement before actually dragging the target element.
        dragAndDropBy(page.item4, 200, 0)
      }
    }
    report "After DnD"
    def movedByX = page.item4.x - x
    println "$x/$y -> $page.item4.x/$page.item4.y"

    then:
    movedByX closeTo(200, 20)
  }

  def "double clicking test"() {
    given:
    go "https://unixpapa.com/js/testmouse-2.html"
    waitFor { $("#link").displayed }
    def clickable = $("#link")

    when:
/*
    if (browser.getDriver().toString().contains("chrome")) {
      // Work-around for Chromedriver's double-click implementation
      clickable.click()
    }
*/
    interact {
      doubleClick(clickable)
    }
    println "Browser ID: " + $("body > tt")*.text()
    println "Action log:\n" + $("textarea").value()

    then:
    true
  }
}
