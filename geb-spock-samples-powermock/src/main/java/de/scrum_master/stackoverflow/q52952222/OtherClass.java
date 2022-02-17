package de.scrum_master.stackoverflow.q52952222;

public class OtherClass {
  public static void methodThatCallsTesterStaticMethod(String one, String two, String three, boolean four, String five) {
    System.out.println(Tester.sendFaqRequest("A", "B"));
    System.out.println(Tester.sendFaqRequest("C", "D"));
    System.out.println(Tester.sendFaqRequest("E", "F"));
  }
}
