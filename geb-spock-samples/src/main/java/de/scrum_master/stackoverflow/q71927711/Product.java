package de.scrum_master.stackoverflow.q71927711;

public class Product {
  private long id;
  private String name;

  public Product(long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
