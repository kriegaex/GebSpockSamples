package de.scrum_master.testing

import geb.spock.GebReportingSpec
import org.openqa.selenium.WebElement
import spock.lang.Requires
import spock.lang.Shared
import static spock.util.matcher.HamcrestMatchers.closeTo
import static spock.util.matcher.HamcrestSupport.that
import java.text.NumberFormat

import static java.text.NumberFormat.getInstance

/**
 * Adapted from an example found at https://github.com/Microsoft/WinAppDriver
 * and made to work with non-English calculator versions, too (tested with German)
 *
 * Before running this test, prepare according to the above GitHub read-me:
 *   - activate developer mode:
 *     https://docs.microsoft.com/en-us/windows/uwp/get-started/enable-your-device-for-development
 *   - install + start Windows Driver:
 *     c:\Program Files (x86)\Windows Application Driver\WinAppDriver.exe
 */

@Requires({ os.windows && sys["geb.env"] == "win_app" })
class CalculatorIT extends GebReportingSpec {

  def addition() {
    expect:
    calculate("1+7=") == 8
  }

  def subtraction() {
    expect:
    calculate("9-1=") == 8
  }

  def multiplication() {
    expect:
    calculate("9*9=") == 81
  }

  def division() {
    expect:
    calculate("88/11=") == 8
  }

  def combination() {
    expect:
    calculate("7*9+1=/8=") == 8
  }

  def nonIntegerDivision() {
    expect:
    that calculate("88/10="), closeTo(8.8, 1E-5)
  }

  @Shared
  def buttonNames = [
    "1": "num1Button", "2": "num2Button", "3": "num3Button", "4": "num4Button", "5": "num5Button",
    "6": "num6Button", "7": "num7Button", "8": "num8Button", "9": "num9Button", "0": "num0Button",
    "+": "plusButton", "-": "minusButton", "*": "multiplyButton", "/": "divideButton",
    "=": "equalButton", "C": "clearButton"
  ]

  Number calculate(String calculation) {
    pushButtons("C")
    pushButtons(calculation as String[])
    calculationResult
  }

  def pushButtons(String... buttons) {
    buttons.each {
      driver.findElementByAccessibilityId(buttonNames[it]).click()
    }
  }

  @Shared
  WebElement calculationResultElement = driver.findElementByAccessibilityId("CalculatorResults")
  @Shared
  NumberFormat numberFormat = getInstance(Locale.default)

  Number getCalculationResult() {
    numberFormat.parse(
      calculationResultElement.text
        .replaceAll("^[^0-9]+", "")
        .replaceAll("[^0-9]*\$", "")
    )
  }

}
