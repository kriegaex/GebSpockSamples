package de.scrum_master.testing

import geb.navigator.Navigator
import geb.spock.GebReportingSpec
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebElement
import pazone.ashot.AShot
import pazone.ashot.Screenshot
import pazone.ashot.ShootingStrategies
import pazone.ashot.coordinates.WebDriverCoordsProvider

import javax.imageio.ImageIO

/**
 * Extends GebReportingSpec in order to integrate the AShot screenshot utility,
 * see https://github.com/pazone/ashot
 * <p>
 * This is functioning, but a quick & dirty solution because the Geb configuration
 * does not know anything about AShot.
 * <p>
 * TODO: Implement a Reporter, possibly derived from ScreenshotReporter.
 * <p>
 * This way it would be possible to define a CompositeReporter (e.g. via GebConfig)
 * similar to the default one, combining AShotReporter + PageSourceReporter, enabling
 * all GebReportingSpec classes to automatically use AShot and always save full page
 * screenshots instead of viewport only. The only open question is how to offer
 * additional features like single or multiple element screenshots, blurring of
 * out-of-scope screenshot elements and screenshot diffs via the Reporter interface
 * or the Browser.report methods, respectively.
 * <p>
 * See https://gebish.org/manual/current/#reporter-configuration
 */
class AShotReportingSpec extends GebReportingSpec {
  protected AShot aShot = new AShot()
    .coordsProvider(new WebDriverCoordsProvider())
    .shootingStrategy(ShootingStrategies.viewportPasting(100))

  void reportFullPage(String label) {
    reportAShot label
  }

  void reportElements(String label, Navigator... navigators) {
    reportAShot label, navigators
  }

  private void reportAShot(String label, Navigator... navigators) {
    if (!(driver instanceof TakesScreenshot)) {
      System.err.println("Driver type ${driver.class.name} cannot take screenshots")
      return
    }
    // Hide scroll bars for clean screenshots
    String scrollBarMode = js.exec("return document.body.style.overflow")
    js.exec("document.body.style.overflow = 'hidden'")

    try {
      // Get all elements from multi-element navigators into flattened list
      List<WebElement> webElements = navigators*.allElements().flatten()
      Screenshot screenshot = aShot.takeScreenshot(driver, webElements)
      File imageFile = new File(browser.reportGroupDir, testManager.createReportLabel(label) + ".png")
      ImageIO.write(screenshot.image, "PNG", imageFile)
    }
    finally {
      // Restore scroll bars to original state
      js.exec("document.body.style.overflow = '$scrollBarMode'")
    }
  }

}
