package de.scrum_master.stackoverflow.q64164101;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

@Aspect
public class LogAspect {
  private static final Logger log = LoggerFactory.getLogger(LogAspect.class.getName());

  @Around("@annotation(Loggable)")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    if (result instanceof Mono)
      return ((Mono) result).doOnSuccess(getConsumer(joinPoint, start));
    return result;
  }

  public Consumer getConsumer(ProceedingJoinPoint joinPoint, long start) {
    return o -> {
      String response = "";
      if (Objects.nonNull(o))
        response = o.toString();
      log.info("Enter: {}.{}() with argument[s] = {}",
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
        joinPoint.getArgs());
      log.info("Exit: {}.{}() had arguments = {}, with result = {}, Execution time = {} ms",
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
        joinPoint.getArgs()[0],
        response, (System.currentTimeMillis() - start));
    };
  }
}
