package de.scrum_master.stackoverflow.q64013999

class WeaponWriter {
  private List<Weapon> weaponList

  WeaponWriter(List<Weapon> weaponList) {
    this.weaponList = weaponList
  }

  String writeWeapon() {
    "Headline" + weaponList*.content().join("")
  }
}
