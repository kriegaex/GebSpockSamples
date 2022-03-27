package de.scrum_master.stackoverflow.q54957790

import geb.spock.GebReportingSpec

class MoneyCnnFearAndGreedIT extends GebReportingSpec {
  def test() {
    given:
    to MoneyCnnFearAndGreedPage
    when:
    String result = js.exec("return document.querySelectorAll('#needleChart ul li')[0].textContent;")
    println result
    then:
    result.toLowerCase() =~ /fear (&|and) greed now/
  }
}
