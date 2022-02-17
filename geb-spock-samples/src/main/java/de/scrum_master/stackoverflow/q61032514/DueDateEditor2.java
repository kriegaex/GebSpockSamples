package de.scrum_master.stackoverflow.q61032514;

import java.time.LocalDate;

public class DueDateEditor2 extends TreeTableCell<String, LocalDate> {
  public void startEdit() {
    if (!isEmpty())
      callSuperStartEdit();
  }

  public void callSuperStartEdit() {}
}
