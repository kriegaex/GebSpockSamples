package de.scrum_master.stackoverflow.q71598133

import spock.lang.Unroll

class SubPageNavigationIT extends LoginBaseTestSpec {
  @Unroll("test portal page #number")
  def "navigate to test portal pages"() {
    given: "starting at home page"
    to Test_HomePage
    report "home page"

    when: "clicking on navigation link"
    page."$clickLink"()
    report "navigation link clicked"

    then: "we are on target page"
    at targetPage
    !page.crashPageDisplayed()

    where:
    number | clickLink    | targetPage
    1      | 'clickLink1' | Test_Link1
    2      | 'clickLink2' | Test_Link2
    3      | 'clickLink3' | Test_Link3
  }
}
