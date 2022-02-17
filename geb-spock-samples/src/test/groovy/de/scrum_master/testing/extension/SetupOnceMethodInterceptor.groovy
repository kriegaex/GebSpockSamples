package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.AbstractMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

class SetupOnceMethodInterceptor extends AbstractMethodInterceptor {
  Map<String, Boolean> annotatedFeatures = new HashMap<>()

  @Override
  void interceptSetupMethod(IMethodInvocation invocation) throws Throwable {
    if (annotatedFeatures.containsKey(invocation.feature.name)) {
      if (!annotatedFeatures[invocation.feature.name]) {
        invocation.proceed()
        annotatedFeatures[invocation.feature.name] = true
      }
    }
    else
      invocation.proceed()
  }
}
