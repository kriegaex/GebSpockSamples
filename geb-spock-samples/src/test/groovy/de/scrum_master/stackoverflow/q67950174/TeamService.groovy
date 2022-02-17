package de.scrum_master.stackoverflow.q67950174

class TeamService {
  def dep1, dep2

  TeamService(dep1, dep2) {
    this.dep1 = dep1
    this.dep2 = dep2
  }

  boolean moveAssets(Team team) {
    println "Moving assets for team $team"
    return false
  }

  void deleteTeam(String id) {
    Team team = findTeamById(id)
    moveAssets(team)
    delete(team)
  }

  Team findTeamById(String id) {
    println "Searching for team $team"
    return new Team(id: id)
  }

  void delete(Team team) {
    println "Deleting team $team"
  }
}
