package de.scrum_master.testing;

/**
 * A bank deposit with <a href="https://en.wikipedia.org/wiki/Compound_interest">compound interest</a>.
 */
public interface IDeposit {
  class DepositException extends Exception {
    public DepositException(String message) {
      super(message);
    }
  }

  double getAnnualNominalInterestRate();
  double getCompoundingPeriodInMonths();

  default double getCompoundingPeriodsPerYear() {
    return 12 / getCompoundingPeriodInMonths();
  }

  default double getBalance(double principalAmount, double numberOfYears) throws DepositException {
    if(principalAmount < 0) throw new IllegalArgumentException("Invalid principalAmount: " + principalAmount);
    if(numberOfYears < 0) throw new DepositException("Invalid numberOfYears: " + numberOfYears);
    double j = getAnnualNominalInterestRate();
    double n = getCompoundingPeriodsPerYear();
    return principalAmount * Math.pow(1 + j / n, n * numberOfYears);
  }

  default double getCompoundInterest(double principalAmount, double numberOfYears) throws DepositException {
    return getBalance(principalAmount, numberOfYears) - principalAmount;
  }
}
