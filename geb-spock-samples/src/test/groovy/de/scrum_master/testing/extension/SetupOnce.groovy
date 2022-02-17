package de.scrum_master.testing.extension

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.METHOD
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Retention(RUNTIME)
@Target(METHOD)
@ExtensionAnnotation(SetupOnceExtension)
@interface SetupOnce {}
