package de.scrum_master.gitter.spock.d20200724

class TransactionStrategy {
  TransactionRepository repository

  TransactionStrategy(TransactionRepository repository) {
    this.repository = repository
  }

  void execute(PaymentDistribution paymentDistribution) {
    repository.saveAll([
      new Transaction(
        paymentDistribution: paymentDistribution,
        amount: paymentDistribution.paidTotal,
        type: paymentDistribution.payment.purpose == PaymentPurpose.IDENTIFICATION ? de.scrum_master.gitter.spock.d20200724.TransactionType.IDENTIFICATION_PAYMENT_RECEIVED : de.scrum_master.gitter.spock.d20200724.TransactionType.EXTENSION_PAYMENT_RECEIVED)
    ])
  }
}
