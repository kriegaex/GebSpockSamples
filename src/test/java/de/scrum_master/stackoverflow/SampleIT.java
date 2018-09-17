package de.scrum_master.stackoverflow;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SampleIT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {

    baseUrl = "https://github.com";
    loadFirefoxDriver();
    //loadChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  private void loadFirefoxDriver() {
    System.setProperty("webdriver.gecko.driver", "c:\\Users\\Alexa\\.m2\\repository\\webdriver\\geckodriver\\win64\\0.14.0\\geckodriver.exe");
    driver = new FirefoxDriver();
  }

  private void loadChromeDriver() throws MalformedURLException {
    // To remove message "You are using an unsupported command-line flag: --ignore-certificate-errors.
    // Stability and security will suffer."
    // Add an argument 'test-type'
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("test-type");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    driver = new RemoteWebDriver(new URL("http://localhost:9515"), capabilities);
  }

  @Test
  public void testFirstSelenium() throws Exception {
    driver.get(baseUrl + "/codesolid");
    driver.findElement(By.linkText("tutorials")).click();
    assertEquals(
      "Source Code for the CodeSolid Tutorials",
      driver.findElement(By.cssSelector("article h1")).getText()
    );
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      }
      else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
