package dev.sarek.example;

public class Util {
  public static String toGeeky(String input) {
    return input.toLowerCase()
      .replace('o', '0').replace('l', '!').replace('e', '3')
      .replace('t', '7').replace('s', '$');
  }
}
