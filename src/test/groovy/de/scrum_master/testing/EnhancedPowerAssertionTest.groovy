package de.scrum_master.testing

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.Specification

/**
 * This is an ugly workaround for https://github.com/spockframework/spock/issues/922
 */
class EnhancedPowerAssertionTest extends Specification {
  static class PowerAssertionEnhancer {
    private static final String DASHED_LINE = "=" * 60

    static boolean specialAssert(String extraMessage, Closure assertion) {
      try { assertion() }
      catch(ConditionNotSatisfiedError e) {
        System.err.println DASHED_LINE
        System.err.println extraMessage
        System.err.println DASHED_LINE
        throw e
      }
      true
    }
  }

  def setupSpec() {
    getClass().mixin PowerAssertionEnhancer
  }

  def myFeature() {
    expect:
    specialAssert "This is some additional info about the error", {
      assert ("one".capitalize() * 3).toUpperCase() == "OneOneOne"
    }
  }
}
