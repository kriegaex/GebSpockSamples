package de.scrum_master.stackoverflow.q59842227

import spock.lang.Specification

class FileCreatorTest extends Specification {
  def "index info file created"() {
    given:
    File file = Mock() {
      createNewFile() >> true
    }

    expect:
    new FileCreator().createIndexInfoFile(file)
  }

  def "no index info file created"() {
    given:
    File file = Mock()

    expect:
    !new FileCreator().createIndexInfoFile(file)
  }
}
