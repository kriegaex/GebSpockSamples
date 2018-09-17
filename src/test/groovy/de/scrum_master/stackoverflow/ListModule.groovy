package de.scrum_master.stackoverflow

import geb.Module

class ListModule extends Module {
  String listId

  static content = {
    list { $("ul", id: listId) }
  }

  def byTitle(String title) {
    list.$("li", title: title)
  }

  def byText(String text) {
    list.$("li", text: text)
  }

  def clickTitle(String title) {
    byTitle(title).click()
  }

  def clickText(String text) {
    byText(text).click()
  }
}
