package de.scrum_master.stackoverflow.q65144983;

public class CategoryService {
  public Category findById(Long categoryId) {
    return new Category("BY_ID_" + categoryId);
  }
}
