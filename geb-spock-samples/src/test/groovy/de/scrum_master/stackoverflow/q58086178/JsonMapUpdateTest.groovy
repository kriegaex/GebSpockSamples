package de.scrum_master.stackoverflow.q58086178

import spock.lang.Specification
import spock.lang.Unroll

class JsonMapUpdateTest extends Specification {
  def "groovy map update question"() {
    setup: 'create json'
    Map json = [user: [name: 'ABC']]

    when: 'update it'
    json.with {
      user.name = value // this one works
      //(field) = value   // this one does not work
    }

    then: 'check the assignment'
    println json
    json.user.name == value

    where:
    field       | value
    'user.name' | 'XYZ'
  }

  def "groovy map update answer"() {
    setup: 'create json'
    Map json = [user: [name: 'ABC']]

    when: 'update it'
    def target = json
    for (node in path - path.last()) {
      target = target[node]
    }
    target[path.last()] = value

    then: 'check the assignment'
    println json
    json.user.name == value

    where:
    path             | value
    ['user', 'name'] | 'XYZ'
  }

  @Unroll
  def "set #field to #value"() {
    setup: 'create json'
    Map json = [user: [name: 'ABC', address: [street: '21 Main St', zip: '12345', city: 'Hometown']]]

    when: 'update it'
    field.split("[.]").inject(json) { subMap, key ->
      subMap[key] instanceof Map ? subMap[key] : subMap.put(key, value)
    }
    println json

    then: 'check the assignment'
    json.newField == value ||
      json.user.name == value ||
      json.user.address.zip == value ||
      json.user.address.country == value ||
      json.user.address == value ||
      json.user.alternativeAddress == value

    where:
    field                     | value
    'newField'                | 'dummy'
    'user.name'               | 'XYZ'
    'user.address.zip'        | '98765'
    'user.address.country'    | 'Germany'
    'user.alternativeAddress' | [street: '23 Test Blvd', zip: '33333', city: 'Somewhere']
//    'user.address'            | [street: '23 Test Blvd', zip: '33333', city: 'Somewhere']
//    'user.address'            | '23 Test Blvd, 33333 Somewhere'
  }

}
