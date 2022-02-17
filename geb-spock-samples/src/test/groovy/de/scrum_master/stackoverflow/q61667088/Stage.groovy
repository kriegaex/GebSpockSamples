package de.scrum_master.stackoverflow.q61667088

class Stage {
  Step step

  Stage(String name) {
    this.step = new Step(name)
  }

  String run() {
    return step.getName()
  }
}
