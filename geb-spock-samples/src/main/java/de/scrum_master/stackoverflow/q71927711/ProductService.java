package de.scrum_master.stackoverflow.q71927711;

//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class ProductService {
  private final QueryBuilderUtility queryBuilderUtility;
  private final JdbcTemplate productJdbcTemplate;

  public ProductService(/*@Qualifier("productJdbcTemplate")*/ JdbcTemplate productJdbcTemplate, QueryBuilderUtility queryBuilderUtility) {
    this.productJdbcTemplate = productJdbcTemplate;
    this.queryBuilderUtility = queryBuilderUtility;
  }

  public List<Product> findProducts(List<Long> productCodes) {
    String productQuery = queryBuilderUtility.buildSelectQuery(productCodes);
    List<Product> products = productJdbcTemplate.query(productQuery, new ProductRowMapper(), productCodes.toArray());
    return products;
  }
}
