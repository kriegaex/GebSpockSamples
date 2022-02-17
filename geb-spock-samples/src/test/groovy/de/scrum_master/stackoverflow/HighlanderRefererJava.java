package de.scrum_master.stackoverflow;

public class HighlanderRefererJava {
  public void doSomething() {
    // This only works if accessed singleton is compiled in another module:
    // Highlander.getInstance().fight();
    try {
      ((Highlander) Highlander.class.getDeclaredMethod("getInstance").invoke(null)).fight();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
