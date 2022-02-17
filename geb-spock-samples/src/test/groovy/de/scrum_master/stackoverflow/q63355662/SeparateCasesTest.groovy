package de.scrum_master.stackoverflow.q63355662

import spock.lang.Specification
import spock.lang.Unroll

class SeparateCasesTest extends Specification {
  int[] randomOnCondition(int input) {
    def output = input % 2 ? Random.newInstance().nextInt() : input
    [input, output]
  }

  @Unroll
  def "predictable output for input #input"() {
    expect:
    randomOnCondition(input) == output

    where:
    input || output
    2     || [2, 2]
    4     || [4, 4]
    6     || [6, 6]
  }

  @Unroll
  def "partly unpredictable output for input #input"() {
    expect:
    randomOnCondition(input)[0] == firstOutputElement

    where:
    input || firstOutputElement
    1     || 1
    3     || 3
    5     || 5
  }
}
