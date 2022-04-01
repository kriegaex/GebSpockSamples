package de.scrum_master.stackoverflow.q71598133

import geb.Page

class Test_Link1 extends Page {
  static url = this.getResource("/q71598133_sub1.html").toString()
  static at = { $("h1").text() == "Sub-page #1" }

  def crashPageDisplayed() { false }
}
