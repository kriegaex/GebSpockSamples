package de.scrum_master.stackoverflow.q60482676;

import de.scrum_master.stackoverflow.q60556923.Issue.IssueType;
import org.junit.Test;

import static de.scrum_master.stackoverflow.q60556923.Issue.IssueType.*;
import static org.junit.Assert.assertEquals;

public class IssueTest {
  // Seriously?! Nah, rather not.
  private static final IssueType TYPE_BUG = BUG;
  private static final IssueType TYPE_ENHANCEMENT = ENHANCEMENT;

  @Test
  public void testUsingStaticFinal() {
    // Does this make anything better or just obfuscate and complicate the test?
    assertEquals("BUG", TYPE_BUG.toString());
    assertEquals("ENHANCEMENT", TYPE_ENHANCEMENT.toString());
  }

  @Test
  public void testUsingEnumDirectly() {
    assertEquals("BUG", BUG.toString());
    assertEquals("ENHANCEMENT", ENHANCEMENT.toString());
  }
}
