package de.scrum_master.stackoverflow.q57525767;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * See https://stackoverflow.com/a/57538340/1082681
 */
public class ByteBuddyNewTest {
  private Logger logger;

  @Before
  public void setup() {
    logger = mock(Logger.class);
//    logger = LoggerFactory.getLogger(ByteBuddyNewTest.class);
    LoggerInterceptor.setLogger(logger);
  }

  @Test
  public void useTarget() throws IllegalAccessException, InstantiationException {
    Resource someBean = new Resource();
    someBean.setId("id-000");
    Class<?> dynamicType = new ByteBuddy()
      .subclass(Resource.class)
      .defineField("target", Resource.class, Visibility.PRIVATE)
      .method(ElementMatchers.any())
      .intercept(MethodDelegation.to(new LoggerInterceptor())
        .andThen(MethodDelegation.toField("target")))
      .implement(ByteBuddyProxy.class)
      .intercept(FieldAccessor.ofField("target"))
      .make()
      .load(getClass().getClassLoader())
      .getLoaded();
    Object dti = dynamicType.newInstance();
    ((ByteBuddyProxy) dti).setTarget(someBean);
    assertEquals("Target", ((ByteBuddyProxy) dti).getTarget(), someBean);
    String id = ((Resource) dti).getId();
    assertEquals("Id", someBean.getId(), id);
    assertFalse("equals()", dti.equals(""));
    verify(logger, times(2)).info(any(String.class));
  }
}
