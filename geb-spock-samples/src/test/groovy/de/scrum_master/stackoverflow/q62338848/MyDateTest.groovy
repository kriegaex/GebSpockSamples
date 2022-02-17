package de.scrum_master.stackoverflow.q62338848

import spock.lang.Specification

import java.sql.Date
import java.text.SimpleDateFormat

class MyDateTest extends Specification {
  def test() {
    given:
    def date = new java.sql.Date(2020 - 1900, 4, 1)
    def newNotification = Mock(TaskNotification)
    def underTest = new UnderTest(taskNotification: newNotification)
    def expectedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-01")

    when:
    underTest.doSomething(date)

    then:
    1 * newNotification.setTasDeadlineDat(expectedDate)
    1 * newNotification.setTasDeadlineToDat(expectedDate)
  }

  static class UnderTest {
    TaskNotification taskNotification

    void doSomething(Date date) {
      Calendar c = Calendar.getInstance()
      c.setTime(date)
      def months = 2
      c.add(Calendar.MONTH, -1 * (months + 1))
      taskNotification.setTasDeadlineDat(new java.sql.Date(c.getTime().getTime()))
      taskNotification.setTasDeadlineToDat(new java.sql.Date(c.getTime().getTime()))
    }
  }

}
