package de.scrum_master.stackoverflow.q70654015;

import java.util.Arrays;

public class SampleJava {
  public String say_hello_to(String input) {
    return "Hello " + input + "!";
  }

  public static void main(String[] args) {
    Arrays.stream(SampleJava.class.getDeclaredMethods()).forEach(System.out::println);
    System.out.println(new SampleJava().say_hello_to("world"));
  }
}
