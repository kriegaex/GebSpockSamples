import org.openqa.selenium.htmlunit.HtmlUnitDriver

reportsDir = "target/geb-reports"

driver = {
  // Supports JS, but no screenshots
  new HtmlUnitDriver(true)
}

def final DEFAULT_BROWSER = "chrome"
def final DEFAULT_LANGUAGE = "nl"                  //"en" or "nl"

def browser = System.getProperty("geb.env")
//Default browser
if (!correctBrowser(browser)) {
  browser = DEFAULT_BROWSER
}

def envLang = System.getProperty("geb.env.lang")
//Default language
if (!correctLanguage(envLang)) {
  envLang = DEFAULT_LANGUAGE
}

System.setProperty("geb.env.lang", envLang)
System.setProperty("geb.env", browser)
//System.setProperty("geb.env.lang", "XY")
println "Finished evaluating GebConfig"

def correctBrowser(String browser) {
  return browser
}

def correctLanguage(String language) {
  return language
}
