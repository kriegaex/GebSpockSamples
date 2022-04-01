package de.scrum_master.stackoverflow.q71598133

import geb.Page

class Test_Link2 extends Page {
  static url = this.getResource("/q71598133_sub2.html").toString()
  static at = { $("h1").text() == "Sub-page #2" }

  def crashPageDisplayed() { false }
}
