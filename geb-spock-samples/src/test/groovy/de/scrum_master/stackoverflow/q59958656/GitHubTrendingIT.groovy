package de.scrum_master.stackoverflow.q59958656

import geb.spock.GebReportingSpec

class GitHubTrendingIT extends GebReportingSpec {
  def test() {
    given:
    browser.baseUrl = "https://github.com"
    def providerListPage = to ProviderListPage
    report "provider list page"
    providerListPage
      .list()
      .collect {
        def pageInfo = it.attr("href").split('/').reverse()
        [pageInfo[1], pageInfo[0]]
      }
      .each {
        println "trending page = $it"
        to ProviderPage, it[0], it[1]
        report "trending page"
      }

    expect:
    true
  }
}
