package de.scrum_master.stackoverflow.q65144983

import spock.lang.Specification

class FormConfigServiceImplTest extends Specification {
  def 'test save formConfig without defaults fields'() {
    given:
    List fieldConfigList = Spy(new ArrayList<FieldConfig>())
    FormConfig formConfig = Mock() {
      getFieldsConfig() >> fieldConfigList
    }
    FormConfigRepository formConfigRepository = Mock() {
      save(_) >> new FormConfig()
    }
    CategoryService categoryService = Mock() {
      findById(_) >> new Category()
    }
    def formConfigService = new FormConfigServiceImpl(formConfigRepository, categoryService)

    when:
    formConfigService.save(formConfig, 1)

    then:
    2 * fieldConfigList.add(_)
    1 * formConfig.setCategory(_)
  }
}
