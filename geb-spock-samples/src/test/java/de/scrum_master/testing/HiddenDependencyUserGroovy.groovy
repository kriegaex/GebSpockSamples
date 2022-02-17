package de.scrum_master.testing

class HiddenDependencyUserGroovy {
  String getMessage() {
    return new HiddenDependencyGroovy().getResult()
  }
}
