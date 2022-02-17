package de.scrum_master.stackoverflow.q67912725

import org.springframework.beans.factory.annotation.Autowired

class PricingService {
  @Autowired
  ReportingService reportingService

  ReportSummary updatePricingDetails(ReportData data) {
    reportingService.updateReport(data)
  }
}
