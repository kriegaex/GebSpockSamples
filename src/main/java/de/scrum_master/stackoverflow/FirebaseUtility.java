package de.scrum_master.stackoverflow;

public class FirebaseUtility {
  private static FirebaseDatabase db = null;

  public FirebaseDatabase getDb() {
    if (db == null)
      initializeFirebase();
    return db;
  }

  protected void initializeFirebase() {
    db = FirebaseDatabase.getInstance();
  }
}
