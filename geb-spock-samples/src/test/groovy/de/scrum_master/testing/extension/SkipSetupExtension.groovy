package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo

/**
 * See https://groups.google.com/d/msg/geb-user/zsILBsH-DxE/4hqdCTprBgAJ
 */
class SkipSetupExtension extends AbstractAnnotationDrivenExtension<SkipSetup> {
  SkipSetupMethodInterceptor interceptor

  @Override
  void visitFeatureAnnotation(SkipSetup annotation, FeatureInfo feature) {
    if (!interceptor) {
      interceptor = new SkipSetupMethodInterceptor()
      feature.spec.addSetupInterceptor interceptor
    }
    interceptor.skippedFeatures << feature.name
  }
}
