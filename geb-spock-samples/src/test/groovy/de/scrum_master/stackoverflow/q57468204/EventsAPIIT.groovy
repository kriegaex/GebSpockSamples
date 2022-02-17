package de.scrum_master.stackoverflow.q57468204


import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import net.sf.json.JSON
import org.apache.log4j.Logger
import org.junit.Ignore
import spock.lang.Specification
import spock.lang.Unroll

@Ignore
class EventsAPIIT extends Specification {
  static Logger logger = Logger.getLogger("EventsAPITest");

  @Unroll
  def "Call calendar event for Invalid Location id 110 throws groovyx.net.http.HttpResponseException"() {
    given:
    RESTClient restClient = new RESTClient("https://test-api2.club-os.com")
    def response = restClient.get(path: '/authenticate', requestContentType: JSON, query: [
      'username': 'apiuser',
      'password': 'apipassword',
    ])
    def authToken = response.data.token
    logger.info "Token:" + authToken
    restClient.defaultRequestHeaders['Authorization'] = authToken
    def locationId = 110

    when:
    restClient.get(path: '/locations/' + locationId + '/events', requestContentType: JSON, query: [
      'startTimeStartAt': '2018-05-25T17:00:00.000Z',
      'startTimeEndAt'  : '2018-06-01T17:00:00.000Z',
      'eventTypeId'     : 2])

    then:
    def exception = thrown HttpResponseException
    exception.message == "An unknown error occurred"

    logger.info "Response: " + exception.response
    exception.response.status == 500
  }
}
