package de.scrum_master.stackoverflow.q69086117;

import java.util.Date;

public class Product {
  private long _id;
  private String _name;
  private String _description;
  private String _category;
  private long _price;
  private Date _creationDate;

  public Product(long id, String name, String description, String category, long price, Date creationDate) {
    this._id = id;
    this._name = name;
    this._description = description;
    this._category = category;
    this._price = price;
    this._creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "Product{" +
           "id=" + _id +
           ", name='" + _name + '\'' +
           ", description='" + _description + '\'' +
           ", category='" + _category + '\'' +
           ", price=" + _price +
           ", creationDate=" + _creationDate +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Product product = (Product) o;

    return _id == product._id;
  }

  @Override
  public int hashCode() {
    return (int) (_id ^ (_id >>> 32));
  }

  public long id() {
    return _id;
  }

  public String name() {
    return _name;
  }

  public String description() {
    return _description;
  }

  public String category() {
    return _category;
  }

  public long price() {
    return _price;
  }

  public Date creationDate() {
    return _creationDate;
  }
}
