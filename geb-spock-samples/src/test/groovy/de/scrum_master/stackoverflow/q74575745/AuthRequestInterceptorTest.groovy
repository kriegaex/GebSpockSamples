package de.scrum_master.stackoverflow.q74575745

import okhttp3.Interceptor
import okhttp3.Request
import spock.lang.IgnoreIf
import spock.lang.Specification

class AuthRequestInterceptorTest extends Specification {
//  @IgnoreIf({ println 'reason: back-end server unavailable on Sundays'; true })
  def "request contains authorization header"() {
    given: "a mock interceptor chain returning a prepared request without headers"
    def chain = Mock(Interceptor.Chain) {
      request() >> new Request.Builder()
        .url("http://1.1.1.1/heath-check")
        .build()
    }

    when: "running the interceptor under test"
    new AuthRequestInterceptor().intercept(chain)

    then: "the expected authorization header is added to the request before proceeding"
    1 * chain.proceed({ Request request -> request.headers("Authorization") == ["auth-value"] })
  }

  def dummy() {
    org.junit.Assume.assumeFalse('back-end server unavailable on Sundays', true)

    expect:
    true
  }
}
