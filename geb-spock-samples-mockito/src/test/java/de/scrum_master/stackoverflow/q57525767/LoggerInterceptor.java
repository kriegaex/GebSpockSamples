package de.scrum_master.stackoverflow.q57525767;

import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import org.slf4j.Logger;

public class LoggerInterceptor {
  private static Logger logger;

  public static void setLogger(Logger logger) {
    LoggerInterceptor.logger = logger;
  }

  public void log(@SuperCall Runnable zuper, @This ByteBuddyProxy self) {
    logger.info("Called on " + self.getTarget());
//      logger.info(zuper.toString());
    zuper.run();
  }
}
