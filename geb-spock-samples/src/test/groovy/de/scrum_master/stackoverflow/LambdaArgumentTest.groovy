package de.scrum_master.stackoverflow

import spock.lang.Specification

import java.util.function.Function

/**
 * See https://stackoverflow.com/questions/52554360
 */
class LambdaArgumentTest extends Specification {
  def anotherObj = Mock(LambdaArgument.AnotherObj)
  def caller = Mock(LambdaArgument.Caller)
  def client = Mock(LambdaArgument.Client)
//  def client = Spy(LambdaArgument.Client)

  def myFeature() {
    given:
    def subject = new LambdaArgument(anotherObj: anotherObj, caller: caller)
    def USER_ID = "dummy"
    def apiResponse = [USER_ID, "hey", "ho"]
//    def apiResponse = [USER_ID, "Two", "Three"]

    when:
    def response = subject.fetchProducts()

    then:
    1 * anotherObj.getId() >> USER_ID
    and:
    1 * caller.call(_) >> { Function lambda ->
      lambda.apply(client)
      return apiResponse
    }
    and:
    1 * client.getProducts(USER_ID)
    and:
    response == apiResponse
  }
}
