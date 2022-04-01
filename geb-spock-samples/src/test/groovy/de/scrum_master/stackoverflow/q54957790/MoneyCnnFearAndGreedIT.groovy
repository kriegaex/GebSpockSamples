package de.scrum_master.stackoverflow.q54957790

import geb.spock.GebReportingSpec
import spock.lang.Ignore
import spock.lang.Issue

@Issue("https://stackoverflow.com/a/54958984/1082681")
@Ignore("flaky, CNN website is volatile")
class MoneyCnnFearAndGreedIT extends GebReportingSpec {
  def test() {
    given:
    def page = to MoneyCnnFearAndGreedPage
    String result

    when:
    if (page.alternativeGaugeValue?.displayed) {
      result = page.alternativeGaugeValue.text()
      println "Alternative gauge value: $result"
    }
    else {
      // Element containing test is hidden -> got to use JS to access content
      result = js.exec("return document.querySelectorAll('#needleChart ul li')[0].textContent;")
      println "Needle chart text: $result"
    }

    then:
    result.toLowerCase() =~ /fear (&|and) greed now/ || result.trim() ==~ /[0-9]+/
  }
}
