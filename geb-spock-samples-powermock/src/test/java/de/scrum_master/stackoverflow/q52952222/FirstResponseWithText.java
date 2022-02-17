package de.scrum_master.stackoverflow.q52952222;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

public class FirstResponseWithText implements Answer {
  @Override
  public Object answer(InvocationOnMock invocation) throws Throwable {
    Object[] args = invocation.getArguments();
    String methodName = invocation.getMethod().getName();
    return methodName + " called with arguments: " + Arrays.toString(args);
  }
}
