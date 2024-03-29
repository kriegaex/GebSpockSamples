package de.scrum_master.stackoverflow.q56064582

import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import reactor.core.publisher.Mono
import spock.lang.Specification

import static org.mockito.ArgumentMatchers.anyString
import static org.powermock.api.mockito.PowerMockito.mockStatic
import static org.powermock.api.mockito.PowerMockito.when

@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
@PrepareForTest([Mono])
class ReactorSampleTest extends Specification {
  def "test me"() {
    given:
    def dummy = Mono.just("power-mocked")
    mockStatic(Mono)
    when(Mono.just(anyString())).thenReturn(dummy)
    def sut = new ReactorSample()

    expect:
    sut.doSomething() == dummy
  }
}
