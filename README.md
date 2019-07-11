# Geb + Spock sample project

This project shows how to easily use Geb + Spock in Maven projects and thereby keeping the Maven POM clean,
utilising a BoM for test dependency versions plus a single test dependency to transitively import all other
dependencies needed for multi-browser Geb tests.

It utilises [this test dependency BoM](https://github.com/kriegaex/MavenTestBom) and
[this test dependency](https://github.com/kriegaex/MavenTestResources) in the following way:

Example:

```xml
<dependencyManagement>
  <dependencies>
    <!-- BoM with test dependency versions -->
    <dependency>
      <groupId>de.scrum-master</groupId>
      <artifactId>test-bom</artifactId>
      <version>1.4.1</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
    <!-- Test resources + base configuration + base classes for Spock, Geb, Selenium -->
    <dependency>
      <groupId>de.scrum-master</groupId>
      <artifactId>test-resources</artifactId>
      <version>1.4.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <!-- Test resources + base configuration + base classes for Spock, Geb, Selenium -->
  <dependency>
    <groupId>de.scrum-master</groupId>
    <artifactId>test-resources</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

If you want to change the browser used in the Geb tests, just change tag `geb.env` in _pom.xml_:

```xml
    <!-- Geb, Selenium -->
    <geb.env>chrome_headless</geb.env>
```

Possible values can be looked up in
[GebConfig.groovy](https://github.com/kriegaex/MavenTestResources/blob/master/src/main/groovy/GebConfig.groovy)
in the test resources. Currently we have these:
* `html_unit` - Selenium HtmlUnit driver with activated JavaScript, but without screenshot capability.
  This is also used as a fall-back if `geb.env` is unspecified or misspelled. HtmlUnit is somewhat limited and
  might fail on fancy web pages, but much more useful than I initially thought. It is worth a try for simple
  headless tests.
* `phantomjs` - PhantomJS is a feature-rich headless browser based on Chromium and can be used even in advanced
  tests as a full alternative to Chrome, Firefox, IE etc. This is also great on Jenkins servers without any
  pre-installed browsers and my browser of choice in that case, used in heavy-duty commercial projects. It is
  also nice if developers want to run tests locally and not be bothered with browser Windows popping up all the
  time while they are trying to continue their work.
* `chrome_headless` - Google Chrome has a relatively new headless feature. I have not tested it extensively yet,
  but it is looking really good. If Chrome is installed on your box anyway but you want to run your tests
  headlessly, give it a try.  
* `chrome` - Google Chrome in normal (non-headless) mode. Make sure that Chrome is installed locally.
* `firefox` - Mozilla Firefox. Make sure that FF is installed locally.
* `ie` - Microsoft Internet Explorer. Make sure that IE is installed locally.
* `edge` - Microsoft Edge. Make sure that Edge is installed locally.
* `opera` - Opera. Make sure that Opera (current, not legacy version) is installed locally.
* `win_app` - Appium Windows driver (used for Windows Calculator demo test)
