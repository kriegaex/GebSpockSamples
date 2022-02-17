package de.scrum_master.stackoverflow.q61032514;

public class DueDateEditor {
  String text;

  public boolean isEmpty() {
    return text == null || text.trim() == "";
  }

  public void startEdit() {
    if (!isEmpty())
      callSuperStartEdit();
  }

  public void callSuperStartEdit() {}
}
