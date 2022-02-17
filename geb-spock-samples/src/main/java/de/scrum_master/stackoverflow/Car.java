package de.scrum_master.stackoverflow;

public class Car {
  private Engine engine;

  public void drive() {
    System.out.println("driving");
    if (engine.isState()) {
      System.out.println("true state");
    }
    else {
      System.out.println("false state");
    }
  }
}
