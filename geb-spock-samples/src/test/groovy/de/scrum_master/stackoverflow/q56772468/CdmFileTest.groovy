package de.scrum_master.stackoverflow.q56772468


import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.Paths

class CdmFileTest extends Specification {
  private static final String filePath = 'mock/cdmTestFile.txt'
  private static final String fileContent = """\
    I heard, that you're settled down
    That you found a girl and you're, married now
    I heard, that your dreams came true
    I guess she gave you things
    I didn't give to you\
  """.stripIndent()

  private CdmFile cdmFile

  void setup() {
    cdmFile = Spy() {
      createBufferedReader(Paths.get(filePath)) >> {
        new BufferedReader(
          new InputStreamReader(
            new ByteArrayInputStream(
              fileContent.getBytes(StandardCharsets.UTF_8)
            )
          )
        )
      }
    }
  }

  def "non-existent file leads to exception"() {
    given:
    Path notRealPath = Paths.get('notreal.txt')

    when:
    cdmFile.readFileContents(notRealPath)

    then:
    thrown NoSuchFileException
  }

  def "read file contents into a string"() {
    given:
    Path testFilePath = Paths.get(filePath)

    when:
    String fileContents = cdmFile.readFileContents(testFilePath)

    then:
    fileContents.contains("your dreams came true\nI guess")
  }

  def "handle file content line by line"() {
    given:
    def result = []
    def closure = { line -> result << line }
    Path testFilePath = Paths.get(filePath)

    when:
    cdmFile.eachLineInFileAsString(testFilePath, closure)

    then:
    result == fileContent.split("\n")
  }
}
