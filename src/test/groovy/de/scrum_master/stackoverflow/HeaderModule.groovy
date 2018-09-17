package de.scrum_master.stackoverflow

import geb.Module
import geb.module.Select

class HeaderModule extends Module {
  static content = {
    signIn { $("a", text: "Sign in") }
    register { $("a", text: "register") }
    deals { $("a", _sp: "m570.l3188") }
    sell { $("a", _sp: "m570.l1528") }
    help { $("a", _sp: "m570.l1545") }
    trackOrder { $("a", _sp: "m570.l2624") }

    myEbay { $("a", _sp: "m570.l2919") }
    myPaisaPay { $("a.gh-eb-li-a") }

    profileExpand { $("#gh-ug") }

    searchField { $("#gh-ac") }
    selectCategory { $("#gh-cat").module(Select) }
    searchButton { $("#gh-btn") }
  }
}
