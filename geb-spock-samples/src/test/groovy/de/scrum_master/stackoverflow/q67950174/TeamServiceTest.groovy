package de.scrum_master.stackoverflow.q67950174

import spock.lang.Specification

class TeamServiceTest extends Specification {
  def deleteTeam() {
    given:
    Team teamDocument = new Team(id: "dummy")
    String id = "foo"
    def mockedInjectedService1 = "X"
    def mockedInjectedService2 = "Y"
    TeamService teamService = Spy(constructorArgs: [mockedInjectedService1, mockedInjectedService2])

    when: 'I delete the team'
    teamService.deleteTeam(id)

    then: 'I want to check that moveAssets gets called(team resources going to be preserved'
    1 * teamService.findTeamById(id) >> teamDocument
    1 * teamService.moveAssets(teamDocument) >> true
  }
}
