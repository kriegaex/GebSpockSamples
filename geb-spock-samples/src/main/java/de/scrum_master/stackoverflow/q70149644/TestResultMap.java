package de.scrum_master.stackoverflow.q70149644;

import java.util.HashMap;

public class TestResultMap extends HashMap<String, TestResult> {
  private static final long serial = -1234567890L;

  public boolean isAuthorized(String resource) {
    TestResult result = get(resource);
    if (result == null) {
      throw new RuntimeException("Authorization not calculated");
    }
    return result.isAuthorized();
  }
}
