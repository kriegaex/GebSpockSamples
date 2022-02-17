package de.scrum_master.stackoverflow.q60044097

class MyClass implements Serializable {
  def steps

  MyClass(steps) { this.steps = steps }

  void myMethodToTest(script, credentialsId, dataObject) {
    dataObject.myKeyValue.each { conf ->
      steps.withCredentials(
        [
          [
            $class          : 'UsernamePasswordMultiBinding',
            credentialsId   : "${credentialsId}",
            usernameVariable: 'USR',
            passwordVariable: 'PWD'
          ]
        ]
      ) {
        steps.sh("git push --set-upstream origin ${conf.branch}")
      }
    }
  }
}
