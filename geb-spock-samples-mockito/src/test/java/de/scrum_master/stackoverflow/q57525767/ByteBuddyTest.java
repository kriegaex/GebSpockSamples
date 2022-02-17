package de.scrum_master.stackoverflow.q57525767;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static net.bytebuddy.matcher.ElementMatchers.takesArgument;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * See https://stackoverflow.com/revisions/57538340/4
 */
public class ByteBuddyTest {
  private static Logger logger;

  @Before
  public void setup() {
    logger = mock(Logger.class);
    LoggerInterceptorStatic.setLogger(logger);
  }

  @Test
  public void useTarget() throws IllegalAccessException, InstantiationException {
    Resource resource = new Resource();
    ByteBuddyProxyGeneric<Resource> proxy = new ByteBuddyProxyGenericImpl<>();
    proxy.setTarget(resource);
    resource.setId("id-000");
    Class<?> dynamicType = new ByteBuddy()
      .subclass(Resource.class)
      .implement(ByteBuddyProxyGeneric.class)
      .method(ElementMatchers.any())
      .intercept(MethodDelegation.to(LoggerInterceptorStatic.class)
        .andThen(MethodDelegation.to(resource)))
      .method(ElementMatchers.named("getTarget")
        .or(ElementMatchers.named("setTarget")))
      .intercept(MethodDelegation.to(proxy))
      .method(ElementMatchers.named("equals").and(takesArgument(0, Object.class)))
      .intercept(FixedValue.value(true))
      .make()
      .load(getClass().getClassLoader())
      .getLoaded();
    Object dti = dynamicType.newInstance();
    assertEquals("Target", ((ByteBuddyProxyGeneric) dti).getTarget(), resource);
    assertEquals("Id", ((Resource) dti).getId(), resource.getId());
    assertEquals("equals()", true, dti.equals(""));
    verify(logger, times(1)).info(any(String.class));
  }
}
