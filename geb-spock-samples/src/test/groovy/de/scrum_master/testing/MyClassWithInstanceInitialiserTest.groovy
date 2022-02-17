package de.scrum_master.testing

import spock.lang.Specification

class MyClassWithInstanceInitialiserTest extends Specification {
  def dummyTest() {
    expect:
    new MyClassWithInstanceInitialiser().name == "John"
    new MyClassWithInstanceInitialiser("Doe").name == "John Doe"
    new MyClassWithInstanceInitialiser(5).id == 16
  }
}
