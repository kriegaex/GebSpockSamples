package de.scrum_master.stackoverflow

import geb.Page

class EbayHomePage extends Page {
  static url = "https://www.ebay.com/"
  static at = { title == "Electronics, Cars, Fashion, Collectibles, Coupons and More | eBay" }
  static content = {
    headerW { module(HeaderModule) }
    profileModule { module(ProfileModule) }
  }
}
