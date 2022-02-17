package de.scrum_master.stackoverflow.q60884910

import spock.lang.Specification

class UserServiceImplTest extends Specification {
   def userRepository = Stub(UserRepository)
   def authenticationService = Stub(AuthenticationServiceImpl)
   def userService = new UserServiceImpl(userRepository, authenticationService)

  def "doesn't throw exception if email doesn't exist in database"() {
    given: "a user DTO"
    def userDto = new UserDTO()
    userDto.email = "example@mail.ru"
    userDto.password = "pw"

    and: "a user repository not finding any user by e-mail"
    userRepository.findByEmail(_) >> Optional.empty()

    when: "signing up a new user"
    userService.signUp(userDto)

    then: "no duplicate e-mail address exception is thrown"
    notThrown WrongDataException
  }
}
