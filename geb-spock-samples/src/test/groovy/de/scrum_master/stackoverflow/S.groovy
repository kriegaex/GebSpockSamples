package de.scrum_master.stackoverflow

import spock.lang.Specification
import spock.lang.Unroll

class S extends Specification {
  @Unroll
  def "Test #file"() {
    given:
    println "Doing redundant stuff with $file"
    when:
    println "Doing redundant stuff with no param"
    then:
    valuableCode(file)

    where:
    file << ["file1", "file2", "file3"]
    valuableCode << [
      {
        println "Valuable code for $it"
        assert 11 < 22
        assert "foo" > "bar"
        true
      },
      {
        println "Valuable code for $it"
        assert 22 < 33
        assert "bar" < "zot"
        true
      },
      {
        println "Valuable code for $it"
        assert 33 > 44
        assert "zot" > "blah"
        true
      },
    ]
  }
}
