package de.scrum_master.stackoverflow

import geb.Page
import geb.module.Select
import org.openqa.selenium.interactions.Actions

class DateFormatTimeZonePage extends Page {
  static url = "file:///C:/Users/alexa/Documents/java-src/GebSpockSamples/src/test/resources/dateformat-timezone.htm"

  // See https://stackoverflow.com/a/51257121/1082681
  static boolean alternativeAt = false

  static at = {
    if (alternativeAt)
      dynamicH1.text() == "Dashboard Content Area"
    else
      $("h1").text() == "Sample Page"
  }

  static content = {
    dynamicH1(required: false, wait: 3, cache: false) { $("div#dashboardTitleDiv>h1") }
    modalDateDropdown { $("#dateFormat").module(Select) }
    modalTZoneDropdown { $("#timeZonePreference").module(Select) }
    fileUploadButton { $("#fileUpload") }
    textArea { $("#textArea") }
    submitButton { $("#submit") }
    foo(required: false, wait: 2) { $("#xxx") }
    label { $("label") }
    bottomDiv { $("#bottomDiv") }
    dates { module DateInputModule }
    yesNoList { module(new ListModule(listId: "dd-menu-assetReStandard-yes_no-udf")) }
    countries { module(new ListModule(listId: "countries")) }
  }

  def mouseHoverMethod() {
    waitFor { submitButton.displayed }
    println submitButton.class
    println submitButton.firstElement().class
    Actions actions = new Actions(driver)
    actions.moveToElement(submitButton.firstElement()).build().perform()
  }
}
