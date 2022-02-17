package de.scrum_master.stackoverflow

import spark.Request
import spark.routematch.RouteMatch
import spock.lang.Specification
import spock.lang.Unroll

import javax.servlet.http.HttpServletRequest

class SparkJavaTest extends Specification {
  @Unroll
  def "Request returns expected value for query parameter '#requestUri'"() {
    given:
    def routeMatch = new RouteMatch(null, "/charges/:lastId", requestUri, "text/html")
    def servletRequest = Stub(HttpServletRequest) {
      getParameter("test") >> "test"
    }
    def request = new Request(routeMatch, servletRequest)

    expect:
    request.queryParams("test") == "test"

    where:
    requestUri << ["/charges/1", "/charges/1?test=test"]
  }
}
