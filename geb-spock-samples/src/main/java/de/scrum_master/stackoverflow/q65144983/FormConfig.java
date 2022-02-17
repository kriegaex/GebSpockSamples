package de.scrum_master.stackoverflow.q65144983;

import java.util.List;

public class FormConfig {
  private Category category;
  private List<FieldConfig> fieldsConfig;

  public FormConfig() {}

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<FieldConfig> getFieldsConfig() {
    return fieldsConfig;
  }
}
