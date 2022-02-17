package de.scrum_master.testing;

public class XxxBase {
  public void doSomething() {
    System.out.println("Base, doing something");
  }

  public static class XxxSub extends XxxBase {
    public int add(int a, int b) {
      System.out.println("Sub, adding " + a + " + " + b);
      return a + b;
    }
  }

  public static void main(String[] args) {
    new XxxBase().doSomething();
    new XxxSub().doSomething();
    new XxxSub().add(3, 5);
  }
}
