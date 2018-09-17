package de.scrum_master.stackoverflow

import spock.lang.Specification

class ControllerTest extends Specification {

  static class Service {
    HelperAdd hd

    def add(AddModel request) {
      int a = request.a
      int b = request.b
      int d = hd.add(a)
      int c = a + d
      return c
    }
  }

  static class HelperAdd {
    def add(int a) {
      int k = a + 4
      return k
    }
  }

  static class AddModel {
    int a
    int b
  }

  Service src
  HelperAdd hd

  def setup() {
    hd = Mock()
    src = new Service(hd: hd)
  }

  def "Add 2 numbers"() {
    given: "input"
    def request = new AddModel(a: 2, b: 3)
    when:
    def result = src.add(request)
    then:
    1 * hd.add(_) >> 2
    result == 4
  }
}
