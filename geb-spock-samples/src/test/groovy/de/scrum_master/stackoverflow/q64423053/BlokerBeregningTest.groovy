package de.scrum_master.stackoverflow.q64423053

import de.scrum_master.stackoverflow.q64423053.xxx.BlokerBeregning
import spock.lang.Specification

class BlokerBeregningTest extends Specification {
  def test() {
    expect:
    new BlokerBeregning(id: 111).id == 111
  }
}
