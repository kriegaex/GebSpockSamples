package de.scrum_master.stackoverflow.q71598133

import geb.Page

class Test_Link3 extends Page {
  static url = this.getResource("/q71598133_sub3.html").toString()
  static at = { $("h1").text() == "Sub-page #3" }

  def crashPageDisplayed() { false }
}
