package de.scrum_master.stackoverflow.q61032514;

public class TreeTableCell<A, B> {
  String text;

  public final boolean isEmpty() {
    return text == null || text.trim() == "";
  }
}
