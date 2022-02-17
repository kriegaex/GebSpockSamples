package de.scrum_master.stackoverflow.q62709855

import spock.lang.Specification

class UserListRepositoryTest extends Specification {
  def test() {
    given:
    Optional<UserList> userListOptional = Optional.empty()
    UserListRepository userListRepository = Mock() {
      findByUserIdAndSharedListAndActiveFlagTrue(*_) >> userListOptional
    }

    expect:
    !userListRepository.findByUserIdAndSharedListAndActiveFlagTrue(123).isPresent()
  }
}
