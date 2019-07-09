package de.scrum_master.testing

import geb.Page

class WindowsCalculatorPage extends Page {
  static at = { driver.findElementByAccessibilityId("CalculatorResults") }
}
