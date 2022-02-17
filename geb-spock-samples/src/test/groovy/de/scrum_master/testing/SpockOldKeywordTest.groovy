package de.scrum_master.testing

import de.scrum_master.stackoverflow.q57298557.Person
import spock.lang.Specification

class SpockOldKeywordTest extends Specification {
  def "use Spock 'old' with primitive value"() {
    given:
    def counter = 11

    when:
    counter *= 2

    then:
    counter == old(counter) * 2
  }

  def "use Spock 'old' with object type"() {
    given: "a person named Joe with black hair"
    def person = new Person(name: "Joe", hair: "black")

    when: "changing to person named James with brown hair"
    person.name = "James"
    person.hair = "brown"

    then: "Spock can tell the person's current from its old properties"
    person.toString() =~ /James.*brown/
    old(person.toString()) =~ /Joe.*black/

    and: "CAVEAT: The old person instance is the same as the current one!"
    old(person).toString() =~ /James.*brown/
  }
}
