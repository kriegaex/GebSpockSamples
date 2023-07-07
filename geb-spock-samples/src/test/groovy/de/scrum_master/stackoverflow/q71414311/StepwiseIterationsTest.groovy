package de.scrum_master.stackoverflow.q71414311

import spock.lang.Unroll

class StepwiseIterationsTest extends BaseSpec {
  def setupSpec() {
    println "FailFeatureIfIterationFailsTest.setupSpec"
  }

  def cleanupSpec() {
    println "FailFeatureIfIterationFailsTest.cleanupSpec"
  }

  def setup() {
    println "FailFeatureIfIterationFailsTest.setup: $specificationContext.currentIteration.name"
  }

  def cleanup() {
    println "FailFeatureIfIterationFailsTest.cleanup: $specificationContext.currentIteration.name"
  }

  @Unroll("without skip #count")
  def "run all tests"() {
    expect:
    new UnderTest().isOk(count)

    where:
    count << (1..5)
  }

  @StepwiseIterations
  @Unroll("with skip #count")
  def "run all tests with skip after first failure"() {
    expect:
    new UnderTest().isOk(count)

    where:
    count << (1..5)
  }

  @StepwiseIterations
  def "uprolled run all tests with skip after first failure"() {
    println count
    expect:
    new UnderTest().isOk(count)

    where:
    count << (1..5)
  }

  static class UnderTest {
    boolean isOk(int iteration) {
      if (iteration == 2)
        return false//throw new RuntimeException("uh-oh")
      true
    }
  }

}
