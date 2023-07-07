package de.scrum_master.stackoverflow.q71927711;

import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilderUtility {
  public String buildSelectQuery(List<Long> productCodes) {
    System.out.println();
    return productCodes.stream()
      .map(Object::toString)
      .collect(Collectors.joining(", ", "QUERY: ", ""));
  }
}
