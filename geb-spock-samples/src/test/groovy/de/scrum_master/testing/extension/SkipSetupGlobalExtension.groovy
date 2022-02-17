package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.SpecInfo

/**
 * See https://groups.google.com/d/msg/geb-user/zsILBsH-DxE/4hqdCTprBgAJ
 */
class SkipSetupGlobalExtension extends AbstractGlobalExtension {
  @Override
  void visitSpec(SpecInfo spec) {
    spec.addSetupInterceptor(new SkipSetupGlobalMethodInterceptor())
  }
}
