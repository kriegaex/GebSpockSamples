package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

class SkipSetupGlobalMethodInterceptor implements IMethodInterceptor {
  @Override
  void intercept(IMethodInvocation invocation) throws Throwable {
    if (!invocation.feature.featureMethod.getAnnotation(SkipSetup))
      invocation.proceed()
  }
}
