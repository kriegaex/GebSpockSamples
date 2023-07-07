package de.scrum_master.stackoverflow.q71927711

import spock.lang.Specification

class ProductServiceTest extends Specification {
  JdbcTemplate jdbcTemplate = Mock()
  // If you don't want to stub a mathod, just use the real class:
  //QueryBuilderUtility queryBuilderUtility = new QueryBuilderUtility()
  QueryBuilderUtility queryBuilderUtility = Mock()
  ProductService productService
  List<Long> productCodes = [33334L, 66754L, 56772L]
  TestUtil testUtil = new TestUtil()
  List<Product> products = testUtil.createProducts()

  def setup() {
    productService = new ProductService(jdbcTemplate, queryBuilderUtility)
  }

  def 'Find Products for product codes'() {
    given:
    //1 * jdbcTemplate.query(_* as String, _ as ProductRowMapper, _ as Object[]) >> products
    1 * jdbcTemplate.query(*_) >> products
    queryBuilderUtility.buildSelectQuery(_) >> "my query"

    expect:
    productService.findProducts(productCodes).size() == 3
  }
}
