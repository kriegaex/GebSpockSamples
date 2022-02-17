package de.scrum_master.stackoverflow.q59442086

class Steps {
  def withCredentials(List args, Closure closure) {
    println "withCredentials: $args, " + closure
    closure()
  }

  def sh(String script) {
    println "sh: $script"
  }
}
