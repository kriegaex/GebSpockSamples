package de.scrum_master.stackoverflow.q60482676;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Collect {
  public static void main(String[] args) {
    List<String> alphaNumericList = Arrays.asList(
      "Demo.23", "Demo.1000", "Demo.12", "Demo.12",
      "Test.01", "Test.02", "Test.100", "Test.99"
    );

    Collections.sort(
      alphaNumericList,
      (text1, text2) -> {
        Integer number1 = parseInt(text1.replaceFirst(".*[.]", ""));
        int number2 = parseInt(text2.replaceFirst(".*[.]", ""));
        return number1.compareTo(number2);
      }
    );
    System.out.println("Output " + alphaNumericList);
  }
}
