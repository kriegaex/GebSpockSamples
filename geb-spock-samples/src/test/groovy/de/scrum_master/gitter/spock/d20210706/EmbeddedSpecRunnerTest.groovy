package de.scrum_master.gitter.spock.d20210706

import spock.lang.Requires
import spock.lang.Specification
import spock.util.EmbeddedSpecRunner

// Groovy 2.5.14 does not support Java 16+ class files, but EmbeddedSpecCompiler.loader is assigned a default
// GroovyClassLoader instead of an instance created with a custom CompilerConfiguration. Therefore, it seems to default
// to creating class files with a target byte code version of the currently running JDK instead of what it can maximally
// run itself.
@Requires({ Integer.parseInt(System.getProperty("java.version").replaceAll("[.].*", "")) < 16 })
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
