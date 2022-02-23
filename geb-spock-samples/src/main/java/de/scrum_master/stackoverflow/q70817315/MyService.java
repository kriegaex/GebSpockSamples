package de.scrum_master.stackoverflow.q70817315;

import org.springframework.stereotype.Service;

/**
 * The class to be tested
 */
@Service
public class MyService extends AbstractComponent {
  public String methodForTest(String s) {
    return someService.generateString(s);
  }
}
