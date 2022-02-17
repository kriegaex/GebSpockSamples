package de.scrum_master.stackoverflow.q60341734

class Something {
  Doer doer

  Something(Doer doer) {
    this.doer = doer
  }

  boolean doSth(x) {
    if (!doer.validate(x)) throw new RuntimeException()
    true
  }
}
