package de.scrum_master.gitter.spock.d20210128

import de.scrum_master.testing.ClassLoaderChecker
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Requires
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Requires({
  // Currently there are PowerMockito tests in the same module with this one requiring inline mocking.
  // In order to run this test, you have to comment out PowerMock dependencies and all tests using them
  // or configure the IDE not to perform a build before running this test.
  boolean inlineMockMakerActive = ClassThatLogsTest.classLoader
    .getResourceAsStream("mockito-extensions/org.mockito.plugins.MockMaker")
    .readLines()[0]
    .contains("mock-maker-inline")
  String classUnderTestName = "de.scrum_master.gitter.spock.d20210128.ClassThatLogs"
  boolean classUnderTestLoaded = ClassLoaderChecker.isClassLoaded(classUnderTestName)
  println "inlineMockMakerActive = $inlineMockMakerActive"
  println "classUnderTestLoaded = $classUnderTestLoaded"
  return inlineMockMakerActive && !classUnderTestLoaded
})
class ClassThatLogsTest extends Specification {
  @Shared
  DelegatingLogger delegatingLogger = new DelegatingLogger()
  @Shared
  MockedStatic<LoggerFactory> loggerFactoryMockitoMock = Mockito.mockStatic(LoggerFactory)

  def setupSpec() {
    loggerFactoryMockitoMock
      .when(LoggerFactory.getLogger(ClassThatLogs) as MockedStatic.Verification)
      .thenReturn(delegatingLogger)
  }

  def cleanupSpec() {
    loggerFactoryMockitoMock.close()
  }

  @Unroll
  def "Vulcan greeting for #person"() {
    given:
    Logger loggerMock = Mock()
    delegatingLogger.logger = loggerMock

    when:
    new ClassThatLogs().logSomethingWithInfo(person)

    then:
    1 * loggerMock.info("Live long and prosper, $person")

    where:
    person << ["Jim", "Scottie", "Bones"]
  }

  static class DelegatingLogger implements Logger {
    @Delegate
    Logger logger
  }
}
