package de.scrum_master.stackoverflow.q64423053

import de.scrum_master.stackoverflow.q64423053.xxx.BlokerBeregning as ModelBlokerBeregning
import spock.lang.Specification

class ModelBlokerBeregningTest extends Specification {
  def test() {
    expect:
    new ModelBlokerBeregning(id: 111).id == 111
  }
}
