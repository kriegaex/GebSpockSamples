package de.scrum_master.stackoverflow.q52952222

import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Requires
import spock.lang.Specification

import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.times
import static org.powermock.api.mockito.PowerMockito.*

// TODO: Configure '--add-opens' and '-Djdk.attach.allowAttachSelf=true' for JDK 16+ in Surefire/Failsafe
@Requires({ Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 16 })
@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
@PrepareForTest([Tester])
class MySpockTest extends Specification {
  def test() {
    given:
    // Tell PowerMockito that we want to mock static methods in this class
    mockStatic(Tester)
    // Stub return value for static method call
    when(Tester.sendFaqRequest(anyString(), anyString())).thenAnswer(new FirstResponseWithText())
    // Call method which calls our static method 3x
    OtherClass.methodThatCallsTesterStaticMethod("", "", "", false, "")
    // Verify that Tester.sendFaqRequest was called 3x
    verifyStatic(Tester, times(3))
    Tester.sendFaqRequest(anyString(), anyString())

    expect:
    true
  }
}
