package de.scrum_master.testing

import spock.lang.See
import spock.lang.Specification


class GroovyDelegateTest extends Specification {
  def count = 0

  def info() {
    "Count value is $count"
  }

  def printInfo = {
    count++
    info()
  }

  @See("http://mrhaki.blogspot.com/2009/11/groovy-goodness-setting-closures.html")
  def "Change Groovy closure delegate to be Java class instance"() {
    expect: "delegate is by default set to owner, i.e. the class in this case"
    "Count value is 1" == printInfo()

    when: "changing closure resolver to use delegate first"
    printInfo.resolveStrategy = Closure.DELEGATE_FIRST

    and: "changing delegate"
    printInfo.delegate = new Post(count: 100)

    then: "result really comes from delegate object"
    "This is Groovy Goodness post #101" == printInfo()

    when: "changing closure resolver back to use owner first"
    printInfo.resolveStrategy = Closure.OWNER_FIRST

    then: "result comes from closure again"
    "Count value is 2" == printInfo()
  }
}
