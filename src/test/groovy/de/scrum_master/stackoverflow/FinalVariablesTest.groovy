package de.scrum_master.stackoverflow

import spock.lang.Specification

class FinalVariablesTest extends Specification {
  def "Final local variables can be changed"() {
    when:
    final def a = 3
    final b = 4
    final int c = 5
    then:
    a + b + c == 12

    // This used to work in Groovy 2.4, but Groovy 2.5 now enforces final local variables
    // and does not compile the re-assignment.
/*
    when:
    a = b = c = 11
    then:
    a + b + c == 33
*/
  }

  final def d = 3
  static final e = 4
  final int f = 5

  def "Class or instance members really are final"() {
    expect:
    d + e + f == 12

    when:
    // Compile errors:
    // cannot modify final field 'f' outside of constructor.
    // cannot modify static final field 'e' outside of static initialization block.
    // cannot modify final field 'd' outside of constructor.
    true //d = e = f = 11
    then:
    true //d + e + g == 33
  }
}
