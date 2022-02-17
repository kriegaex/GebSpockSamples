package com.baeldung.jni;

public class HelloWorldJNI {
  static {
    System.loadLibrary("native");
  }

  public static void main(String[] args) {
    new HelloWorldJNI().sayHello();
  }

  public native String sayHello();
}
