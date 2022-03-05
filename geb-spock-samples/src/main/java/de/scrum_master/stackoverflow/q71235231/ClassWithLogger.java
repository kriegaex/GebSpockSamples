package de.scrum_master.stackoverflow.q71235231;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassWithLogger {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClassWithLogger.class);

  public void logSomething(int i) {
    LOGGER.info("FYI, 3 * {} = {}", i, 3 * i);
    if (i < 0)
      LOGGER.warn("Be warned that {} is a negative number", i);
    if (i == 0)
      LOGGER.error("Using zero is forbidden");
  }
}
