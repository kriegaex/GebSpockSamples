package de.scrum_master.stackoverflow.q59713798

class FooService {
  Double getFoo(SomeClass clazz) {
    Double returningValue = 0.0
    String parameter

    SomeClass.findAllByCodeLike(parameter).each {
      Double amount = someOtherFooServiceMethod(it, it.someClassAttribute?.toInteger())
      if (amount) {
        returningValue += amount
      }
    }
    return returningValue
  }

  double someOtherFooServiceMethod(Object o, Object o2) {
    o2
  }
}
