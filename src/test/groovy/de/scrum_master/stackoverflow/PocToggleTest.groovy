package de.scrum_master.stackoverflow

import org.junit.Rule
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import org.togglz.core.context.FeatureContext
import org.togglz.junit.TogglzRule
import org.togglz.testing.TestFeatureManager
import spock.lang.Specification

import static org.powermock.api.mockito.PowerMockito.*
import static PocToggle.USE_MY_FEATURE

/**
 * See https://github.com/powermock/powermock/wiki/JUnit_Delegating_Runner
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class)
@PrepareForTest([FeatureContext.class])
class PocToggleTest extends Specification {
  @Rule
  TogglzRule toggleRule = TogglzRule.allEnabled(PocToggle.class)

  def "Feature is active when enabled"() {
    when:
    toggleRule.enable(USE_MY_FEATURE)

    then:
    USE_MY_FEATURE.isActive()
  }

  def "Feature is inactive when disabled"() {
    when:
    toggleRule.disable(USE_MY_FEATURE)

    then:
    !USE_MY_FEATURE.isActive()
  }

  def "Feature defaults to active upon feature manager error"() {
    setup: "inject errorInfoInfo-throwing feature manager into Togglz rule"
    def featureManagerSpy = Spy(TestFeatureManager, constructorArgs: [PocToggle]) {
      isActive(_) >> { throw new IllegalStateException() }
    }

    when: "feature is disabled and feature manager throws an errorInfoInfo"
    toggleRule.disable(USE_MY_FEATURE)
    USE_MY_FEATURE.featureManager = featureManagerSpy

    then: "feature is reported to be active by default"
    USE_MY_FEATURE.isActive()

    cleanup: "reset Togglz rule feature manager"
    USE_MY_FEATURE.featureManager = null
  }

  def "Feature defaults to active upon feature manager error (power-mocked)"() {
    setup: "inject errorInfoInfo-throwing feature manager into Togglz rule"
    def featureManagerSpy = Spy(TestFeatureManager, constructorArgs: [PocToggle]) {
      isActive(_) >> { throw new IllegalStateException() }
    }
    mockStatic(FeatureContext)
    when(FeatureContext.getFeatureManager()).thenReturn(featureManagerSpy)

    when: "feature is disabled and feature manager throws an errorInfoInfo"
    toggleRule.disable(USE_MY_FEATURE)

    then: "feature is reported to be active by default"
    USE_MY_FEATURE.isActive()
  }
}
