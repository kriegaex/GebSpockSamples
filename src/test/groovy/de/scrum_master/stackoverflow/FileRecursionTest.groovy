package de.scrum_master.stackoverflow

import groovy.json.JsonParserType
import groovy.json.JsonSlurper
import spock.lang.Specification

class FileRecursionTest extends Specification {
  def jsonDirectoryTree = """{
      com : {
        na : {
          tests : [
            MyBaseIT.groovy
          ]
        },
        twg : {
          sample : {
            model : [
              PrimeNumberCalculatorSpec.groovy
            ]
          }
        }
      },
      de : {
        scrum_master : {
          stackoverflow : [
            AllowedPasswordsTest.groovy,
            CarTest.groovy,
            FileRecursionTest.groovy,
            {
              foo : [
                LoginIT.groovy,
                LoginModule.groovy,
                LoginPage.groovy,
                LoginValidationPage.groovy,
                User.groovy
              ]
            },
            LuceneTest.groovy
          ],
          testing : [
            GebTestHelper.groovy,
            RestartBrowserIT.groovy,
            SampleGebIT.groovy
          ]
        }
      }
    }"""

  def "Parse directory tree JSON representation"() {
    given:
    def jsonSlurper = new JsonSlurper(type: JsonParserType.LAX)
    def rootDirectory = jsonSlurper.parseText(jsonDirectoryTree)

    expect:
    rootDirectory.de.scrum_master.stackoverflow.contains("CarTest.groovy")
    rootDirectory.com.twg.sample.model.contains("PrimeNumberCalculatorSpec.groovy")

    when:
    def fileList = objectGraphToFileList("src/test/groovy", rootDirectory)
    fileList.each { println it }

    then:
    fileList.size() == 14
    fileList.contains("src/test/groovy/de/scrum_master/stackoverflow/CarTest.groovy")
    fileList.contains("src/test/groovy/com/twg/sample/model/PrimeNumberCalculatorSpec.groovy")
  }

  List<File> objectGraphToFileList(String directoryPath, Object directoryContent) {
    List<File> files = []
    directoryContent.each {
      switch (it) {
        case String:
          files << directoryPath + "/" + it
          break
        case Map:
          files += objectGraphToFileList(directoryPath, it)
          break
        case Map.Entry:
          files += objectGraphToFileList(directoryPath + "/" + (it as Map.Entry).key, (it as Map.Entry).value)
          break
        default:
          throw new IllegalArgumentException("unexpected directory content value $it")
      }
    }
    files
  }

}
