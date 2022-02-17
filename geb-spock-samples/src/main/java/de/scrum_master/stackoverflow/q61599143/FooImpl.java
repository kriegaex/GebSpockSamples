package de.scrum_master.stackoverflow.q61599143;

public class FooImpl extends FooBase {
  private String prop;

  @Override
  public String getProp() {
    System.out.println("Foo.getProp");
    return prop;
  }

  @Override
  public void setProp(String val) {
    System.out.println("Foo.setProp");
    this.prop = val;
  }
}
