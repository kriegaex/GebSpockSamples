package de.scrum_master.stackoverflow.q65144983;

public class FormConfigServiceImpl implements FormConfigService {
  private FormConfigRepository formConfigRepository;
  private CategoryService categoryService;

  public FormConfigServiceImpl(FormConfigRepository formConfigRepository, CategoryService categoryService) {
    this.formConfigRepository = formConfigRepository;
    this.categoryService = categoryService;
  }

  @Override
  public FormConfig save(FormConfig formConfig, Long categoryId) {
    setDefaultFormFieldConfig(formConfig, "title", "offer.label.title", FieldType.INPUT);
    setDefaultFormFieldConfig(formConfig, "shortDescription", "offer.label.shortDescription", FieldType.TEXTAREA);
    formConfig.setCategory(categoryService.findById(categoryId));
    return formConfigRepository.save(formConfig);
  }

  private void setDefaultFormFieldConfig(FormConfig formConfig, String name, String label, FieldType type) {
    if (
      formConfig.getFieldsConfig().stream()
        .noneMatch(fieldConfig -> fieldConfig.getName().equals(name))
    )
    {
      formConfig.getFieldsConfig()
        .add(new FieldConfig(name, label, null, "MATCH", type, false, false, true, null));
    }
  }
}
