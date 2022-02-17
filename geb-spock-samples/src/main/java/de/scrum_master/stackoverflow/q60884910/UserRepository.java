package de.scrum_master.stackoverflow.q60884910;

import java.util.Optional;

public class UserRepository {
  public void save(UserEntity user) {
    System.out.println("user saved");
  }

  public Optional<UserEntity> findByEmail(String email) {
    return email.equalsIgnoreCase("john.doe@acme.com")
      ? Optional.empty()
      : Optional.of(new UserEntity());
  }
}
