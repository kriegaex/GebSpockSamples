package de.scrum_master.stackoverflow

import de.scrum_master.testing.GebTestHelper
import geb.spock.GebReportingSpec
import org.openqa.selenium.Alert
import org.openqa.selenium.Dimension
import spock.lang.Requires

class DateFormatTimeZoneIT extends GebReportingSpec {
  def "Check texts for selected drop-down elements"() {
    given:
    def page = to DateFormatTimeZonePage

    when: "selecting drop-down elements by unique value"
    page.modalDateDropdown = "DD/MM/YYYY"
    page.modalTZoneDropdown = "US/Samoa"

    then: "corresponding texts match"
    page.modalDateDropdown.selectedText == "DD/MM/YYYY"
    page.modalTZoneDropdown.selectedText == "GMT -11:00, Samoa Time Samoa"

    when: "selecting drop-down default element with non-unique value by text"
    page.modalTZoneDropdown = "- Select time zone -"

    then: "corresponding value matches"
    page.modalTZoneDropdown.selected == "America/Chicago"

    when: "selecting another drop-down element with non-unique value by text"
    page.modalTZoneDropdown = "GMT -06:00, Central Time (Chicago)"

    then: "corresponding value matches"
    page.modalTZoneDropdown.selected == "America/Chicago"
  }

  def "Check for non-required, non-existent element"() {
    given:
    def page = to DateFormatTimeZonePage

    expect:
    page.foo.empty
  }

  def "Check read-only attributes"() {
    given:
    def page = to DateFormatTimeZonePage

    expect:
    page.dates.input1.@readonly
    !page.dates.input2.@readonly
    page.dates.input3.@readonly
    !page.dates.input4.@readonly
    page.dates.input5.@readonly
  }

  def "Mouse hover"() {
    given:
    def page = to DateFormatTimeZonePage

    when:
    page.mouseHoverMethod()

    then:
    true
  }
  def "Look up text and set area content"() {
    given:
    def page = to DateFormatTimeZonePage

    expect:
    page.textArea == "The big brown fox jumps over the lazy dog"
    page.textArea.value() =~ /brown fox/

    when:
    page.textArea = "Hello world!"

    then:
    page.textArea == "Hello world!"
    page.textArea.value() =~ /world/
  }

  def "File upload"() {
    given:
    def uploadFile = new File("src/test/resources/test.txt")
    def page = to DateFormatTimeZonePage
    report "Before selecting upload file"

    when:
    page.fileUploadButton = uploadFile.absolutePath

    then:
    report "After selecting upload file"
    page.fileUploadButton.value() =~ /$uploadFile.name$/

    when:
    page.submitButton.click()

    then:
    report "After uploading file"
    page.fileUploadButton.value() == ""
  }

  def "Get text from CSS pseudo-element ::before"() {
    given:
    to DateFormatTimeZonePage
    def jsCode = '''
      return window
        .getComputedStyle(document.querySelector('label'),'::before')
        .getPropertyValue('content')
    '''

    when:
    def pseudoElementContent = js.exec(jsCode)[1..-2]

    then:
    pseudoElementContent == "some text"
  }

  // Only Chrome in headless mode seems to resize the window exactly as specified.
  // Other browsers resize to sometimes vastly different dimensions.
  @Requires({ sys["geb.env"] == "chrome_headless" })
  def "Check element visibility"() {
    given:
    def testHelper = new GebTestHelper(driver, js)
    def page = to DateFormatTimeZonePage
    def originalWindowSize = driver.manage().window().size
    def labelSingle = page.label.singleElement()

    when:
    report "Initial window"
    println "Initial window"

    then:
    testHelper.isPartiallyVisible(labelSingle)
    testHelper.isFullyVisible(labelSingle)

    when:
    int resizeWidth = 200
    int resizeHeight = 200
    driver.manage().window().size = new Dimension(resizeWidth, resizeHeight)
    def windowSize = driver.manage().window().size
    report "Resized window unscrolled"
    println "Resized window unscrolled"

    then:
    windowSize.width == resizeHeight
    windowSize.height == resizeHeight
    !testHelper.isPartiallyVisible(labelSingle)
    !testHelper.isFullyVisible(labelSingle)

    when:
    labelSingle.click()
    report "Resized window scrolled to label"
    println "Resized window scrolled to label"

    then:
    testHelper.isPartiallyVisible(labelSingle)
    !testHelper.isFullyVisible(labelSingle)

    when:
    page.bottomDiv.click()
    report "Resized window scrolled to bottom div"
    println "Resized window scrolled to bottom div"

    then:
    testHelper.isPartiallyVisible(labelSingle)
    testHelper.isFullyVisible(labelSingle)

    cleanup:
    driver.manage().window().size = originalWindowSize
  }

  def "Resize window"() {
    given:
    def page = to DateFormatTimeZonePage
    def originalWindowSize = driver.manage().window().size

//    report "Initial window"
    println "Initial window"

    when:
    int resizeWidth = 250
    int resizeHeight = 200
    driver.manage().window().size = new Dimension(resizeWidth, resizeHeight)
    def windowSize = driver.manage().window().size
    println "Window size = $windowSize"
//    report "Resized window 1"
    println "Resized window 1"

    then:
    windowSize.width == resizeWidth
    windowSize.height == resizeHeight

    when:
    resizeWidth = 444
    resizeHeight = 333
    driver.manage().window().size = new Dimension(resizeWidth, resizeHeight)
    windowSize = driver.manage().window().size
    println "Window size = $windowSize"
//    report "Resized window 2"
    println "Resized window 2"

    then:
    windowSize.width == resizeWidth
    windowSize.height == resizeHeight
    println "Finished"

    cleanup:
    driver.manage().window().size = originalWindowSize
  }

  def "Dynamically added 'at' content"() {
    expect:
    to DateFormatTimeZonePage

    when:
    DateFormatTimeZonePage.alternativeAt = true
    then:
    !at(DateFormatTimeZonePage)

    when:
    js.exec("""
      var bottomDiv = document.getElementById('bottomDiv');
      var div = document.createElement('div');
      div.id = 'dashboardTitleDiv';
      div.innerHTML = '<h1>Dashboard Content Area</h1>';
      bottomDiv.parentNode.insertBefore(div, bottomDiv);
    """)
    sleep 100
    then:
    at DateFormatTimeZonePage

    when:
    go "/Presse/blog"
    report "Presse"

    then:
    $("a.contentpagetitle")*.text().find({ it.contains("Solisten") })

    cleanup:
    DateFormatTimeZonePage.alternativeAt = false
  }

  def "Iteract with list elements"() {
    given:
    def page = to DateFormatTimeZonePage

    when:
    page.yesNoList.clickTitle("Yes")
    page.yesNoList.clickText("No")

    then:
    page.yesNoList.byTitle("Yes").text() == "Yes"
    page.yesNoList.byText("No").text() == "No"

    when:
    page.countries.clickTitle("DE")
    page.countries.clickText("Italy")
    page.countries.clickText("Thailand")
    page.countries.clickTitle("US")

    then:
    page.countries.byTitle("DE").text() == "Germany"
    page.countries.byText("Italy").text() == "Italy"
    page.countries.byText("Thailand").text() == "Thailand"
    page.countries.byTitle("US").text() == "USA"
  }

  def "Interact with alert/confirm dialogues"() {
    given:
    def page = to DateFormatTimeZonePage

    expect:
    withAlert(wait: true) { $("input", name: "showAlert").click() } == "Bang!"
    withConfirm(true) { $("input", name: "showConfirm").click() } == "Do you like Geb?"

    when:
    $("input", name: "showAlert").click()
    def alert = driver.switchTo().alert()

    then:
    alert.text == "Bang!"
    alert.accept()

    when:
    $("input", name: "showConfirm").click()
    alert = driver.switchTo().alert()

    then:
    alert.text == "Do you like Geb?"
    alert.accept()
  }
}
