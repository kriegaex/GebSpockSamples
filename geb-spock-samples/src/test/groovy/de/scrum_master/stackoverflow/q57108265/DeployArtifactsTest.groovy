package de.scrum_master.stackoverflow.q57108265

import spock.lang.Specification

class DeployArtifactsTest extends Specification {
  def "type is SOA then deployStreamSOA is called"() {
    setup:
    def artifactList = [[groupId: "com.company", artifactId: "artifact1", version: "1.0.0"]]
    DeployArtifacts deployArtifacts = Spy(constructorArgs: [ArtifactType.SOA])

    when:
    "DeployArtifact call is passed the SOA ArtifactType"
    deployArtifacts.call(artifactList)

    then:
    "the deployStreamSOA method is called"
    1 * deployArtifacts.deployStreamSOA(_) >> ""
  }
}
