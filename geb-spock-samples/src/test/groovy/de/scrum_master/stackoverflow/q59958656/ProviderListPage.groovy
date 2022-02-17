package de.scrum_master.stackoverflow.q59958656

import geb.Page

class ProviderListPage extends Page {
  static url = "/trending"

  static content = {
    providers { $(".h3 a") }
  }

  def list() {
    return providers
  }
}
