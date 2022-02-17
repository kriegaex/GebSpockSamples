package de.scrum_master.stackoverflow.q70817315;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * An abstract class from which the tested one is inherited and which contains the service
 */
public class AbstractComponent {
  @Autowired
  protected SomeService someService;
}
