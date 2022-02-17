package de.scrum_master.testing

import de.jodamob.kotlin.testrunner.SpotlinTestRunner
import org.junit.runner.Description
import org.junit.runner.manipulation.Filter
import org.junit.runner.manipulation.NoTestsRemainException
import org.junit.runner.manipulation.Sorter
import org.junit.runner.notification.RunNotifier
import org.junit.runners.model.InitializationError
import org.spockframework.runtime.Sputnik

class JEP396AwareSpotlinTestRunner extends Sputnik {
  private static final Double javaSpecVersion = Double.parseDouble(System.properties.getProperty("java.specification.version"))
  private static final long javaMajor = (javaSpecVersion < 2 ? 10 * javaSpecVersion - 10 : javaSpecVersion).round()
  public static final boolean hasJEP396 = javaMajor >= 16

  private static final Filter ignoreAllFilter = new Filter() {
    @Override
    boolean shouldRun(Description description) { return false }

    @Override
    String describe() { return "ignores all tests" }
  }

  private final Sputnik sputnik

  JEP396AwareSpotlinTestRunner(Class<?> clazz) throws InitializationError {
    super(clazz)
    sputnik = hasJEP396 ? this : new SpotlinTestRunner(clazz)
  }

  @Override
  void filter(Filter filter) throws NoTestsRemainException {
    if (hasJEP396)
      super.filter(ignoreAllFilter)
    else
      sputnik.filter(filter)
  }

  @Override
  void run(RunNotifier notifier) {
    if (hasJEP396)
      super.run(notifier)
    else
      sputnik.run(notifier)
  }

  @Override
  Description getDescription() {
    if (hasJEP396)
      return super.getDescription()
    else
      return sputnik.getDescription()
  }

  @Override
  void sort(Sorter sorter) {
    if (hasJEP396)
      super.sort(sorter)
    else
      sputnik.sort(sorter)
  }
}
