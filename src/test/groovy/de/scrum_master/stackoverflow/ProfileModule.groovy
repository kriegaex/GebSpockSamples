package de.scrum_master.stackoverflow

import geb.Module

class ProfileModule extends Module {
  static content = {
    username { $("li#gh-un") }
    userId { $("#gh-ui") }
    accountSettings { $("a", _sp: "m570.l3399") }
    signout { $("#gh-uo") }
  }
}
