package de.scrum_master.stackoverflow.q67912725

class ReportingService {
  ReportSummary updateReport(ReportData reportData) {
    return new ReportSummary(
      reportType: "real report",
      createdTime: new Date(),
      reportedById: "me"
    )
  }
}
