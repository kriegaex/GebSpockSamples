package de.scrum_master.stackoverflow.q64013999

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class WeaponWriterTest extends Specification {
  private WeaponWriter writer
  @Shared
  def emptyObject = Mock(Weapon)
  @Shared
  def weaponMocked = Mock(Weapon)

  def setupSpec() {
    emptyObject.content() >> ""
    weaponMocked.content() >> "BF Sword"
  }

  @Unroll
  def "flatten weapon list into '#expectedText'"() {
    given:
    writer = new WeaponWriter([weapon1, weapon2])

    expect:
    writer.writeWeapon() == expectedText

    where:
    weapon1      | weapon2      | expectedText
    emptyObject  | emptyObject  | "Headline"
    weaponMocked | emptyObject  | "Headline" + "BF Sword"
    weaponMocked | weaponMocked | "Headline" + "BF Sword" + "BF Sword"
  }
}
