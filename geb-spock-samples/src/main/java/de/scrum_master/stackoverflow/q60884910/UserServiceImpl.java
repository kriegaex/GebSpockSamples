package de.scrum_master.stackoverflow.q60884910;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserServiceImpl implements UserService {
  static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  UserRepository userRepository;
  AuthenticationService authenticationService;

  public UserServiceImpl(UserRepository userRepository, AuthenticationService authenticationService) {
    this.userRepository = userRepository;
    this.authenticationService = authenticationService;
  }

  public void signUp(UserDTO userDTO) {
    logger.info("ActionLog.Sign up user.Start");
    Optional<UserEntity> checkedEmail = userRepository.findByEmail(userDTO.getEmail());
    System.out.println(checkedEmail);
    if (checkedEmail.isPresent()) {
      System.out.println("check email: " + checkedEmail);
      logger.error("ActionLog.WrongDataException.Thrown");
      throw new WrongDataException("This email already exists");
    }

    String password = new BCryptPasswordEncoder().encode(userDTO.getPassword());
    UserEntity customerEntity = UserEntity
      .builder()
      .name(userDTO.getName())
      .surname(userDTO.getSurname())
      .username(userDTO.getEmail())
      .email(userDTO.getEmail())
      .password(password)
      .role(Role.ROLE_USER)
      .build();

    userRepository.save(customerEntity);
    logger.info("ActionLog.Sign up user.Stop.Success");
  }

  public static void main(String[] args) {
    new UserServiceImpl(
      new UserRepository(),
      new AuthenticationServiceImpl()
    ).signUp(new UserDTO());
  }
}
