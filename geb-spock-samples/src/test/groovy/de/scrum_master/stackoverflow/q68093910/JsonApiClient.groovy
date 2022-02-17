package de.scrum_master.stackoverflow.q68093910

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator

import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.POST

class JsonApiClient {
  HTTPBuilder http = new HTTPBuilder("https://jsonplaceholder.typicode.com")
  String PATH = "/users"
  String novaAuthentication = ''
  String respondentString = ''
  String autoCreditResponse = ''
  int statusCode
  JsonSlurper jsonSlurper = new JsonSlurper()

  void addRespondents() {
    http.request(POST, TEXT) {
      uri.path = PATH
      headers.Cookie = novaAuthentication
      headers.Accept = 'application/json'
      headers.ContentType = 'application/json'
      body = respondentString
      response.success = { resp, json ->
        println "Success -> ${jsonSlurper.parse(json)}"
        statusCode = 2
      }

      response.failure = { resp, json ->
        if (resp.status == 400) {
          println "Error 400 -> ${jsonSlurper.parse(json)}"
          statusCode = 3
        }
        else {
          println "Other error -> ${jsonSlurper.parse(json)}"
          autoCreditResponse = createErrorResponse(resp)
        }
      }
    }
  }
  
  String createErrorResponse(HttpResponseDecorator responseDecorator) {
    "ERROR"
  }
}
