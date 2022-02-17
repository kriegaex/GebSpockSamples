package de.scrum_master.stackoverflow

@Singleton
class Highlander {
  def count = 0

  def fight() {
    println "There can be only one!"
    count++
    doSomething()
  }

  def doSomething() {
    println "Doing something"
  }
}
