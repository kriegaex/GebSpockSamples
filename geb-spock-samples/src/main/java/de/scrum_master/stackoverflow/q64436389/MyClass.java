package de.scrum_master.stackoverflow.q64436389;

import java.util.List;

public class MyClass {
  private String applicationType;
  private List<TestItem> lockedItems;

  public MyClass(String applicationType, List<TestItem> lockedItems) {
    this.applicationType = applicationType;
    this.lockedItems = lockedItems;
  }

  public void setLockStatus(TestItem testItem) {
    if (!applicationType.equals("")) {
      //do stuff here
    }
    else {
      testItem.isLocked = lockFunction(testItem.productID);
    }
  }

  private boolean lockFunction(Integer id) {
    return lockedItems.stream()
      .anyMatch(item -> item.productID.equals(id));
  }

  public static class TestItem {
    private boolean isLocked;
    private Integer productID;

    public TestItem(boolean isLocked, Integer productID) {
      this.isLocked = isLocked;
      this.productID = productID;
    }
  }
}
