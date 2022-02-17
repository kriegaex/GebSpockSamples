package de.scrum_master.stackoverflow.q66129162

import geb.spock.GebSpec
import org.openqa.selenium.By
import spock.lang.Unroll

class MultiElementNavigatorIT extends GebSpec {
  static url = this.getResource("/page-q66129162.html").toString()

  @Unroll
  def "select by #selectorType"() {
    given:
    go url
    def selector = selectorClosure()

    expect:
    selector.text() == "Student:"
    selector.parent().text() == "Student:"
    selector.parent().parent().text() == "Student: Foo Bar"
    selector.parent().parent().parent().text() == " Student Information\nStudent: Foo Bar\nClass X\nSection A"

    where:
    selectorType | selectorClosure
    "XPath"      | { $(By.xpath("//b[contains(text(),'Student:')][1]")) }
    "CSS"        | { $('b', text: contains('Student:')) }
  }
}
