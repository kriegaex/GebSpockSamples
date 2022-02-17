package de.scrum_master.testing

import geb.spock.GebReportingSpec
import org.openqa.selenium.By

/**
 * See https://groups.google.com/d/msg/geb-user/gXsLLAKOUCg/NeKPzDMPBAAJ
 *
 * Current status: cannot reproduce problem, working fine in Chrome 76
 */
class GebSelectorsNoLongerWorkingWithChromedriver76IT extends GebReportingSpec {
  static url = this.getResource("/simple-form-page.html").toString()

  def test() {
    given:
    go url

    when:
    $(name: "Zip Code") << "11111"
    report "1"

    and:
    $("[name='Zip Code']").value("22222")
    report "2"

    and:
    $(By.name("Zip Code")).value("33333")
    report "3"

    and:
    def element = driver.findElement(By.name("Zip Code"))
    element.clear()
    element.sendKeys("44444")
    report "4"

    then:
    true

    when: "https://stackoverflow.com/a/68493406/1082681"
    def nameLabel = $("td.RowLabelPopup")[0]

    then:
    nameLabel.text() == '" Name: "'
  }
}
