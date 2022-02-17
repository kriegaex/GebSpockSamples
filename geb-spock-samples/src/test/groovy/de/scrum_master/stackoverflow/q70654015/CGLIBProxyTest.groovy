package de.scrum_master.stackoverflow.q70654015

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import spock.lang.Specification

class CGLIBProxyTest extends Specification {
  def "create CGLIB proxy for Java class"() {
    given:
    Enhancer enhancer = new Enhancer()
    enhancer.superclass = SampleJava
    enhancer.callback = { obj, method, args, proxy ->
      method.getDeclaringClass() != Object.class && method.getReturnType() == String.class
        ? "Hello cglib!"
        : proxy.invokeSuper(obj, args)
    } as MethodInterceptor

    when:
    SampleJava proxy = enhancer.create()

    then:
    proxy.say_hello_to("world") == "Hello cglib!"
  }

  /**
   * This method only works with CGLIB 3.3.0, but fails on 3.2.6 due to spaces in the target method name
   */
  def "create CGLIB proxy for Groovy class with spaces in method name"() {
    given:
    Enhancer enhancer = new Enhancer()
    enhancer.superclass = SampleGroovy
    enhancer.callback = { obj, method, args, proxy ->
      method.getDeclaringClass() != Object.class && method.getReturnType() == String.class
        ? "Hello cglib!"
        : proxy.invokeSuper(obj, args)
    } as MethodInterceptor

    when:
    SampleGroovy proxy = enhancer.create()

    then:
    proxy."say hello to"("world") == "Hello cglib!"
  }

  def "mock Groovy class with spaces in method name"() {
    given:
    SampleGroovy sampleGroovy = Stub() {
      "say hello to"(_) >> "Hello cglib!"
    }

    expect:
    sampleGroovy."say hello to"("world") == "Hello cglib!"
  }
}
