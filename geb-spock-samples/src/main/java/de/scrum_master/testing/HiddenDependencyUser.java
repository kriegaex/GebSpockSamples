package de.scrum_master.testing;

public class HiddenDependencyUser {
  public String getMessage() {
    return new HiddenDependency().getResult();
  }
}
