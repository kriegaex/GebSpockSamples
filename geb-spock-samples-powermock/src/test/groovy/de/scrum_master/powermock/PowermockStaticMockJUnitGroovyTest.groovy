package de.scrum_master.powermock

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import spock.lang.Specification

import java.lang.reflect.Method
import java.sql.Date
import java.sql.DriverManager
import java.sql.Timestamp

import static org.junit.Assert.assertEquals
import static org.junit.Assume.assumeTrue
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.times
import static org.powermock.api.mockito.PowerMockito.*

@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(JUnit4)
@PrepareForTest([ClassCallingStaticSystemMethods])
@CompileStatic
class PowermockStaticMockJUnitGroovyTest extends Specification {
  // TODO: PowerMock cannpr prepare classes for test on JDK 9+ which it can on 8
  private static final boolean JAVA_8_OR_LOWER = Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 9

  @Test
  void mySqlDateTest_whenThenReturn() {
    assumeTrue(JAVA_8_OR_LOWER)
    Date mockDate = new Date(1971 - 1900, 5 - 1, 8)
    mockStatic(Date.class)
    when(Date.valueOf(anyString())).thenReturn(mockDate)
    Date date = new ClassCallingStaticSystemMethods().genDate("1998-12-20")
    // Static mocking works for system (JDK, JRE) classes if the class *using* the
    // system class is present in @PrepareForTest instead of the system class itself
    assertEquals(mockDate, date)
    // Static verification works with system (JDK, JRE) classes, too
    verifyStatic(Date.class, times(1))
    Date.valueOf(anyString())
  }

  @Test
  void mySqlDateTest_whenThenAnswer() {
    assumeTrue(JAVA_8_OR_LOWER)
    Date mockDate = new Date(1971 - 1900, 5 - 1, 8)
    mockStatic(Date.class)
    when(Date.valueOf(anyString())).thenAnswer(new Answer<Date>() {
      @Override
      Date answer(InvocationOnMock invocation) throws Throwable {
        Object[] args = invocation.getArguments()
        Method method = invocation.getMethod()
        System.out.println(method.toString() + " called with arguments: " + Arrays.toString(args))
        return mockDate
      }
    })
    Date date = new ClassCallingStaticSystemMethods().genDate("1998-12-20")
    // Static mocking works for system (JDK, JRE) classes if the class *using* the
    // system class is present in @PrepareForTest instead of the system class itself
    assertEquals(mockDate, date)
    // Static verification works with system (JDK, JRE) classes, too
    verifyStatic(Date.class, times(1))
    Date.valueOf(anyString())
  }

  @Test
  void mySqlTimeStampTest() {
    assumeTrue(JAVA_8_OR_LOWER)
    Timestamp mockTimestamp = new Timestamp(1971 - 1900, 5 - 1, 8, 11, 15, 0, 0)
    mockStatic(Timestamp.class)
    when(Timestamp.valueOf(anyString())).thenReturn(mockTimestamp)
//    stub(method(Timestamp, "valueOf", String)).toReturn(mockTimestamp)
    Timestamp timestamp = new ClassCallingStaticSystemMethods().genTimestamp("1998-12-20")
    // Static mocking works for system (JDK, JRE) classes if the class *using* the
    // system class is present in @PrepareForTest instead of the system class itself
    assertEquals(mockTimestamp, timestamp)
    // Static verification works with system (JDK, JRE) classes, too
    verifyStatic(Timestamp.class, times(1))
    Timestamp.valueOf(anyString())
  }

  @Test
  void assertThatMockingOfCollectionsWork() throws Exception {
    List<?> list = new LinkedList<Object>()
    mockStatic(Collections.class)

    Collections.shuffle(list)
    new ClassCallingStaticSystemMethods().shuffleCollection(list)

    verifyStatic(Collections.class, times(2))
    Collections.shuffle(list)
  }

  @Test
  void testDriverManager() throws Exception {
    assumeTrue(JAVA_8_OR_LOWER)
    mockStatic(DriverManager.class)
    when(DriverManager.getLoginTimeout()).thenReturn(111)
    assertEquals(111, DriverManager.getLoginTimeout())
    verifyStatic(DriverManager.class, times(1))
    DriverManager.getLoginTimeout()
  }
}
