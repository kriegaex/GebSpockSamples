package de.scrum_master.stackoverflow

import groovy.mock.interceptor.MockFor
import spock.lang.Specification

class MockDirTest extends Specification {

  def "Mock eachDir indirectly via method stubbing"() {
    setup:
    File subDir = Mock() {
      // Stub all methods (in-)directly used by 'eachDir'
      getName() >>> ['subDir1', 'subDir2']
      lastModified() >> 2000
      exists() >> true
      isDirectory() >> true
    }
    File parentDir = Mock() {
      // Stub all methods (in-)directly used by 'eachDir'
      getName() >> 'parentDir'
      listFiles() >> [subDir, subDir]
      exists() >> true
      isDirectory() >> true
    }
    def helper = new DirectoryNameHelper()

    when:
    def result = helper.getUpperCaseDirectoryNames(parentDir)

    then:
    result == ['SUBDIR1', 'SUBDIR2']
  }

  def "Mock eachDir directly via MockFor.demand"() {
    setup:
    File subDir = Mock() {
      getName() >>> ['subDir1', 'subDir2']
    }
    def parentDir = new MockFor(File)
    parentDir.demand.eachDir { Closure closure ->
      closure(subDir)
      closure(subDir)
    }
    def helper = new DirectoryNameHelper()

    when:
    def result
    parentDir.use {
      result = helper.getUpperCaseDirectoryNames(new File('parentDir'))
    }

    then:
    result == ['SUBDIR1', 'SUBDIR2']
  }

  static class DirectoryNameHelper {
    List<String> getUpperCaseDirectoryNames(File dir) {
      List<String> names = []
      dir.eachDir { File f ->
        names << f.name.toUpperCase()
      }
      names
    }
  }

}
