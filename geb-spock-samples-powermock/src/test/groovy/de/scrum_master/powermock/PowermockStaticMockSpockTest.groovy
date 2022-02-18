package de.scrum_master.powermock

import groovy.transform.CompileStatic
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Requires
import spock.lang.Specification

import java.sql.Date

import static de.scrum_master.powermock.PowermockStaticMockSpockTest.PowerMockHelper.testSQLDate_mockStatic
import static de.scrum_master.powermock.PowermockStaticMockSpockTest.PowerMockHelper.testSQLDate_verifyStatic
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.times
import static org.powermock.api.mockito.PowerMockito.*
import static org.powermock.api.support.membermodification.MemberMatcher.method
import static org.powermock.api.support.membermodification.MemberModifier.stub

@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
@PrepareForTest(ClassCallingStaticSystemMethods)
class PowermockStaticMockSpockTest extends Specification {
  def testUUID() {
    given:
    def id = "493410b3-dd0b-4b78-97bf-289f50f6e74f"
    UUID uuid = UUID.fromString(id)
    stub(method(UUID, "randomUUID")).toReturn(uuid)

    expect:
    new ClassCallingStaticSystemMethods().genUUID() == id
  }

  @Requires({ Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 9 })
  def testSQLDate() {
    given:
    def mockDate = new Date(1971 - 1900, 5 - 1, 8)

    when:
    testSQLDate_mockStatic(mockDate)
    def date = new ClassCallingStaticSystemMethods().genDate("1998-12-20")

    then:
    testSQLDate_verifyStatic()
    mockDate == date
  }

  @CompileStatic
  static class PowerMockHelper {
    def static testSQLDate_mockStatic(Date mockDate) {
      mockStatic(Date)
      when(Date.valueOf(anyString())).thenReturn(mockDate)
    }

    def static testSQLDate_verifyStatic() {
      verifyStatic(Date, times(1))
      Date.valueOf(anyString())
      true
    }
  }
}
