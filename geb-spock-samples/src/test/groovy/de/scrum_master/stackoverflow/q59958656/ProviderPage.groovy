package de.scrum_master.stackoverflow.q59958656

import geb.Page

class ProviderPage extends Page {
  static content = {
    heading { $(".h3 a").text() }
  }

  def waitForHeading() {
    waitFor { assert $(".h3 a") }
  }
}
