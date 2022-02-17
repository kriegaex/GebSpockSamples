package de.scrum_master.testing.extension

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

@Ignore("test fails on purpose; activate if you want to see special error output")
class TestFailureReportingTest extends Specification {
  def "failing normal feature"() {
    expect:
    0 == 1
  }

  def "passing normal feature"() {
    expect:
    0 == 0
  }

  def "parametrised feature"() {
    expect:
    a == b

    where:
    a << [2, 4, 6]
    b << [3, 5, 6]
  }

  @Unroll
  def "unrolled feature with #a/#b"() {
    expect:
    a == b

    where:
    a << [6, 8, 0]
    b << [7, 9, 0]
  }

  def cleanup() {
    specificationContext.currentSpec.listeners
      .findAll { it instanceof TestResultExtension.ErrorListener }
      .each {
        def errorInfo = (it as TestResultExtension.ErrorListener).errorInfo
        if (errorInfo)
          println "Test failure in feature '${specificationContext.currentIteration.name}', " +
            "exception class ${errorInfo.exception.class.simpleName}"
        else
          println "Test passed in feature '${specificationContext.currentIteration.name}'"
      }
  }
}
