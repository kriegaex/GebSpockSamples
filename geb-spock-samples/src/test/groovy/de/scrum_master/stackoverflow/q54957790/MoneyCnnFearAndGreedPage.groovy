package de.scrum_master.stackoverflow.q54957790

import geb.Page

class MoneyCnnFearAndGreedPage extends Page {
  static url = 'https://money.cnn.com/data/fear-and-greed'
  static at = { true && title.toLowerCase() =~ /fear (&|and) greed index/ }

  static content = {
    // Not working, because invisible
    needleChart(required: false) { $("#needleChart") }
    // Not working, because invisible
    fearAndGreed { needleChart.$("ul li")[0].text() }
    // Sometimes, a different UI variant is displayed (A/B testing? Device dependency?)
    alternativeGaugeValue(required: false, wait: 2) { $("span.market-fng-gauge__dial-number-value") }
  }
}
