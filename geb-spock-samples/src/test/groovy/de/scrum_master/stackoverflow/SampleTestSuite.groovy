package de.scrum_master.stackoverflow

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.Suite

/** See https://stackoverflow.com/a/50421405/1082681 */

@RunWith(Suite.class)
@Suite.SuiteClasses([SpockoTest, CarTest])
class SampleTestSuite {
  @BeforeClass
  static void beforeTests() throws Exception {
    println "Before suite"
  }

  @AfterClass
  static void afterTests() throws Exception {
    println "After suite"
  }

/*
  static class FooTest extends Specification {
    def test() {
      expect:
      println "FooTest"
    }
  }

  static class BarTest extends Specification {
    def test() {
      expect:
      println "BarTest"
    }
  }
*/
}
