package de.scrum_master.stackoverflow

import geb.spock.GebReportingSpec

class EbaySearchIT extends GebReportingSpec {
  def "Searching iPhone 6"() {
    given:
    to EbayHomePage

    when:
    waitFor { at(EbayHomePage) }
    at(EbayHomePage).with {
      headerW.searchField.value("iPhone 6")
      headerW.selectCategory.setSelected("Cell Phones & Accessories")
      headerW.searchButton.click()
    }
    waitFor 10, { at(SearchResultPage).itemTitle.isDisplayed() }
    String itemName = at(SearchResultPage).itemTitle.text()
    at(SearchResultPage).itemTitle.click()

    then:
    assert at(ItemDetailsPage).itemName.text() == itemName
  }
}
