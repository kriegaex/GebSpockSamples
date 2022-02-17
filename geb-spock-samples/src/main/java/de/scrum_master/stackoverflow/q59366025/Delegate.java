package de.scrum_master.stackoverflow.q59366025;

class Delegate {
  private RegistrationService service;

  Delegate (RegistrationService r) {
    this.service = r;
  }

  void processUser(UserData data) {
    service.doRegistration(data);
  }
}
