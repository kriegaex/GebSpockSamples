package de.scrum_master.stackoverflow.q59993907;

import java.util.ArrayList;
import java.util.List;

public class SecondClass {
  public static List<String> getOrderedValuesBy(String by) {
    List<String> values = new ArrayList<>();
    values.addAll(getOrderedValuesBy(by, true));
    return values;
  }

  private static List<String> getOrderedValuesBy(String by, boolean ordered) {
    // some action
    List<String> result = new ArrayList<>();
    result.add("one");
    result.add("two");
    result.add("three");
    return result;
  }
}
