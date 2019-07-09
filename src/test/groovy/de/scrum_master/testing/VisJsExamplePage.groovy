package de.scrum_master.testing

import geb.Page

class VisJsExamplePage extends Page {
  static url = "https://visjs.org/examples/timeline/interaction/animateWindow.html"
  static atCheckWaiting = true
  static at = { title.contains("Animate window") && item4.displayed }

  static content = {
    item4(cache: false) { $(".vis-item-content", text: "item 4") }
    draggable(cache: false, wait: 2) { $(".vis-drag-center") }
  }
}
