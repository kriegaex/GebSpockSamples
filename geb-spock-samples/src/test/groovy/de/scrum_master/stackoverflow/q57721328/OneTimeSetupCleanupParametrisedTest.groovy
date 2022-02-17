package de.scrum_master.stackoverflow.q57721328

import spock.lang.See
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@See("https://stackoverflow.com/a/57765390/1082681")
class OneTimeSetupCleanupParametrisedTest extends Specification {
  @Shared User user
  @Shared int iteration

  User createUser() {
    // Set up test fixture only if iteration == 0 (or optionally, if fixture is uninitialised)
    user ?: new User()
  }

  void deleteUser(User userInstance) {
    // Clean up counter (and test fixture, if any) only if iteration == total number of iterations
    if (++iteration == specificationContext.currentIteration.estimatedNumIterations) {
      userInstance.delete()
      user = null
      iteration = 0
    }
  }

//  @Unroll
  void "test something"() {
    setup:
    // Call initialiser helper for each iteration, relying on the fact that it will only
    // create a text fixture if none exists yet
    user = createUser()

    when:
    user.setAdress(new Address(zipCode: inputZipCode, city: inputCity))

    then:
    user.address.city == inputCity
    user.address.zipCode == inputZipCode

    cleanup:
    // Call clean-up helper for each iteration, relying on the fact that it will only
    // clean up the fixture during the last iteration
    deleteUser(user)

    where:
    inputCity | inputZipCode
    "a"       | "1"
    "b"       | "2"
  }

  static class User {
    Address address

    User() {
      println "creating user"
    }

    void setAdress(Address address) {
      this.address = address
    }

    void delete() {
      println "deleting user"
      address = null
    }
  }

  static class Address {
    String zipCode, city
  }
}
