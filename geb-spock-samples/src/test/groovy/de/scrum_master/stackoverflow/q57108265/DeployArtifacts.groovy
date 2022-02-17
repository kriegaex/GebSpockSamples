package de.scrum_master.stackoverflow.q57108265

class DeployArtifacts {
  ArtifactType artifactType

  DeployArtifacts(ArtifactType artifactType) {
    this.artifactType = artifactType
  }

  def call(List artifacts) {
    String methodName = "deployStream${artifactType.toString()}"

    for (artifact in artifacts) {
      "$methodName"(artifact)
    }
  }

  def deployStreamSOA(Map artifactDetails) {
    println "original"
    String artifactName = getDownloadArtifactName(artifactDetails)
    String targetName = getDownloadArtifactTarget(artifactDetails)
    String modifiedConfigFileName = getUpdatedConfigFileName(artifactName)
    deployArtifact(artifactName, targetName, modifiedConfigFileName)
  }

  def deployArtifact(def s, String t, String u) {}

  def getUpdatedConfigFileName(def s) {}

  def getDownloadArtifactTarget(def map) {}

  def getDownloadArtifactName(def map) {}
}
