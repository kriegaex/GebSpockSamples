package de.scrum_master.stackoverflow.q57036137

class PublishBuildLog {
  BuildLogProvider buildLogProvider = new BuildLogProvider()

  void setBuildLogProvider(BuildLogProvider buildLogProvider) {
    this.buildLogProvider = buildLogProvider
  }

  def call(def jobName, def buildNumber) {
    println "### $jobName / $buildNumber"
    println "### ${jobName.class} / ${buildNumber.class}"
    def contents = buildLogProvider.getBuildLog(jobName, buildNumber)
    writeFile(file: "filename", text: contents)
  }
}
