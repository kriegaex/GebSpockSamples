package de.scrum_master.stackoverflow;

public class FirebaseDatabase {
  private static FirebaseDatabase instance;

  public static FirebaseDatabase getInstance() {
    if (instance == null)
      instance = new FirebaseDatabase();
    return instance;
  }
}
