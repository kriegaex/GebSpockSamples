package de.scrum_master.stackoverflow.q53677237

import geb.Page

class ArtOfTestingPage extends Page {
  static url = "https://demo.guru99.com/test/simple_context_menu.html"

  static at = {
    doubleClickButton.displayed
  }

  static content = {
    doubleClickButton(cache: false, required: false, wait: 5) { $("button", text: "Double-Click Me To See Alert") }
  }
}
