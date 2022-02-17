package de.scrum_master.stackoverflow.q59993907;

import java.util.List;

public class FirstClass {
  public static void doSomething() {
    List<String> values = SecondClass.getOrderedValuesBy(anotherMethod());
    // action
    System.out.println(values);
  }

  private static String anotherMethod() {
    // some action
    return ""; // something that depends on action above.
  }
}
