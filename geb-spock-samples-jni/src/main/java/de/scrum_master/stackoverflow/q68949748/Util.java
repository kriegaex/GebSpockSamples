package de.scrum_master.stackoverflow.q68949748;

import com.baeldung.jni.HelloWorldJNI;

public class Util {
  private static HelloWorldJNI helloWorld = new HelloWorldJNI();

  public static void setHelloWorld(HelloWorldJNI helloWorld) {
    Util.helloWorld = helloWorld;
  }

  public static void doSomethingUseful() {
    System.out.println(helloWorld.sayHello());
  }
}
