package de.scrum_master.stackoverflow.q58470315;

public class ToBeTestedWithInteractions {
  private DataService ds;

  public void setDataService(DataService ds) {
    this.ds = ds;
  }

  // legacy code; can't alter
  public void getData() {
    if (ds == null)
      ds = new DataService();
    ds = ds.findByOffset(5);
    Long len = ds.getOffset();
  }
}
