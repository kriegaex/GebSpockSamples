package de.scrum_master.stackoverflow.q61667088

class Step {
  private String name

  Step(String name) {
    this.name = name
  }

  String getName() {
    return this.name
  }

  static String staticMethod() {
    return "original"
  }
}
