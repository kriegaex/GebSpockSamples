package de.scrum_master.stackoverflow.q58279620;

public class ClassUnderTest {
  public static Object someMethod(String n) {
    return n == null ? "nothing" : "something";
  }

  public static Object someMethod(Integer n) {
    return n == null ? -999 : 11;
  }
}
