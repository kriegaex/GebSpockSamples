package de.scrum_master.stackoverflow

import geb.Page

class ArtOfTestingPage extends Page {
  static url = "https://artoftesting.com/sampleSiteForSelenium.html"

  static at = {
    doubleClickButton.displayed
  }

  static content = {
    doubleClickButton(wait: 5) { $("#dblClkBtn") }
  }
}
