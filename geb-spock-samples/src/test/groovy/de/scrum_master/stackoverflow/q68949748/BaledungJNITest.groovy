package de.scrum_master.stackoverflow.q68949748

import com.baeldung.jni.HelloWorldJNI
import spock.lang.Specification

/**
 * Make sure to copy c:\Users\Alexa\Documents\java-src\GebSpockSamples\geb-spock-samples\native.dll to the
 * library path, e.g. to the Maven module base path which is used as '.'
 */
class BaledungJNITest extends Specification {
  def "verify original native method"() {
    given:
    HelloWorldJNI helloWorld = new HelloWorldJNI()

    expect:
    helloWorld.sayHello() == "Hello from C++ !!"
  }

  def "mock native method"() {
    given:
    HelloWorldJNI helloWorld = Mock() {
      sayHello() >> "mocked"
    }

    expect:
    helloWorld.sayHello() == "mocked"
  }

  def "inject mock into utility class"() {
    given:
    HelloWorldJNI helloWorld = Mock()
    Util.setHelloWorld(helloWorld)

    when:
    Util.doSomethingUseful()

    then:
    1 * helloWorld.sayHello() >> "mocked"
  }

}
