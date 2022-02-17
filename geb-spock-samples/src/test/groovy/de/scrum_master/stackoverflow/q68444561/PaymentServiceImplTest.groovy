package de.scrum_master.stackoverflow.q68444561

import spock.lang.Shared
import spock.lang.Specification

import java.time.Duration

class PaymentServiceImplTest extends Specification {

  @Shared
  PaymentsMapper paymentsMapper = Mock(PaymentsMapper) {
    getPaymentIds(_ as String, _ as Long) >> Arrays.asList('1', '2', '3')
    3 * updatedPaymentsWithId(_ as String)
  }
  @Shared
  MyProperties properties = new MyProperties()
  @Shared
  PaymentServiceImpl paymentService = new PaymentServiceImpl(paymentsMapper, properties)

  def setupSpec() {
    properties.setPayments(new MyProperties.Payments())
    properties.getPayments().setOperator('OP1')
    properties.getPayments().setPeriod(new MyProperties.Payments.Period())
    properties.getPayments().getPeriod().setDuration(Duration.ofSeconds(3600))
  }


  def 'update pending acceptation payment ids'() {
    expect:
    paymentService.doSomething()
  }
}
