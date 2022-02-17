package de.scrum_master.stackoverflow.q68060743

import org.spockframework.runtime.WrongExceptionThrownError
import spock.lang.FailsWith
import spock.lang.Specification

class UnpredictableStubInitialisationTest extends Specification {
  @FailsWith(WrongExceptionThrownError)
  def "Upload mock has same name as stubbed method in TransferManager mock"() {
    given:
    def upload = Stub(Upload){
      waitForCompletion() >> { throw new InterruptedException() }
    }

    def transferManager = Stub(TransferManager) {
      upload(_,_,_) >> upload
    }

    when:
    def up = transferManager.upload(null, null, null)
    up.waitForCompletion()

    then:
    thrown(InterruptedException)
  }
  
  def "renamed Upload mock variable solves the problem"() {
    given:
    def uploadStub = Stub(Upload){
      waitForCompletion() >> { throw new InterruptedException() }
    }

    def transferManager = Stub(TransferManager) {
      upload(_,_,_) >> uploadStub
    }

    when:
    def up = transferManager.upload(null, null, null)
    up.waitForCompletion()

    then:
    thrown(InterruptedException)
  }

  def "not stubbing inline solves the problem"() {
    given:
    def upload = Stub(Upload) {
      waitForCompletion() >> { throw new InterruptedException() }
    }
    def transferManager = Stub(TransferManager)
    transferManager.upload(_,_,_) >> upload

    when:
    def up = transferManager.upload(null, null, null)
    up.waitForCompletion()

    then:
    thrown(InterruptedException)
  }
  
  static class Upload {
    String id
    void waitForCompletion() {
      println "Waiting for completion"
    }
  }

  static class TransferManager {
    Upload upload(String a, String b, String c) {
      println "Uploading"
      new Upload(id: a)
    }
  }
}
