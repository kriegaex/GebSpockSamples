package de.scrum_master.gitter.spock.d20210706

import spock.lang.Specification
import spock.util.EmbeddedSpecRunner

class FooTest extends Specification {
  def test() {
    given:
    EmbeddedSpecRunner specRunner = new EmbeddedSpecRunner()
    specRunner.configurationScript = {
      def myMap = [:]
      myMap['hello'] = 'world'
      println "### " + myMap['hello']
      System.setProperty("myMap_hello", "world")
    }
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
