package de.scrum_master.stackoverflow.q54957790

import geb.Page

class MoneyCnnFearAndGreedPage extends Page {
  static url = 'https://money.cnn.com/data/fear-and-greed'
  static at = { title.contains("Fear & Greed Index") && $("#needleChart").$("ul li").size() > 0 }
  static content = {
    fearAndGreed { $("#needleChart").$("ul li")[0].text() }
  }
}
