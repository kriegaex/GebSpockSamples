package de.scrum_master.stackoverflow.q59713798

class SomeClass {
  String someClassAttribute

  static def findAllByCodeLike(String s) {
    return [
      new SomeClass(someClassAttribute: "11"),
      new SomeClass(someClassAttribute: "22"),
      new SomeClass(someClassAttribute: "33")
    ]
  }
}
