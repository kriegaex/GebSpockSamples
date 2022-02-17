package de.scrum_master.stackoverflow.q66197474;

public class MyClass {
  private final Foo foo;
  private String stringBar;

  MyClass(Foo foo) {
    this.foo = foo;
    this.stringBar = getStringBar();
  }

  String getStringBar() {
    return "value of stringBar";
  }

  String testFunction() {
    return "value of testFunction";
  }
}
