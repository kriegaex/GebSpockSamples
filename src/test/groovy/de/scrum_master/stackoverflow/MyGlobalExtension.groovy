package de.scrum_master.stackoverflow

import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.IterationInfo
import org.spockframework.runtime.model.SpecInfo

class MyGlobalExtension extends AbstractGlobalExtension {
  @Override
  void visitSpec(SpecInfo spec) {
    spec.addListener(new ErrorListener())
  }

  static class ErrorListener extends AbstractRunListener {
    ErrorInfo errorInfo

    @Override
    void beforeFeature(FeatureInfo feature) {
      errorInfo = null
    }

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
