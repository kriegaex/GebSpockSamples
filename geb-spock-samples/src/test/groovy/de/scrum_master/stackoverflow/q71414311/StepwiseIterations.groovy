package de.scrum_master.stackoverflow.q71414311

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtensionAnnotation(StepwiseIterationsExtension)
@interface StepwiseIterations {
  String value() default "skipping iteration because previous iteration failed"
}
