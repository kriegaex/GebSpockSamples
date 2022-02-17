package de.scrum_master.testing.extension

import geb.spock.GebSpec
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.SpecInfo

import java.nio.file.Files

/**
 * See https://groups.google.com/g/geb-user/c/vrNZFaRyDQQ
 */
class TestFailureScreenshotExtension extends AbstractGlobalExtension {
  @Override
  void visitSpec(SpecInfo spec) {
    if (GebSpec.isAssignableFrom(spec.reflection)) {
      def reporter = new ScreenshotOnErrorReporter()
      spec.addListener(reporter)
      spec.allFeatures*.addIterationInterceptor(reporter)
    }
  }

  static class ScreenshotOnErrorReporter extends AbstractRunListener implements IMethodInterceptor {
    GebSpec gebSpec

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
      gebSpec = invocation.instance
      try {
        invocation.proceed()
      } finally {
        gebSpec = null
      }
    }

    @Override
    void error(ErrorInfo error) {
      def driver = gebSpec.driver
      driver.manage().window().maximize()
      takeScreenshot(driver)
    }

    void takeScreenshot(WebDriver driver) {
      // Not all WebDrivers can take screenshots, e.g. HtmlUnitDriver
      if (!(driver instanceof TakesScreenshot)) {
        println "Driver $driver is incapable of taking screenshots"
        return
      }
      def screenshotTempFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
      def targetDir = new File(gebSpec.browser.reportGroupDir, gebSpec.class.name.replaceAll("[.]", "/"))
      // TODO: generate nicer name for screenshot file, this is just an example
      def screenshotFile = new File(targetDir, System.nanoTime() + ".png")
      targetDir.mkdirs()
      Files.copy(screenshotTempFile.toPath(), screenshotFile.toPath())
      println "Screenshot saved as $screenshotFile"
    }
  }

}
