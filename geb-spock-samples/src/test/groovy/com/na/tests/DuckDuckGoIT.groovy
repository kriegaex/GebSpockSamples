package com.na.tests

import geb.spock.GebSpec

class DuckDuckGoIT extends GebSpec {
  def "search 'Groovy Browser Automation' in duckduckgo"() {
    given: "we are on the duckduckgo search-engine"
    go "https://duckduckgo.com"

    when: "we search for 'Groovy Browser Automation'"
    waitFor 2, 0.1, {
      $("input#search_form_input_homepage").value("Groovy Browser Automation")
    }
    waitFor 2, 0.1, {
      $("input#search_button_homepage").click()
    }

    then: "the first result (excluding ads) is the geb website"
    waitFor 3, {
      $('article[data-testid="result"] h2>a', 0).attr("href").startsWith("https://gebish.org/")
    }
  }
}
