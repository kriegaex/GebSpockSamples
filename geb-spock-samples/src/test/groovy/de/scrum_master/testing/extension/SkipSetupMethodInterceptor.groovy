package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.AbstractMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

class SkipSetupMethodInterceptor extends AbstractMethodInterceptor {
  List<String> skippedFeatures = new LinkedList<>()

  @Override
  void interceptSetupMethod(IMethodInvocation invocation) throws Throwable {
    if (!skippedFeatures.contains(invocation.feature.name))
      invocation.proceed()
  }
}
