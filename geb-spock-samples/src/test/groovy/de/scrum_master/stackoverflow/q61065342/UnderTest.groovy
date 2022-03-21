package de.scrum_master.stackoverflow.q61065342

import groovy.transform.ASTTest

import static org.codehaus.groovy.control.CompilePhase.CLASS_GENERATION

class UnderTest {
  @ASTTest(phase = CLASS_GENERATION, value = {
    System.err.println(">> CLASS_GENERATION ----------")
    def expr = node.code.statements[0].expression
    def objectExpression = expr.leftExpression.objectExpression
    System.err.println(objectExpression)
    System.err.println(objectExpression.type)
    System.err.println(objectExpression.originType)
    System.err.println(objectExpression.originType.isDerivedFromGroovyObject())
    System.err.println(objectExpression.getNodeMetaData())
    System.err.println("<< CLASS_GENERATION ----------")
  })
  void call(Collaborator collaborator, String value) {
    collaborator.x = value
  }
}
