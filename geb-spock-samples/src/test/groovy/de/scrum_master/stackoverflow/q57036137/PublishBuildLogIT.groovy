package de.scrum_master.stackoverflow.q57036137

import com.homeaway.devtools.jenkins.testing.JenkinsPipelineSpecification
import spock.lang.IgnoreIf
import spock.lang.Requires

import static de.scrum_master.testing.JavaAgentDetector.isJMockitActive

@IgnoreIf({
  // Only works with a locally build 2.1.6-SNAPSHOT, but not on GitHub with 2.1.5
  // TODO: Bump version after https://github.com/ExpediaGroup/jenkins-spock/pull/111 was merged and released
  sys["java.class.path"].contains("jenkins-spock-2.1.5.jar")
})
@Requires({ JMockitActive })
class PublishBuildLogIT extends JenkinsPipelineSpecification {
  BuildLogProvider buildLogProvider = Mock()
  PublishBuildLog publishBuildLog

  def setup() {
    publishBuildLog = new PublishBuildLog(buildLogProvider: buildLogProvider)
    explicitlyMockPipelineStep('writeFile')
    explicitlyMockPipelineVariable('atChecker')
  }

  def "Gets the log file contents for a specific job and build"() {
    when: "the call method is executed with the jobName and buildNumber parameters set"
    publishBuildLog.call("JOBNAME", "42")

    then: "the getBuildLog on the buildLogProvider is called with those parameters"
    1 * buildLogProvider.getBuildLog("JOBNAME", "42")
  }

  def "the contents of log file is written to the workspace"() {
    given: "getBuildLog returns specific contents"
    def logFileText = "Example Log File Text"
    buildLogProvider.getBuildLog(_, _) >> logFileText

    when: "publishBuildLog.call is executed"
    publishBuildLog.call("JOBNAME", "42")

    then: "the specific contents is passed to the writeFile step"
    1 * getPipelineMock("writeFile").call([file: _, text: logFileText])
  }

}
