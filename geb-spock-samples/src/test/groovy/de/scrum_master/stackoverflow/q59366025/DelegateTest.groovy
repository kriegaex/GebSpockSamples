package de.scrum_master.stackoverflow.q59366025

import spock.lang.Specification

class DelegateTest extends Specification {
  def delegate = Mock(Delegate)
  def invoker = new Invoker(delegate)

//  Delegate delegate
//  RegistrationService registration
//  Invoker invoker
//
//  def setup() {
//    registration = Mock()
//    delegate = Spy(constructorArgs: [registration])
//    invoker = new Invoker(delegate)
//  }
  def "Invoker should invoke delegate passed to it"() {
    given:
    def userData = new UserData()
    when:
    invoker.invoke(userData)
    then:
    1 * delegate.processUser(userData)
  }
}
