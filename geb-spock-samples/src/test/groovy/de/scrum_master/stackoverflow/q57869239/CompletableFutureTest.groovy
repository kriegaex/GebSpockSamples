package de.scrum_master.stackoverflow.q57869239

import spock.lang.Specification
import spock.lang.Stepwise

import java.util.concurrent.CompletableFuture

//@Stepwise
class CompletableFutureTest extends Specification {
  def runnable = Stub(Runnable) {
    run() >> {
      println "${Date.newInstance()} BEGIN1 in thread ${Thread.currentThread()}"
      sleep(3000)
      println "${Date.newInstance()} END1   in thread ${Thread.currentThread()}"
    }
  }

  def "runAsyncWithMock"() {
    when:
    def future = CompletableFuture.runAsync(runnable)
//    while (!future.done) {
//      sleep 100
//    }

    then:
    true
  }

  def "runAsyncWithMockAndClosure"() {
    when:
    def future = CompletableFuture.runAsync({ runnable.run() })
//    while (!future.done) {
//      sleep 100
//    }

    then:
    true
  }

  def "runAsyncWithClass"() {
    when:
    def future = CompletableFuture.runAsync(new Runnable() {
      void run() {
        println "${Date.newInstance()} BEGIN2 in thread ${Thread.currentThread()}"
        sleep(3000)
        println "${Date.newInstance()} END2   in thread ${Thread.currentThread()}"
      }
    })
//    while (!future.done) {
//      sleep 100
//    }

    then:
    true
  }
}
