package de.scrum_master.stackoverflow.q68949748

import com.baeldung.jni.HelloWorldJNI
import spock.lang.Requires
import spock.lang.Specification

/**
 * Make sure to put the native libraries directory on the library path via
 *   -Djava.library.path=${basedir}/src/main/resources/lib
 * in Maven Surefire/Failsafe 'argLine' option
 */
@Requires({ os.windows || os.linux })
class BaeldungJNITest extends Specification {
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
