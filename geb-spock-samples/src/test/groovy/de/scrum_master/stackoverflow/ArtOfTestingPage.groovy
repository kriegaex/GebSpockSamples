package de.scrum_master.stackoverflow

import geb.Page

class ArtOfTestingPage extends Page {
  static url = "https://demo.guru99.com/test/simple_context_menu.html"

  static at = {
    doubleClickButton.displayed
  }

  static content = {
    doubleClickButton(wait: 5) { $("button", text: "Double-Click Me To See Alert") }
  }
}
