package de.scrum_master.stackoverflow.q55914920

import spock.lang.Specification

class ServiceClassTest extends Specification {
  ServiceClass serviceClass = new ServiceClass()

  def "test doSomething method"(){
    given:
    String id = "id"
    def cacheService = Mock(CacheService)
    def obj = Mock(CacheObj)
    cacheService.get(_) >> obj
    obj.getValue("thisID") >> "test"  //stubbing this to return test

    when:
    //calling dosomething() method of service class
    cacheService.doSomething(id)

    then:
    //checking assertions here
    true
  }
}
