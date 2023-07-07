package de.scrum_master.stackoverflow.q71414311

import org.junit.AssumptionViolatedException
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class StepwiseIterationsExtension extends AbstractAnnotationDrivenExtension<StepwiseIterations> {
  @Override
  void visitFeatureAnnotation(StepwiseIterations annotation, FeatureInfo feature) {
    SkipFeatureInterceptor skipFeatureInterceptor = new SkipFeatureInterceptor()
    SpecInfo spec = feature.spec
    // If an error occurs in this feature, skip remaining iterations
    feature.getFeatureMethod().addInterceptor(skipFeatureInterceptor)
    feature.addIterationInterceptor(skipFeatureInterceptor)
    spec.addInitializerInterceptor(skipFeatureInterceptor)
    spec.addSetupInterceptor(skipFeatureInterceptor)
    spec.addCleanupInterceptor(skipFeatureInterceptor)
  }

  static class SkipFeatureInterceptor implements IMethodInterceptor {
    boolean hasFailed
    String failedIterationName

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
      if (!hasFailed) {
        try {
          invocation.proceed()
        }
        catch (Throwable t) {
          hasFailed = true
          failedIterationName = invocation.iteration.name
          println "### has failed in iteration $failedIterationName"
        }
      }
      if (!hasFailed)
        return
//      invocation.getFeature().skipped = true
      throw new AssumptionViolatedException(
        "skipping subsequent iterations after failure"//, t
      )
    }
  }
}
