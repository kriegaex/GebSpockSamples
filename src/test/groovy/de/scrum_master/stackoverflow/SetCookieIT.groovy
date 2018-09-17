package de.scrum_master.stackoverflow

import geb.spock.GebReportingSpec
import org.openqa.selenium.Cookie

class SetCookieIT extends GebReportingSpec {
  def "Cookie example"() {
    given:
    def options = driver.manage()

    when:
    go "https://www.wikipedia.org/"
    then:
    !options.getCookieNamed("my-geb-cookie")

    when:
    options.addCookie(new Cookie("my-geb-cookie", "foobar", ".wikipedia.org", "/", null))
    go "https://www.wikipedia.org/"
    then:
    title == "Wikipedia"
    options.getCookieNamed("my-geb-cookie").value == "foobar"
  }

  def "GitHub health check"() {
    given:
    go "https://status.github.com/api/status.json"

    expect:
    driver.pageSource =~ /"status":"good"/
  }

  // TODO: remove when no longer needed, this is just Geb mailing list support
  def xyz() {
    given:
    go "https://www.uline.com/Product/GuidedNav?t=184360&dup=over" // direct to Shipping Boxes
    expect:
    title == 'ULINE - Shipping Boxes'
    when:
    def currentPage = 1
    while (true) {
      waitFor { $("span.GNPaging a.DisabledLink")*.text().contains(currentPage.toString()) }
      def table = $('table.GNItemTable')
      assert table != null && table.size() > 0
      def theader = table.$('thead tr th')
      def next = $('a.EnabledLink', text: 'Next>')
      if (next == null || next.size() == 0) break
      next[0].click()
      currentPage++
    }
    then:
    true
  }
}
