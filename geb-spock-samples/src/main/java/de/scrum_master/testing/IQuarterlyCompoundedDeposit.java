package de.scrum_master.testing;

public interface IQuarterlyCompoundedDeposit extends IDeposit {
  default double getCompoundingPeriodInMonths() {
    return 3;
  }
}
