package de.scrum_master.stackoverflow.q573690817

import org.jenkinsci.plugins.workflow.cps.CpsScript
import spock.lang.FailsWith
import spock.lang.Specification

/**
 * https://stackoverflow.com/q/73690817/1082681
 */
class JenkinsShellScriptCallerTest extends Specification {
  @FailsWith(value = NullPointerException, reason = 'cannot stub non-existent method')
  def 'does something'() {
    given: 'valid input'
    def validInput = 'foo'
    def cpsScript = createContext()

    expect:
    new JenkinsShellScriptCaller(cpsScript).doSomething(validInput) == 'sh called'
  }

  private def createContext() {
    Mock(CpsScript) {
      sh(_) >> 'sh called'
    }
  }
}
