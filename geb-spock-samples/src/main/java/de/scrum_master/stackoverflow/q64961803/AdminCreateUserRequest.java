package de.scrum_master.stackoverflow.q64961803;

public class AdminCreateUserRequest {
  private String userName;

  public AdminCreateUserRequest(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }
}
