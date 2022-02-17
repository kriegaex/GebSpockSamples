package de.scrum_master.stackoverflow.q60884910;

public class UserEntity {
  private String name;
  private String surname;
  private String username;
  private String email;
  private String password;
  private Role role;

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private UserEntity user = new UserEntity();

    public Builder name(String name) {
      user.name = name;
      return this;
    }

    public Builder surname(String surname) {
      user.surname = surname;
      return this;
    }

    public Builder username(String username) {
      user.username = username;
      return this;
    }

    public Builder email(String name) {
      user.name = name;
      return this;
    }

    public Builder password(String password) {
      user.password = password;
      return this;
    }

    public Builder role(Role role) {
      user.role = role;
      return this;
    }

    public UserEntity build() {
      return user;
    }
  }
}
