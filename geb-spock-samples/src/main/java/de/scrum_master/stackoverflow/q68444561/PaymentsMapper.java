package de.scrum_master.stackoverflow.q68444561;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PaymentsMapper {
  public List<String> getPaymentIds(String operator, long seconds) {
    return Collections.singletonList("one");
  }

  public long updatedPaymentsWithId(String id) {
    return new Random().nextInt(10) + 1;
  }
}
