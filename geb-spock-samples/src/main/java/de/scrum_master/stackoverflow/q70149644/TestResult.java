package de.scrum_master.stackoverflow.q70149644;

public class TestResult {
  private String result;

  public TestResult(String result) {
    this.result = result;
  }

  public boolean isAuthorized() {
    return !result.toLowerCase().matches(".*(forbidden|blocked|unauthorized|denied).*");
  }

  @Override
  public String toString() {
    return "TestResult(result='" + result + "')";
  }
}
