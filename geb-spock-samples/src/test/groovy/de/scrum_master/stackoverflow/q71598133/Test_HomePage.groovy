package de.scrum_master.stackoverflow.q71598133

import geb.Page

class Test_HomePage extends Page {
  static url = this.getResource("/q71598133_home.html").toString()
  static at = { $("h1").text() == "Main page" }

  static content = {
    link1 { $("#first") }
    link2 { $("#second") }
    link3 { $("#third") }
  }

  def clickLink1() { link1.click() }
  def clickLink2() { link2.click() }
  def clickLink3() { link3.click() }
}
