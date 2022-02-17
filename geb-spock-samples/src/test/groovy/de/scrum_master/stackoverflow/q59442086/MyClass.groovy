package de.scrum_master.stackoverflow.q59442086

class MyClass implements Serializable {
  Steps steps
  String mvn = "/my/path/mvn"

  MyClass(steps) {
    this.steps = steps
  }

  void myMethodToTest(script, String credentialsId) {
    steps.withCredentials(
      [
        [
          class: "UsernamePasswordMultiBinding",
          credentialsId: "$credentialsId",
          usernameVariable: "USR",
          passwordVariable: "PWD"
        ]
      ]
    ) {
      steps.sh """
        export USR=${script.env["USR"]}
        export PWD=${script.env["PWD"]}
        $mvn -X clean deploy
      """.stripIndent()
    }
  }
}
