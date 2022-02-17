package de.scrum_master.stackoverflow.q70817315;

import org.springframework.stereotype.Service;

@Service
public class ServiceForTestWithSetter extends AbstractComponent{
  final SomeService someService;

  public ServiceForTestWithSetter(SomeService someService) {
    this.someService = someService;
  }

  public String methodForTest (String s) {
    return someService.generateString(s);
  }
}
