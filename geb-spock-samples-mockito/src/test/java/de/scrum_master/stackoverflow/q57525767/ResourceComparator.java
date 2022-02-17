package de.scrum_master.stackoverflow.q57525767;

import net.bytebuddy.implementation.bind.annotation.This;

public class ResourceComparator {
  public static boolean equals(@This ByteBuddyProxy self, ByteBuddyProxy that) {
    Resource someBeanThis = self.getTarget();
    Resource someBeanThat = that.getTarget();
    return someBeanThis.getId().equals(someBeanThat.getId());
  }
}
