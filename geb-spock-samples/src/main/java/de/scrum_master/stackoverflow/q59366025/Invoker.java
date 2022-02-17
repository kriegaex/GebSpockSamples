package de.scrum_master.stackoverflow.q59366025;

class Invoker {
  Delegate delegate;

  Invoker(Delegate delegate) {
    this.delegate = delegate;
  }

  void invoke(UserData user) {
    delegate.processUser(user);
  }
}
