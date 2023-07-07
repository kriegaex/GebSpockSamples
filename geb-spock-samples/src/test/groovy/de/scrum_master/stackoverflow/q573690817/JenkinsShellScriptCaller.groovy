package de.scrum_master.stackoverflow.q573690817

class JenkinsShellScriptCaller {
  private final def context

  JenkinsShellScriptCaller(context) {
    this.context = context
  }

  def doSomething(foo) {
    //println context
    context.sh('''
    # bash script that uses foo
    echo "Hello world!"
    ''')
  }
}
