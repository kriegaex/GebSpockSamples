package com.na.tests

import geb.spock.GebSpec

class MyBaseIT extends GebSpec {
  def "search 'Groovy Browser Automation' in duckduckgo"() {
    given: "we are on the duckduckgo search-engine"
    go "http://duckduckgo.com"

    when: "we search for 'Groovy Browser Automation'"
    $("#search_form_homepage").q = "Groovy Browser Automation"
    $("#search_button_homepage").click()

    then: "the first result (excluding ads) is the geb website"
    $("#links").$(".links_main a", 0).attr("href") == "https://gebish.org/"
  }
}
