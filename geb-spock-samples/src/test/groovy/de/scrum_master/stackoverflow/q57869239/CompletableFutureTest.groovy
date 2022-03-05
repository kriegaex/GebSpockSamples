package de.scrum_master.stackoverflow.q57869239

import spock.lang.Specification
import spock.lang.Stepwise

import java.util.concurrent.CompletableFuture

import static java.lang.System.currentTimeMillis

@Stepwise
class CompletableFutureTest extends Specification {
  static final int SLEEP_MILLIS = 1
  static final long TEST_STARTED_AT = currentTimeMillis()

  def runnable = Stub(Runnable) {
    run() >> {
      printf "%5d BEGIN1 in thread %s%n", currentTimeMillis() - TEST_STARTED_AT, Thread.currentThread()
      sleep SLEEP_MILLIS
      printf "%5d END1   in thread %s%n", currentTimeMillis() - TEST_STARTED_AT, Thread.currentThread()
    }
  }

  def "runAsyncWithMock"() {
    given:
    def startTime = currentTimeMillis()
    when:
    def future = CompletableFuture.runAsync(runnable)
    future.get()
    then:
    currentTimeMillis() - startTime >= SLEEP_MILLIS
  }

  def "runAsyncWithMockAndClosure"() {
    given:
    def startTime = currentTimeMillis()
    when:
    def future = CompletableFuture.runAsync({ runnable.run() })
    future.get()
    then:
    currentTimeMillis() - startTime >= SLEEP_MILLIS
  }

  def "runAsyncWithClass"() {
    given:
    def startTime = currentTimeMillis()
    when:
    def future = CompletableFuture.runAsync(new Runnable() {
      void run() {
        printf "%5d BEGIN2 in thread %s%n", currentTimeMillis() - TEST_STARTED_AT, Thread.currentThread()
        sleep SLEEP_MILLIS
        printf "%5d END2   in thread %s%n", currentTimeMillis() - TEST_STARTED_AT, Thread.currentThread()
      }
    })
    future.get()
    then:
    currentTimeMillis() - startTime >= SLEEP_MILLIS
  }
}
