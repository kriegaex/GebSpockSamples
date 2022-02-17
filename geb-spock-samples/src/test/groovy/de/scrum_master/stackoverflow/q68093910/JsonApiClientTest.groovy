package de.scrum_master.stackoverflow.q68093910

import groovyx.net.http.HTTPBuilder
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.ResponseHandler
import org.apache.http.entity.StringEntity
import org.apache.http.message.BasicHttpResponse
import org.apache.http.message.BasicStatusLine
import spock.lang.Specification
import spock.lang.Unroll

import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.POST
import static org.apache.http.HttpVersion.HTTP_1_1

class JsonApiClientTest extends Specification {
  @Unroll
  def "verify status code #statusCode"() {
    given: "a JSON response"
    HttpResponse response = new BasicHttpResponse(
      new BasicStatusLine(HTTP_1_1, statusCode, "my reason")
    )
    def json = "{ \"name\" : \"JSON-$statusCode\" }"
    response.setEntity(new StringEntity(json))

    and: "a mock HTTP client returning the JSON response"
    HttpClient httpClient = Mock() {
      execute(_, _ as ResponseHandler, _) >> { List args ->
        (args[1] as ResponseHandler).handleResponse(response)
      }
    }

    and: "an HTTP builder spy using the mock HTTP client"
    HTTPBuilder httpBuilder = Spy(constructorArgs: ["https://foo.bar"])
    httpBuilder.setClient(httpClient)

    and: "a JSON API client using the HTTP builder spy"
    def builderUser = new JsonApiClient(http: httpBuilder)
    def actualResult

    when: "calling 'addRespondents'"
    builderUser.addRespondents()

    then: "'HTTPBuilder.request' was called as expected"
    1 * httpBuilder.request(POST, TEXT, _) >> {
      actualResult = callRealMethod()
    }
    actualResult == expectedResult

    where:
    statusCode | expectedResult
    200        | 2
    400        | 3
    404        | "ERROR"
  }
}
