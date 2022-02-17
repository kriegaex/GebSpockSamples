package de.scrum_master.stackoverflow.q66208875

import spock.lang.Specification

class UserServiceTest extends Specification {
  UserService userService

  void setup() {
    userService = new UserService()
    userService.userRepository = Mock(UserRepository)
  }

  def "retrieve the responsible of interaction in JivoChat by configs of plugin item"() {
    given: 'that exist a collection of JivoChat interaction configurations'
    List<Map> agents = null
    Map configs = [responsibleId: 1]

    when: 'the main method is called'
    User user = userService.getResponsible(configs, agents)

    then: 'the method get last agent and search in DB by e-mail'
    1 * userService.userRepository.findById(_) >> Optional.of(new User(username: "XPTO"))//.orElse("null")
  }
}
