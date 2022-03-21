package de.scrum_master.stackoverflow.q61065342

import groovy.transform.ASTTest
import org.codehaus.groovy.classgen.AsmClassGenerator

import static org.codehaus.groovy.control.CompilePhase.INSTRUCTION_SELECTION

class UnderTest {
  @ASTTest(phase = INSTRUCTION_SELECTION, value = {
    System.err.println(">> INSTRUCTION_SELECTION ----------")
    def expr = node.code.statements[0].expression
    def objectExpression = expr.leftExpression.objectExpression
//    System.err.println(AsmClassGenerator.@setGroovyObjectProperty.@methods*.name)
//    System.err.println(objectExpression)
//    System.err.println(objectExpression.type)
//    System.err.println(objectExpression.originType)
//    System.err.println(objectExpression.originType.isDerivedFromGroovyObject())
    System.err.println(objectExpression.originType.interfaces)
//    System.err.println(objectExpression.getNodeMetaData())
    System.err.println("<< INSTRUCTION_SELECTION ----------")
  })
  void call(Collaborator collaborator, String value) {
    collaborator.x = value
  }
}
