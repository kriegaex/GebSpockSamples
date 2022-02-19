package de.scrum_master.stackoverflow.q59958656

import geb.spock.GebReportingSpec

class GitHubTrendingIT extends GebReportingSpec {
  def test() {
    given:
    def providerListPage = to ProviderListPage
    report "provider list page"

    when:
    providerListPage
      .list()
      .collect {
        def pageInfo = it.attr("href").split('/').reverse()
        [pageInfo[1], pageInfo[0]]
      }
      // Take only the top 3 of 25 elements in order to speed up the test
      .take(3)
      .each {
        println "trending page = $it"
        to ProviderPage, it[0], it[1]
        report "trending page ${it[0]}/${it[1]}"
      }

    then:
    true
  }
}
