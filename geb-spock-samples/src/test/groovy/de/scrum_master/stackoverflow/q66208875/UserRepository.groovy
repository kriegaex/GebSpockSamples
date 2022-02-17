package de.scrum_master.stackoverflow.q66208875

class UserRepository {
  Optional<User> findById(int id) {
    Optional.of(new User(id: id, username: "User #$id"))
  }
}
