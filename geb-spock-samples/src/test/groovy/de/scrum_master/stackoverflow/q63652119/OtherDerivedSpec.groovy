package de.scrum_master.stackoverflow.q63652119

class OtherDerivedSpec extends AbstractSpec {
  def "check if proper event was emited"() {
    when:
    def someId = facade.doSomething().block()
    println "facade = $facade, eventBus = ${eventBus()}"

    then:
    someId ==  "someId"
  }
}
