package de.scrum_master.stackoverflow.q71272788

class MyClass {
  private final Calculator calculator

  MyClass(Calculator calculator) {
    this.calculator = calculator
  }

  int calculate() {
    return calculator.multiply(3, 4)
  }
}
