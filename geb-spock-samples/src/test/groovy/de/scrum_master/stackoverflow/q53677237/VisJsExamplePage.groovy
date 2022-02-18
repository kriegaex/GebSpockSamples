package de.scrum_master.stackoverflow.q53677237

import geb.Page

class VisJsExamplePage extends Page {
  // For an unknown reason, the "Chrome is being controlled by automated test software" notification this pages from
  // loading correctly. The buttons on the top are visible, but the time graph on the bottom only appears after the
  // page is resized hotizontally. Therefore, when configuring the Chrome driver in GebConfig.groovy, an option like
  //   options.setExperimentalOption("excludeSwitches", ["enable-automation"])
  // for recent Chrome versions or the now deprecated
  //   options.addArguments("disable-infobars")
  // for older versions is necessary.
  static url = "https://visjs.github.io/vis-timeline/examples/timeline/interaction/animateWindow.html"
  static atCheckWaiting = true
  static at = {
    title.contains("Animate window") && fitAllItemsButton.displayed
      // Does not work on Chromium browsers, because time graph only loaded after manually resizing the window
      // && item4.displayed
  }

  static content = {
    item4(cache: false, required: false, wait: 5) { $(".vis-item-content", text: "item 4").parent().parent() }
    fitAllItemsButton(cache: false) { $("#fit") }
    draggable(required: false, cache: false, wait: 2) { $(".vis-drag-center") }
  }
}
