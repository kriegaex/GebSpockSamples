package de.scrum_master.stackoverflow.q49946922;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersistManager.class)
public class ProxyHandlerTest {
  private ProxyHandler handler;

  @Before
  public void setUp() {
    handler = new ProxyHandler();
  }

  @Test
  public void testProxy() {
    PowerMockito.mockStatic(PersistManager.class);
    ArgumentCaptor<String> arg1 = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> arg2 = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> arg3 = ArgumentCaptor.forClass(String.class);

    handler.process("a long string");
    PowerMockito.verifyStatic(PersistManager.class, times(1));
    PersistManager.proxy(arg1.capture(), arg2.capture(), arg3.capture());
    assertEquals("a", arg1.getValue());
    assertEquals("long", arg2.getValue());
    assertEquals("string", arg3.getValue());
  }
}
