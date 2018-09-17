package de.scrum_master.stackoverflow

import spock.lang.Specification
import spock.lang.Unroll

class AllowedPasswordsTest extends Specification {
  static allowedPasswords = ["1", "2"]

  @Unroll
  def "password check for '#input' should return #result"() {
    expect:
    checkPassword(input) == result

    where:
    input << allowedPasswords + ["3", "oops", "  ", null]
    result = input in allowedPasswords
  }

  static boolean checkPassword(String input) {
    return input?.trim()?.matches("[12]")
  }
}
