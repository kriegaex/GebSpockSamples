package de.scrum_master.testing

import org.openqa.selenium.Dimension
import spock.lang.Retry

@Retry
class AShotIT extends AShotReportingSpec {
  def "Visit Scrum-Master.de download page"() {
    when:
    go "https://scrum-master.de/Downloads"
    driver.manage().window().size = new Dimension(640, 480)
    report "viewport"
    reportFullPage "AShot full page"
    reportElements "AShot single element", $("div.article-content")
    reportElements "AShot multiple elements", $("div#ja-mainnavwrap"), $("h2"), $("li", text: iContains("Scrum"))

    then:
    $("h2").text().startsWith("Scrum on a Page")
  }
}
