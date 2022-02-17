package de.scrum_master.stackoverflow.q70029352

import spock.lang.Specification

class UserServiceTest extends Specification {
  UserRepository userRepoMock = Mock()
  AnotherRepository anotherRepoMock = Mock()
  UserService userServiceUnderTest = new UserService(userRepo: userRepoMock, anotherRepo: anotherRepoMock)
  User testUser = new User(id: "x", name: "Xander")

  def "My Test"() {
    given:
    String id = "sample"

    // Better move this to the 'then:' block
    and:
    1 * userRepoMock.findById(id) >> testUser
    // Change to '0 * anotherRepoMock._(*_)' to make test fail
    1 * anotherRepoMock._(*_)

    when:
    User user = userServiceUnderTest.findUser(id)

    then:
    user == testUser
  }
}
