package de.scrum_master.gitter.spock.d20200724

import spock.lang.Specification
import spock.lang.Unroll

import static de.scrum_master.gitter.spock.d20200724.PaymentPurpose.EXTENSION
import static de.scrum_master.gitter.spock.d20200724.PaymentPurpose.IDENTIFICATION
import static de.scrum_master.gitter.spock.d20200724.PaymentType.INCOMING
import static de.scrum_master.gitter.spock.d20200724.TransactionType.EXTENSION_PAYMENT_RECEIVED
import static de.scrum_master.gitter.spock.d20200724.TransactionType.IDENTIFICATION_PAYMENT_RECEIVED

class PaymentStrategyTest extends Specification {
  @Unroll
  def 'should register transaction amount #transactionAmountExpected if payment purpose: #paymentPurpose'() {
    given:
    def transactionRepository = Mock(TransactionRepository)
    def paymentDistribution = new PaymentDistribution(
      payment: new BankAccountPayment(type: paymentType, purpose: paymentPurpose),
      paidTotal: 10.5
    )
    def strategy = new TransactionStrategy(transactionRepository)

    when:
    strategy.execute(paymentDistribution)

    then:
    1 * transactionRepository.saveAll({ List<Transaction> transactions ->
      transactions.size() == 1
      transactions[0].paymentDistribution == paymentDistribution
      transactions[0].amount == transactionAmountExpected
      transactions[0].type == transactionTypeExpected
    })

    where:
    paymentPurpose | paymentType || transactionTypeExpected         | transactionAmountExpected
    IDENTIFICATION | INCOMING    || IDENTIFICATION_PAYMENT_RECEIVED | 10.5
    EXTENSION      | INCOMING    || EXTENSION_PAYMENT_RECEIVED      | 10.5
  }
}
