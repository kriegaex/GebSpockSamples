package de.scrum_master.testing;

public class MyClassWithInstanceInitialiser {
  private int id;
  private String name;

  // Instance initialiser is executed in between super() and rest of constructor code,
  // see http://wiki.c2.com/?DoubleBraceInitialization.
  //
  // Typical JMockit idiom, see also https://stackoverflow.com/a/15913018/1082681.
  {
    id = 11;
    name = "John";
    System.out.println("Instance initialiser: " + id + ", " + name);
  }

  public MyClassWithInstanceInitialiser() {
    System.out.println("Default constructor: " + id + ", " + name);
  }

  public MyClassWithInstanceInitialiser(int idIncrement) {
    id += idIncrement;
    System.out.println("Constructor with ID increment: " + id + ", " + name);
  }

  public MyClassWithInstanceInitialiser(String nameSuffix) {
    name += " " + nameSuffix;
    System.out.println("Constructor with name suffix: " + id + ", " + name);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
