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
      <groupId>de.scrum-master.test</groupId>
      <artifactId>test-bom</artifactId>
      <version>1.1</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
    <!-- Test resources + base configuration + base classes for Spock, Geb, Selenium -->
    <dependency>
      <groupId>de.scrum-master</groupId>
      <artifactId>test-resources</artifactId>
      <version>1.2</version>
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
