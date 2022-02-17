  package de.scrum_master.stackoverflow.q54636103

  import org.spockframework.runtime.ConditionNotSatisfiedError
  import spock.lang.FailsWith
  import spock.lang.Specification

  class ApiGatewayTest extends Specification {
    @FailsWith(ConditionNotSatisfiedError)
    def "testing api gateway the wrong way"() {
      given:
      def sdk = Mock(MyAWSSDK)
      sdk.lookupByField("xyz") >> "mock result"

      when:
      def myClass = new MyClass(sdk)
      def result = myClass.foo()

      then:
      1 * sdk.lookupByField(_)
      result == "mock result"    // uh-oh!
    }

    def "testing api gateway the right way"() {
      given:
      def sdk = Mock(MyAWSSDK)

      when:
      def myClass = new MyClass(sdk)
      def result = myClass.foo()

      then:
      1 * sdk.lookupByField("xyz") >> "mock result"
      result == "mock result"    // yeah!
    }
  }
