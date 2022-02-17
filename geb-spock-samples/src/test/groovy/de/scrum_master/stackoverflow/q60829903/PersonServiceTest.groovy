package de.scrum_master.stackoverflow.q60829903

import spock.lang.Specification

class PersonServiceTest extends Specification {
  def logService = Mock(LogService)

  def "test if id is logged"() {
    given:
    def person = new Person(name: "John Doe")
    def personRequest = new PersonRequest(person: person)

    and:
    def personId = "012345-6789-abcdef"
    def idCreator = Stub(IdCreator) {
      createId() >> personId
    }
    def personService = new PersonService(logService, idCreator)

    when:
    personService.savePerson(personRequest)

    then:
    1 * logService.logSavedId(personId)
    person.id == personId
  }

  static class Person {
    String id
    String name
  }

  static class PersonRequest {
    Person person
  }

  static class LogService {
    void logSavedId(String id) {
      println "Logged ID = $id"
    }
  }

  static class IdCreator {
    String createId() {
      return UUID.randomUUID().toString()
    }
  }
  static class PersonService {
    LogService logService
    IdCreator idCreator

    PersonService(LogService logService) {
      this(logService, new IdCreator())
    }

    PersonService(LogService logService, IdCreator idCreator) {
      this.logService = logService
      this.idCreator = idCreator
    }

    String savePerson(PersonRequest personRequest) {
      def id = idCreator.createId()
      personRequest.person.id = id
      logService.logSavedId(id)
      return id
    }
  }

}
