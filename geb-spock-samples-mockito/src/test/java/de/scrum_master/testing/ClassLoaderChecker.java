package de.scrum_master.testing;

import net.bytebuddy.agent.ByteBuddyAgent;

import javax.crypto.CipherOutputStream;
import java.util.Arrays;

public class ClassLoaderChecker {
  public static boolean isClassLoaded(String className) {
    boolean isLoaded = Arrays
      .stream(ByteBuddyAgent.install().getAllLoadedClasses())
      .anyMatch(aClass -> aClass.getName().equals(className));
    System.out.println("Class loader checker: " + className + " -> loaded = " + isLoaded);
    return isLoaded;
  }

  public static void main(String[] args) {
    // Should yield 'true'
    isClassLoaded("java.lang.String");
    isClassLoaded("java.net.URL");
    isClassLoaded("java.net.URI");
    isClassLoaded("java.util.Properties");
    isClassLoaded("java.io.InputStream");
    isClassLoaded("java.io.FileInputStream");
    // Should yield 'false'
    isClassLoaded("org.acme.DoesNotExist");
    isClassLoaded("de.scrum_master.stackoverflow.q57525767.ByteBuddyProxy");

    // Should change from 'false' to 'true'
    isClassLoaded("javax.crypto.CipherOutputStream");
    CipherOutputStream outputStream = new CipherOutputStream(null, null);
    isClassLoaded("javax.crypto.CipherOutputStream");
  }
}
