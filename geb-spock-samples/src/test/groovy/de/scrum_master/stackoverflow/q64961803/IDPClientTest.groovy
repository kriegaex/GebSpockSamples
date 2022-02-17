package de.scrum_master.stackoverflow.q64961803

import spock.lang.Specification

class IDPClientTest extends Specification {
  def test() {
    given: "mock IDPClient with stubbed method"
    IDPClient idpClient = Mock() {
      adminCreateUser(_) >> { AdminCreateUserRequest adminCreateUserRequest ->
        UserType user = new UserType()
        user.setUserName(adminCreateUserRequest.getUserName())
        AdminCreateUserResult result = new AdminCreateUserResult()
        result.setUser(user)
        result
      }
    }
    def request = new AdminCreateUserRequest("test user")

    expect: "real IDPClient behaves normally"
    new IDPClient().adminCreateUser(request).user.userName == "real user"

    and: "mock IDPClient displays stub behaviour"
    idpClient.adminCreateUser(request).user.userName == "test user"
  }
}
