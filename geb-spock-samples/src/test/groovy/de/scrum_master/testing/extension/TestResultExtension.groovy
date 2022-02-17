package de.scrum_master.testing.extension

import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.IterationInfo
import org.spockframework.runtime.model.SpecInfo

/**
 * See https://stackoverflow.com/a/50679606/1082681
 */
class TestResultExtension extends AbstractGlobalExtension {
  @Override
  void visitSpec(SpecInfo spec) {
    spec.addListener(new ErrorListener())
  }

  static class ErrorListener extends AbstractRunListener {
    ErrorInfo errorInfo

    @Override
    void beforeIteration(IterationInfo iteration) {
      errorInfo = null
    }

    @Override
    void error(ErrorInfo error) {
      errorInfo = error
    }
  }

}
