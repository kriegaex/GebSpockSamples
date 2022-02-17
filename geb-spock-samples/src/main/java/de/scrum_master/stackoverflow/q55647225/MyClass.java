package de.scrum_master.stackoverflow.q55647225;

public class MyClass {
  @MDCAnnotated
  public void doSomething() {
    System.out.println("xxx");
  }

  @MDCAnnotated({
    @MDCValue(key = "key", content = "content"),
    @MDCValue(key = "key2", content = "blah")
  })
  public void doSomethingElse() {
    System.out.println("xxx");
  }

  public static void main(String[] args) throws Exception {
    for (MDCValue mdcValue : MyClass.class.getMethod("doSomething").getAnnotation(MDCAnnotated.class).value())
      System.out.println(mdcValue);
    System.out.println("-----");
    for (MDCValue mdcValue : MyClass.class.getMethod("doSomethingElse").getAnnotation(MDCAnnotated.class).value())
      System.out.println(mdcValue);
  }
}
