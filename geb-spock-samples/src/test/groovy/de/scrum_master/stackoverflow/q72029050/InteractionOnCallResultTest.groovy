package de.scrum_master.stackoverflow.q72029050

import spock.lang.Specification

class InteractionOnCallResultTest extends Specification {
  def "dependency method is called with the expected argument"() {
    given:
    def dependency = Mock(Dependency)
    def randomNumber
    def dependencyMethodArg

    when:
    randomNumber = new UnderTest().getRandomNumber(dependency)

    then:
    1 * dependency.dependencyMethod(_) >> { args -> dependencyMethodArg = args[0] }
    dependencyMethodArg == randomNumber
  }
}

interface Dependency {
  void dependencyMethod(double x)
}

class UnderTest {
  double getRandomNumber(Dependency dependency) {
    double randomNumber = Math.random()
    dependency.dependencyMethod(randomNumber)
    return randomNumber
  }
}
