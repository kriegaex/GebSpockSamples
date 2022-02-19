package de.scrum_master.stackoverflow.q59958656

import geb.Page

class ProviderListPage extends Page {
  static url = "https://github.com/trending"
  static at = { heading == "Trending" && providers.size() >= 10 }

  static content = {
    heading { $("h1.h1").text() }
    providers(wait: 5) { $(".h3 a") }
  }

  def list() {
    return providers
  }
}
