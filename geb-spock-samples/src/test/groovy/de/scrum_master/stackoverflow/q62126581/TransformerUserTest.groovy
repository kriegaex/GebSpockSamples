package de.scrum_master.stackoverflow.q62126581

import spock.lang.Specification

class TransformerUserTest extends Specification {
  def test() {
    given:
    def transformer = Mock(Transformer)
    def function = { "1234" }

    when:
    new TransformerUser().foo(transformer, [new Test(), new Test()])

    then:
    1 * transformer.findSuccess() >> function
  }
}
