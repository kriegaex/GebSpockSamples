package de.scrum_master.stackoverflow.q67912725

import spock.lang.Specification

class PricingServiceTest extends Specification {
  def reportingService = Mock(ReportingService)
  def pricingService = new PricingService(reportingService: reportingService)
  def reportData = new ReportData(pricingDescription: 'Pricing for Produce')

  def "Should return summary report data"() {
    given:
    ReportSummary stubbedSummary = new ReportSummary(
      reportType: 'Pricing',
      createdTime: new Date(),
      reportedById: 'GXT111111345'
    )

    when:
    ReportSummary reportSummary = pricingService.updatePricingDetails(reportData)

    then:
    1 * reportingService.updateReport(
      { it.pricingDescription == 'Pricing for Produce' }
    ) >> stubbedSummary
    reportSummary.reportType == stubbedSummary.reportType
    reportSummary.reportedById == stubbedSummary.reportedById
    0 * _
  }
}
