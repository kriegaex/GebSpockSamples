package de.scrum_master.testing.extension

import geb.spock.GebSpec
import spock.lang.Ignore
import spock.lang.Unroll

@Ignore("test fails on purpose; activate if you want to see screenshots taken via global extension")
class TestFailureGebReportingTest extends GebSpec {
  static url = this.getResource("/simple-form-page.html").toString()

  def "failing normal feature"() {
    given:
    go url

    expect:
    0 == 1
  }

  def "passing normal feature"() {
    given:
    go url

    expect:
    0 == 0
  }

  def "parametrised feature"() {
    given:
    go url

    expect:
    a == b

    where:
    a << [2, 4, 6]
    b << [3, 5, 6]
  }

  @Unroll
  def "unrolled feature with #a/#b"() {
    given:
    go url

    expect:
    a == b

    where:
    a << [6, 8, 0]
    b << [7, 9, 0]
  }
}
