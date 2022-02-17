package de.scrum_master.stackoverflow.q70817315;

import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
  @Override
  public String generateString(String s) {
    return s;
  }
}
