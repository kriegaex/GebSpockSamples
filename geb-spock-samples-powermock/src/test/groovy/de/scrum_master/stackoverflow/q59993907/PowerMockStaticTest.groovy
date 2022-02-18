package de.scrum_master.stackoverflow.q59993907

import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Requires
import spock.lang.Specification

import static de.scrum_master.stackoverflow.q59993907.SecondClass.getOrderedValuesBy
import static org.mockito.ArgumentMatchers.anyString
import static org.powermock.api.mockito.PowerMockito.mockStatic
import static org.powermock.api.mockito.PowerMockito.when

// TODO: Configure '--add-opens' and '-Djdk.attach.allowAttachSelf=true' for JDK 16+ in Surefire/Failsafe
@Requires({ Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 16 })
@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
@PrepareForTest(SecondClass)
class PowerMockStaticTest extends Specification {
  static final PrintStream originalSysOut = System.out
  PrintStream mockSysOut = Mock()

  def setup() {
    System.out = mockSysOut
  }

  def cleanup() {
    System.out = originalSysOut
  }

  def "no mock"() {
    when:
    FirstClass.doSomething()

    then:
    1 * mockSysOut.println(['one', 'two', 'three'])
  }

  def "when-thenAnswer"() {
    given:
    mockStatic(SecondClass)
    when(getOrderedValuesBy(anyString())).thenAnswer { ['thenAnswer'] }

    when:
    FirstClass.doSomething()

    then:
    1 * mockSysOut.println(['thenAnswer'])
  }

  def "when-thenReturn"() {
    given:
    mockStatic(SecondClass)
    when(getOrderedValuesBy(anyString())).thenReturn(['thenReturn'])

    when:
    FirstClass.doSomething()

    then:
    1 * mockSysOut.println(['thenReturn'])
  }
}
