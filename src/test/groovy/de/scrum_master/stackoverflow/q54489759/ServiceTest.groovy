package de.scrum_master.stackoverflow.q54489759

import spock.lang.Specification
import spock.mock.AutoAttach
import spock.mock.DetachedMockFactory

class ServiceTest extends Specification {
  @AutoAttach
  DaoClass dao = new MockConfig().dao()
  ServiceClass service = new ServiceClass(dao: dao)

  static class MockConfig {
    def detachedMockFactory = new DetachedMockFactory()

    DaoClass dao() {
      detachedMockFactory.Mock(DaoClass)
    }
  }

  def "Test Success Senario"() {
    given: "dao"
    // Not needed due to field with @AutoAttach
    // new MockUtil().attachMock(dao, this)
    1 * dao.getAddressFromSomewhere("me") >> "USA"

    when: "call service "
    def actualResponse = service.getAddress("me")

    then: "dao should be invoked with given response"
//    dao.getAddressFromSomewhere("me")
    actualResponse == "USA"
  }

  def "testing api gateway"() {
    given:
    def sdk = Mock(MyAWSSDK)
    //sdk.lookupByField("xyz") >> "mock result"

    when:
    def myClass = new MyClass(sdk)
    def result = myClass.foo()

    then:
    //1 * sdk.lookupByField(_)
    1 * sdk.lookupByField("xyz") >> "mock result"
    result == "mock result"
  }

  static class MyAWSSDK {
    String lookupByField(String s) { "real result" }
  }
  static class MyClass {
    MyAWSSDK myAWSSDK

    MyClass(MyAWSSDK myAWSSDK) {
      this.myAWSSDK = myAWSSDK
    }

    String foo(String s) { myAWSSDK.lookupByField("xyz") }
  }
}
