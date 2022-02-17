package de.scrum_master.stackoverflow.q62126581;

import java.util.List;

public class TransformerUser {
  public void foo(Transformer transformer, List<Test> tests) {
    transformer
      .findSuccess()
      .apply(tests);
  }
}
