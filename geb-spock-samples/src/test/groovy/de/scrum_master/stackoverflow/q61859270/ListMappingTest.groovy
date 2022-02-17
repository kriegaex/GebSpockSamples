package de.scrum_master.stackoverflow.q61859270

import spock.lang.Specification

class ListMappingTest extends Specification {
  static class UnderTest {
    List<String> getFirstList() {
      ["a", "b", "c", "d"]
    }

    List<String> getSecondList() {
      ["dog", "goat", "wolf", "fox"]
    }
  }

  def "lists in matching order"() {
    given:
    def underTest = new UnderTest()
    def list1 = underTest.getFirstList()
    def list2 = underTest.getSecondList()

    expect: "one way to do it: list of lists"
    [list1, list2].transpose() ==
      [["a", "dog"], ["b", "goat"], ["c", "wolf"], ["d", "fox"]]

    and: "another way to do it: map"
    [list1, list2].transpose().collectEntries { [it[0], it[1]] } ==
      [a: "dog", b: "goat", c: "wolf", d: "fox"]
  }

  def "lists in non-matching order"() {
    given:
    def underTest = new UnderTest()
    def list1 = underTest.getFirstList().swap(0, 3)
    def list2 = underTest.getSecondList().swap(1, 2)
    def set1 = list1.toSet()
    def set2 = list2.toSet()

    expect:
    list1.size() == set1.size()
    list2.size() == set2.size()
    set1 == ["a", "b", "c", "d"].toSet()
    set2 == ["dog", "goat", "wolf", "fox"].toSet()
  }
}
