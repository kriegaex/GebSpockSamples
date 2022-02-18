package de.scrum_master.stackoverflow.q55101703

import groovy.transform.CompileStatic
import org.junit.runner.RunWith
import org.mockito.exceptions.base.MockitoException
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

import static de.scrum_master.stackoverflow.q55101703.EncryptTest.PowerMockHelper.testUUID_mockStatic
import static de.scrum_master.stackoverflow.q55101703.EncryptTest.PowerMockHelper.testUUID_verifyStatic
import static org.mockito.Mockito.times
import static org.powermock.api.mockito.PowerMockito.mockStatic
import static org.powermock.api.mockito.PowerMockito.verifyStatic
import static org.powermock.api.support.membermodification.MemberMatcher.method
import static org.powermock.api.support.membermodification.MemberModifier.stub

@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
@PrepareForTest([Encrypt])
class EncryptTest extends Specification {

  def testUUID() {
    given:
    def id = "493410b3-dd0b-4b78-97bf-289f50f6e74f"
    UUID uuid = UUID.fromString(id)
    testUUID_mockStatic(uuid)

    expect:
    new Encrypt().genUUID() == id
    // This does not work because UUID is final. Otherwise it would work.

    when:
    testUUID_verifyStatic()

    then:
    def exception = thrown MockitoException
    exception.message =~ /Cannot mock.*UUID/
    exception.message =~ /final class/
  }

  @CompileStatic
  static class PowerMockHelper {
    def static testUUID_mockStatic(UUID uuid) {
      mockStatic(UUID)
      stub(method(UUID, "randomUUID")).toReturn(uuid)
//      when(UUID.randomUUID()).thenReturn(uuid)
      // Use 'thenAnswer' instead of 'thenReturn' for side effect or complex calculation
      // when(UUID.randomUUID()).thenAnswer { println "stub static method randomUUID()"; uuid }
    }

    def static testUUID_verifyStatic() {
      // This does not work for final classes, at least not with Mockito back-end.
      // Maybe there is a way with EasyMock. The documentation at least mentions
      // mocking final classes for EasyMock, but I only saw an example with non-static
      // methods.
      //
      // Executing the following line yields this error:
      // org.mockito.exceptions.base.MockitoException:
      //   Cannot mock/spy class java.util.UUID
      //   Mockito cannot mock/spy because :
      //     - final class
      verifyStatic(UUID, times(1))
      UUID.randomUUID()
      true
    }
  }
}
