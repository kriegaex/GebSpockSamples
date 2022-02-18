package de.scrum_master.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ ClassCallingStaticSystemMethods.class })
public class PowermockStaticMockJUnitTest {
  private static final boolean JAVA_8_OR_LOWER = Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 9;

  @Test
  public void mySqlDateTest_whenThenReturn() {
    assumeTrue(JAVA_8_OR_LOWER);
    Date mockDate = new Date(1971 - 1900, 5 - 1, 8);
    mockStatic(Date.class);
    when(Date.valueOf(anyString())).thenReturn(mockDate);
    Date date = new ClassCallingStaticSystemMethods().genDate("1998-12-20");
    // Static mocking works for system (JDK, JRE) classes if the class *using* the
    // system class is present in @PrepareForTest instead of the system class itself
    assertEquals(mockDate, date);
    // Static verification works with system (JDK, JRE) classes, too
    verifyStatic(Date.class, times(1));
    Date.valueOf(anyString());
  }

  @Test
  public void mySqlDateTest_whenThenAnswer() {
    assumeTrue(JAVA_8_OR_LOWER);
    Date mockDate = new Date(1971 - 1900, 5 - 1, 8);
    mockStatic(Date.class);
    when(Date.valueOf(anyString())).thenAnswer((Answer<Date>) invocation -> {
      Object[] args = invocation.getArguments();
      Method method = invocation.getMethod();
      System.out.println(method.toString() + " called with arguments: " + Arrays.toString(args));
      return mockDate;
    });
    Date date = new ClassCallingStaticSystemMethods().genDate("1998-12-20");
    // Static mocking works for system (JDK, JRE) classes if the class *using* the
    // system class is present in @PrepareForTest instead of the system class itself
    assertEquals(mockDate, date);
    // Static verification works with system (JDK, JRE) classes, too
    verifyStatic(Date.class, times(1));
    Date.valueOf(anyString());
  }

  @Test
  public void testDriverManager() throws Exception {
    assumeTrue(JAVA_8_OR_LOWER);
    mockStatic(DriverManager.class);
    when(DriverManager.getLoginTimeout()).thenReturn(111);
    assertEquals(111, DriverManager.getLoginTimeout());
    verifyStatic(DriverManager.class, times(1));
    DriverManager.getLoginTimeout();
  }

  @Test
  public void assertThatMockingOfCollectionsWork() throws Exception {
    List<?> list = new LinkedList<Object>();
    mockStatic(Collections.class);

    Collections.shuffle(list);
    new ClassCallingStaticSystemMethods().shuffleCollection(list);

    verifyStatic(Collections.class, times(2));
    Collections.shuffle(list);
  }
}
