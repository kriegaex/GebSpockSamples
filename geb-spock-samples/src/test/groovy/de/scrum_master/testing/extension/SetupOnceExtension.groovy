package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo

/**
 * See https://stackoverflow.com/a/63929945/1082681
 */
class SetupOnceExtension extends AbstractAnnotationDrivenExtension<SetupOnce> {
  SetupOnceMethodInterceptor interceptor

  @Override
  void visitFeatureAnnotation(SetupOnce annotation, FeatureInfo feature) {
    if (!interceptor) {
      interceptor = new SetupOnceMethodInterceptor()
      feature.spec.addSetupInterceptor interceptor
    }
    interceptor.annotatedFeatures[feature.name] = false
  }
}
