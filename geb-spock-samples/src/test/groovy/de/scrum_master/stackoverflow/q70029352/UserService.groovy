package de.scrum_master.stackoverflow.q70029352

class UserService {
  UserRepository userRepo
  AnotherRepository anotherRepo

  User findUser(String id){
    anotherRepo.removeTypeById(id)
    userRepo.findById(id)
  }
}
