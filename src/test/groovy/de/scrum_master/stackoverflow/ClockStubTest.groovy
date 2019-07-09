package de.scrum_master.stackoverflow

import spock.lang.Specification

import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime

import static java.time.ZoneId.systemDefault

class ClockStubTest extends Specification {
  private static Instant REFERENCE_DATE_TIME = LocalDateTime.of(2018, 4, 1, 10, 0)
    .atZone(systemDefault())
    .toInstant()

  private static Instant OTHER_REFERENCE_DATE_TIME = LocalDateTime.of(2018, 4, 2, 10, 0)
    .atZone(systemDefault())
    .toInstant()

  Clock clock = Stub() {
    instant() >>> [REFERENCE_DATE_TIME, OTHER_REFERENCE_DATE_TIME]
  }

  def "should return different date"() {
    expect:
    clock.instant() == REFERENCE_DATE_TIME
    clock.instant() == OTHER_REFERENCE_DATE_TIME
  }
}
