package de.scrum_master.gitter.spock.d20210128;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

public class ClassThatLogs {
  private static final Logger LOG = LoggerFactory.getLogger(ClassThatLogs.class);

  public void logSomethingWithInfo(String whomToGreet) {
    final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
    final String logMessage = "Live long and prosper, " + whomToGreet;
    System.out.println(logMessage);
    LOG.info(logMessage);
  }
}
