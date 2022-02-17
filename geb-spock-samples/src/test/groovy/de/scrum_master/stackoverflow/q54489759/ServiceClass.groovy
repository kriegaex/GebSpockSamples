package de.scrum_master.stackoverflow.q54489759

class ServiceClass {
  private DaoClass dao

  String getAddress(String name) {
    return dao.getAddressFromSomewhere(name)
  }
}
