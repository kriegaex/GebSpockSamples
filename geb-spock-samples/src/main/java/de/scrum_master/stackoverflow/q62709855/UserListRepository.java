package de.scrum_master.stackoverflow.q62709855;

import java.util.Optional;

public class UserListRepository {
  public Optional<UserList> findByUserIdAndSharedListAndActiveFlagTrue(int userId) {
    return Optional.of(new UserList("admins"));
  }
}
