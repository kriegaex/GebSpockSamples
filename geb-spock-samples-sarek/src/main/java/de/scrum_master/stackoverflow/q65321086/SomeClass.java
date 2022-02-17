package de.scrum_master.stackoverflow.q65321086;

import java.time.LocalDate;
import java.time.Year;

import static java.time.Month.JANUARY;

public class SomeClass {
  public LocalDate methodUnderTest() {
    return Year.now()
      .atMonth(JANUARY)
      .atDay(1);
  }
}
