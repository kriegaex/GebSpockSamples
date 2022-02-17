package de.scrum_master.stackoverflow

import spock.lang.Specification
import spock.lang.Unroll

/**
 * See https://stackoverflow.com/q/48410722/1082681
 */
class PseudoExcelTableTest extends Specification {
  @Unroll//("For #calcToCheck.tr_date_class")
  def "I check flag value #calcToCheck.tr_date"(CalculationClass calcToCheck, int flag) {
    expect:
    flag == calcToCheck.result

    where:
    calcToCheck << calInputParameters()
    flag << [11, 22, 33]
  }

  def calInputParameters() {
    def calcsToCheck = new ArrayList<CalculationClass>()
    calcsToCheck.addAll(
      new CalculationClass(result: 11, tr_date: "eleven", tr_date_class: "short"),
      new CalculationClass(result: 22, tr_date: "twenty-two", tr_date_class: "long"),
      new CalculationClass(result: 33, tr_date: "thirty-three", tr_date_class: "normal")
    )
    return calcsToCheck
  }

  static class CalculationClass {
    int result
    String tr_date
    String tr_date_class
  }
}
