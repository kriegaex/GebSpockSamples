package de.scrum_master.stackoverflow

import spock.lang.Specification

/**
 * See https://stackoverflow.com/q/53521864/1082681
 */
class StringListTest extends Specification {

  static class Utility {
    void processBatchesOf10Lines(String... allTextLines) {
      allTextLines.toList().collate(10).each { addLDocument(it) }
    }

    def addLDocument(List<String> textLines) {}
  }

  def myTest() {
    given:
    Utility spyCH = Spy()

    when:
    spyCH.processBatchesOf10Lines(
      "1", "2", "4", "4g", "5", "6", "7", "8", "9", "0x",
      "A", "B", "C", "Dg", "E", "F", "G", "H", "I", "Jx",
      "K", "L", "M", "Ng", "O", "P", "Q", "R", "S", "Tx",
      "U", "V", "W", "Xg", "Y", "Z", ":", "-", "&", "=x",
      "1", "2", "4", "4g", "5"
    )

    then:
//    4 * spyCH.addLDocument({ it.size() == 10 })
    4 * spyCH.addLDocument(_) >> {
      arguments ->
        def stringVal = arguments[0]
        println "stringVal $stringVal size ${stringVal.size()}"
        assert stringVal.size() == 10
    }

    then:
//    1 * spyCH.addLDocument({ it.size() == 5 })
    1 * spyCH.addLDocument(_) >> {
      arguments ->
        def stringVal = arguments[0]
        println "stringVal $stringVal size ${stringVal.size()}"
        assert stringVal.size() == 5
    }
  }
}
