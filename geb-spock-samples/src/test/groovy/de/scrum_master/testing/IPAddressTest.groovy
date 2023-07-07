package de.scrum_master.testing

import spock.lang.Specification

class IPAddressTest extends Specification {
  static final int CAPACITY = 10

  def test() {
    given:
    int[] ipAddresses = new int[CAPACITY]
    Random random = new Random()

    when:
    for (i in 0..<CAPACITY) {
      ipAddresses[i] = random.nextInt()
      Integer firstIP = ipAddresses[i]
      println firstIP + " = " + Integer.toHexString(firstIP) + " -> "+
        "${firstIP.rightShiftUnsigned(24).intValue() & 0x000000ff}." +
        "${firstIP.rightShiftUnsigned(16).intValue() & 0x000000ff}." +
        "${firstIP.rightShiftUnsigned(8).intValue() & 0x000000ff}." +
        "${firstIP.rightShiftUnsigned(0).intValue() & 0x000000ff}"
    }

    then:
    println ipAddresses.size()
  }
}
