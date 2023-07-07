package de.scrum_master.gitter.spock.d20210706

import spock.lang.Issue
import spock.lang.Specification
import spock.util.EmbeddedSpecRunner

// Groovy 2.5.15 does not support Java 16+ class files, because it embeds ASM 8.0.1.
// See also https://issues.apache.org/jira/browse/GROOVY-10503 and https://github.com/apache/groovy/pull/1690.
// Since 2.5.18, '@Requires' is no longer necessary.
// @Requires({ Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 16 })
@Issue([
  "https://issues.apache.org/jira/browse/GROOVY-10503",
  "https://issues.apache.org/jira/browse/GROOVY-10656"
])
class EmbeddedSpecRunnerTest extends Specification {
  def test() {
    given:
    EmbeddedSpecRunner specRunner = new EmbeddedSpecRunner()
    specRunner.configurationScript = {
      def myMap = [:]
      myMap['hello'] = 'world'
      println "### " + myMap['hello']
      System.setProperty("myMap_hello", "world")
    }
    // We cannot use 'setTargetBytecode' here, because the default compiler config is an anonymous subclass throwing an
    // exception when setting it.
    // specRunner.compiler.loader.config.targetBytecode = "8"
    specRunner
      .run """
        import spock.lang.*
    
        class SpockTestClass extends Specification {
          def "hello must be world"() {
            given:
            def value = System.getProperty("myMap_hello")// myMap['hello']
            
            expect:
            value == 'world'
          }
        }
      """

    expect:
    true
  }

}
