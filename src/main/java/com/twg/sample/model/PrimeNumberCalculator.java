package com.twg.sample.model;

import java.util.stream.IntStream;

public class PrimeNumberCalculator {
  public int[] getPrimeNumbers(int end) {
    return IntStream.rangeClosed(1, end)
      .filter(number -> !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0))
      .toArray();
  }
}
