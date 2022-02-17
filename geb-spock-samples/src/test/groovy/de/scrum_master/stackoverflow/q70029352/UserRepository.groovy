package de.scrum_master.stackoverflow.q70029352

class UserRepository {
  List<User> users = [
    new User(id: "a", name: "Alice"),
    new User(id: "b", name: "Bob"),
    new User(id: "c", name: "Claire")
  ]

  User findById(String id) {
    users.findAll { it.id == id }?.first()
  }
}
