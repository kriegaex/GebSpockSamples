package de.scrum_master.stackoverflow.q61032514

import spock.lang.Ignore
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Unroll

@Issue("https://stackoverflow.com/a/61037719/1082681")
class DueDateEditorTest extends Specification {
  @Unroll
  def 'super start edit #shouldMsg be called if the cell is #cellStateMsg'() {
    given:
    DueDateEditor editor = Spy() {
      isEmpty() >> empty
    }

    when:
    editor.startEdit()

    then:
    (empty ? 0 : 1) * editor.callSuperStartEdit()

    where:
    empty << [true, false]
    shouldMsg = empty ? 'should not' : 'should'
    cellStateMsg = empty ? 'empty' : 'not empty'
  }

  @Unroll
  def "super start edit #shouldMsg be called if cell text is '#text'"() {
    given:
    DueDateEditor editor = Spy()
    editor.text = text

    when:
    editor.startEdit()

    then:
    (editor.isEmpty() ? 0 : 1) * editor.callSuperStartEdit()
    // Or, if 'isEmpty()' has a side effect:
    // (text ? 1 : 0) * editor.callSuperStartEdit()

    where:
    text << ["foo", "", null, "line 1\nline 2"]
    shouldMsg = text ? 'should' : 'should not'
    cellStateMsg = text ? 'not empty' : 'empty'
  }

  @Unroll
  @Ignore("just a counter-example to explain why it GroovySpy does not work with Java classes")
  def 'GroovySpy variant: super start edit #shouldMsg be called if the cell is #cellStateMsg'() {
    given:
    DueDateEditor2 editor = GroovySpy() {
      isEmpty() >> empty
    }

    when:
    editor.startEdit()

    then:
    (empty ? 0 : 1) * editor.callSuperStartEdit()

    where:
    empty << [true, false]
    shouldMsg = empty ? 'should not' : 'should'
    cellStateMsg = empty ? 'empty' : 'not empty'
  }
}
