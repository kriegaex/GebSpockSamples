package com.twg.sample.model

import spock.lang.Specification

class PrimeNumberCalculatorTest extends Specification {
  def "test prime numbers"() {
    given:
    def primeCal = new PrimeNumberCalculator()
    expect:
    [1, 2, 3, 5, 7] == primeCal.getPrimeNumbers(9).toList()
  }
}
