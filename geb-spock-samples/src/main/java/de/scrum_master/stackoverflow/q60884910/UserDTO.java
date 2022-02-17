package de.scrum_master.stackoverflow.q60884910;

public class UserDTO {
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return "John";
  }

  public String getSurname() {
    return "Doe";
  }
}
