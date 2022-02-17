import spock.lang.Specification
import spock.lang.Stepwise

import java.util.concurrent.CompletableFuture

//@Stepwise
class SpockCompletableFutureTest extends Specification {
  def runnable = Stub(Runnable) {
    run() >> {
      println "${Date.newInstance()} BEGIN1 in thread ${Thread.currentThread()}"
      sleep(3000)
      println "${Date.newInstance()} END1   in thread ${Thread.currentThread()}"
    }
  }

  def "runAsyncWithMock"() {
    when:
    CompletableFuture.runAsync(runnable)

    then:
    true
  }

  def "runAsyncWithMockAndClosure"() {
    when:
    CompletableFuture.runAsync({ runnable.run() })

    then:
    true
  }

  def "runAsyncWithClass"() {
    when:
    CompletableFuture.runAsync(new Runnable() {
      void run() {
        println "${Date.newInstance()} BEGIN2 in thread ${Thread.currentThread()}"
        sleep(3000)
        println "${Date.newInstance()} END2   in thread ${Thread.currentThread()}"
      }
    })

    then:
    true
  }
}
