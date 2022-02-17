package de.scrum_master.stackoverflow.q66208875

class UserService {
  UserRepository userRepository = new UserRepository()

  User getResponsible(Map configs, List<Map> agents) {
    Integer responsibleId = configs.responsibleId as Integer
    Optional<User> userOptional = userRepository.findById(responsibleId)
    User user = userOptional.orElse(null)
    user
  }
}
