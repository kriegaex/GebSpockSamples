package de.scrum_master.stackoverflow.q58101434

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import spock.lang.Requires
import spock.lang.Specification
import spock.lang.Unroll

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.times
import static org.mockito.Mockito.when
import static org.powermock.api.mockito.PowerMockito.*
import static org.powermock.api.support.membermodification.MemberMatcher.method
import static org.powermock.api.support.membermodification.MemberModifier.stub

// TODO: Configure '--add-opens' and '-Djdk.attach.allowAttachSelf=true' for JDK 16+ in Surefire/Failsafe
@Requires({ Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 16 })
@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
// Avoid java.lang.IllegalAccessError:
//   class javax.xml.parsers.FactoryFinder (in unnamed module @0x4c51cf28)
//   cannot access class jdk.xml.internal.SecuritySupport (in module java.xml)
// See https://github.com/mockito/mockito/issues/1562#issuecomment-481108443
@PowerMockIgnore(["javax.management.*", "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"])
@PrepareForTest([RequestEntity])
class MyRestApiTest extends Specification {
  @Unroll
  def "API returns status code #statusCode"() {
    given: "prepare mocks + spy"
    RequestEntity<String> requestEntity = Mock()
    ResponseEntity<String> responseEntity = Mock() {
      getStatusCode() >> httpStatus
    }
    MyRestApi myRestApi = Spy() {
      createRequestEntity(_, _) >> requestEntity
      executeRequest(_, _) >> responseEntity
    }

    when: "execute API method"
    def result = myRestApi.api()

    then: "check expected results"
    // This actually only tests mock functionality, your real test would look differently
    statusCode == result.value()
    reasonPhrase == result.reasonPhrase

    where:
    httpStatus                   | statusCode | reasonPhrase
    HttpStatus.OK                | 200        | "OK"
    HttpStatus.MOVED_PERMANENTLY | 301        | "Moved Permanently"
    HttpStatus.UNAUTHORIZED      | 401        | "Unauthorized"
  }

  /**
   * PowerMockito: how to stub a static method via 'mockStatic-when-thenReturn'
   *
   *   1) mockStatic(StaticClass)
   *   2) when(StaticClass.staticMethod(any(ParameterClass))).thenReturn(result)
   *   3) (in-)direct static method call
   *   4) check result
   */
  def "create request entity, stub via mockStatic-when-thenReturn"() {
    given:
    def url = "http://localhost/dummy"
    def bodyBuilder = RequestEntity.put(new URI(url))
    mockStatic(RequestEntity)
    when(RequestEntity.put(any(URI))).thenReturn(bodyBuilder)

    when: "execute API method"
    def requestEntity = new MyRestApi().createRequestEntity("my-url", "my-body")

    then: "check expected results"
    url == requestEntity.url.toString()
  }

  /**
   * PowerMockito: how to stub a static method via 'stub(Method).toReturn(result)'
   *
   *   1) stub(Method).toReturn(result)
   *   2) (in-)direct static method call
   *   3) check result
   *
   * This is the more exotic variant needed when trying to stub JDK classes from Spock
   */
  def "create request entity, stub via stub-toReturn"() {
    given:
    def url = "http://localhost/dummy"
    def bodyBuilder = RequestEntity.put(new URI(url))
    stub(method(RequestEntity, "put", URI)).toReturn(bodyBuilder)

    when: "execute API method"
    def requestEntity = new MyRestApi().createRequestEntity("my-url", "my-body")

    then: "check expected results"
    url == requestEntity.url.toString()
  }

  /**
   * PowerMockito: how to verify a static method call via 'spy-verifyStatic'
   *
   *   1) spy(StaticClass) -> because original method is not stubbed
   *   2) (in-)direct static method call
   *   3) verifyStatic(StaticClass, [times])
   *   4) repeat method call to be verified -> unintuitive, yet important!
   *   5) check result
   */
  def "create request entity, verify static method call"() {
    given:
    def url = "my-url"
    spy(RequestEntity)

    when: "execute API method"
    def requestEntity = new MyRestApi().createRequestEntity url, "my-body"
    verifyStatic(RequestEntity, times(1))
    RequestEntity.put(any(URI))

    then: "check expected results"
    url == requestEntity.url.toString()
  }

  /**
   * PowerMockito: how to combine static stub + verification
   *
   *   1) mockStatic(StaticClass)
   *   2) when(StaticClass.staticMethod(any(ParameterClass))).thenReturn(result)
   *   3) (in-)direct static method call
   *   4) verifyStatic(StaticClass, [times])
   *   5) repeat method call to be verified -> unintuitive, yet important!
   *   6) check result
   */
  def "create request entity, stub + verify static method call"() {
    given:
    def url = "http://localhost/dummy"
    def bodyBuilder = RequestEntity.put(new URI(url))
    mockStatic(RequestEntity)
    when(RequestEntity.put(any(URI))).thenReturn(bodyBuilder)

    when: "execute API method"
    def requestEntity = new MyRestApi().createRequestEntity("my-url", "my-body")
    verifyStatic(RequestEntity, times(1))
    RequestEntity.put(any(URI))

    then: "check expected results"
    url == requestEntity.url.toString()
  }
}
