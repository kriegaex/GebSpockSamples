package de.scrum_master.stackoverflow.q65321086

import dev.sarek.agent.mock.MockFactory
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDate
import java.time.Year

import static java.time.Month.JANUARY
import static net.bytebuddy.matcher.ElementMatchers.named

class SomeClassTest extends Specification {
  @Subject
  SomeClass classUnderTest = new SomeClass()

  @Unroll
  def "override static JRE method Year.now() for #year"() {
    setup:
    MockFactory<Year> mockFactory = MockFactory
      .forClass(Year.class)
      .mockStatic(
        named("now"),
        { method, args -> false },
        { method, args, proceedMode, returnValue, throwable -> Year.of(year) }
      )
      .build()

    expect:
    classUnderTest.methodUnderTest() == expectedDate

    cleanup:
    mockFactory.close()

    where:
    year || expectedDate
    2019 || LocalDate.of(2019, JANUARY, 1)
    2020 || LocalDate.of(2020, JANUARY, 1)
    2021 || LocalDate.of(2021, JANUARY, 1)
  }
}
