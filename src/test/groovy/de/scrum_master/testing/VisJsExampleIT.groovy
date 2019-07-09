package de.scrum_master.testing


import geb.spock.GebReportingSpec
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.*
import org.openqa.selenium.interactions.internal.Locatable
import spock.lang.Requires

import java.time.Duration

import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT
import static spock.util.matcher.HamcrestMatchers.closeTo

class VisJsExampleIT extends GebReportingSpec {

  static class DragDropWorkaround {
    Actions dragAndDropBy(WebElement source, int xOffset, int yOffset) {
      if (isBuildingActions()) {
        action.addAction(new ClickAndHoldAction(jsonMouse, (Locatable) source))
        // The next line is a workaround for Chrome, Opera, Edge
        // but currently does not work for Chrome 75 anymore
        action.addAction(new MoveToOffsetAction(jsonMouse, null, -100, -100))
        action.addAction(new MoveToOffsetAction(jsonMouse, null, xOffset, yOffset))
        action.addAction(new ButtonReleaseAction(jsonMouse, null))
      }

      return moveInTicks(source, 0, 0)
        .tick(defaultMouse.createPointerDown(LEFT.asArg()))
        .tick(defaultMouse.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.pointer(), xOffset, yOffset))
        .tick(defaultMouse.createPointerUp(LEFT.asArg()))
    }
  }

  def setupSpec() {
    Actions.mixin DragDropWorkaround
  }

  @Requires({ sys["geb.env"] in ["opera", "edge", "firefox"] })
  def "dragging vis-js module"() {
    given:
    def page = to VisJsExamplePage
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
      dragAndDropBy(page.item4, 200, 0)
    }
    report "After DnD"
    def movedByX = page.item4.x - x
    println "$x/$y -> $page.item4.x/$page.item4.y"

    then:
    movedByX closeTo(200, 10)
  }

  def "double clicking test"() {
    given:
    go "https://unixpapa.com/js/testmouse-2.html"
    waitFor { $("#link").displayed }
    def clickable = $("#link")

    when:
    interact {
      doubleClick(clickable)
    }
    println "Browser ID: " + $("body > tt")*.text()
    println "Action log:\n" + $("textarea").value()

    then:
    true
  }
}
