package de.scrum_master.stackoverflow.q62126581

import java.util.function.Function

interface Transformer {
  String doSomething();

  Integer performTest();

  Function<List<Test>, String> findSuccess();
}
