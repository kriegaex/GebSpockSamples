package de.scrum_master.stackoverflow.q57525767;

import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import org.slf4j.Logger;

public class LoggerInterceptorStatic {
  private static Logger logger;

  public static void setLogger(Logger logger) {
    LoggerInterceptorStatic.logger = logger;
  }

  public static void log(@SuperCall Runnable zuper, @This ByteBuddyProxyGeneric self) {
    logger.info("Called on " + self.getTarget());
    System.out.println("Called on " + self.getTarget());
//    logger.info(zuper.toString());
    zuper.run();
  }
}
