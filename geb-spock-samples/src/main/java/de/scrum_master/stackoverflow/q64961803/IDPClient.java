package de.scrum_master.stackoverflow.q64961803;

public class IDPClient {
  public AdminCreateUserResult adminCreateUser(AdminCreateUserRequest adminCreateUserRequest) {
    AdminCreateUserResult userResult = new AdminCreateUserResult();
    UserType userType = new UserType();
    userType.setUserName("real user");
    userResult.setUser(userType);
    return userResult;
  }
}
