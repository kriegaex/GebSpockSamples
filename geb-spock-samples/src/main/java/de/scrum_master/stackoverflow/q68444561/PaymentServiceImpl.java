package de.scrum_master.stackoverflow.q68444561;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

  private final PaymentsMapper paymentsMapper;
  private final MyProperties myProperties;

  public PaymentServiceImpl(PaymentsMapper paymentsMapper, MyProperties myProperties) {
    this.paymentsMapper = paymentsMapper;
    this.myProperties = myProperties;
  }

  @Override
  public void doSomething() {
    List<String> ids = paymentsMapper.getPaymentIds(
      myProperties.getPayments().getOperator(),
      myProperties.getPayments().getPeriod().getDuration().getSeconds()
    );

    System.out.println("IDs = " + ids);
    long updated = 0;
    for (String id : ids) {
      updated += paymentsMapper.updatedPaymentsWithId(id);
    }
  }
}
