package de.scrum_master.stackoverflow.q53677237

import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.IgnoreIf
import spock.lang.Retry

import static spock.util.matcher.HamcrestMatchers.closeTo

/**
 * https://groups.google.com/g/geb-user/c/ROY9qcNm3E8/m/ioX0GZtBEAAJ
 * https://bugs.chromium.org/p/chromedriver/issues/detail?id=841
 * https://stackoverflow.com/a/54020579/1082681
 */
@Retry
class VisJsExampleIT extends GebReportingSpec {

  @IgnoreIf({
    sys["geb.env"] in [
      "ie",        // time graph panel is not displayed at all
      "html_unit"  // JS capabilities are not good enough in HtmlUnit 2.58.0, Selenium 3.141.59
    ]
  })
  def "dragging vis-js module"() {
    given:
    def toDragX = 200
    // For Chrome and HtmlUnit, delta 20 would be enough, Firefox needs ~40, on MacOS even ~105
    def permittedDragDelta = 110
    def page = to VisJsExamplePage
    def window = driver.manage().window()

    and:
    // Dummy resize to make time graph visible on Chromium browsers after 2nd resize (OMG!)
    window.size = new Dimension(200, 200)
    // Give page a second to refresh after the resize. Two resizes quickly one after another often trips up Firefox,
    // not displaying the time graph after the 2nd refresh at all. Probably this is some kind of JS problem.
    sleep 1000
    // Resize to make DnD distance more predictable
    window.size = new Dimension(1024, 768)
    println "Window size: ${window.size}"

    and:
    waitFor 3, {
      page.item4.displayed
    }
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
        moveByOffset(toDragX, 0)
        release()
      }
      else {
        // This does not work on Chromium-based browsers, because after the mouse click
        // there should be a dummy mouse movement before actually dragging the target element.
        dragAndDropBy(page.item4, toDragX, 0)
      }
    }
    report "After DnD"
    def draggedX = page.item4.x - x
    println "DnD from $x/$y to $page.item4.x/$page.item4.y (planned horizontal drag distance $toDragX, actual $draggedX)"

    then:
    draggedX closeTo(toDragX, permittedDragDelta)
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
