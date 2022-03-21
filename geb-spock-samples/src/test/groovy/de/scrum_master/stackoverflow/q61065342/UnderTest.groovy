package de.scrum_master.stackoverflow.q61065342

import groovy.transform.ASTTest

import static org.codehaus.groovy.control.CompilePhase.CLASS_GENERATION

class UnderTest {
  @ASTTest(phase = CLASS_GENERATION, value = {
    def expr = node.code.statements[0].expression
    def type = expr.leftExpression.objectExpression.type
    System.err.println("CLASS_GENERATION: type.isDerivedFromGroovyObject() = " + type.isDerivedFromGroovyObject())
  })
  void call(Collaborator collaborator, String value) {
    collaborator.x = value
  }
}
