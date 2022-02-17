package de.scrum_master.stackoverflow.q58470315;

public class DataService {
  private long offset;

  public DataService(long offset) {
    this.offset = offset;
  }

  public DataService() {}

  public DataService findByOffset(long offset) {
    return new DataService(offset);
  }

  public long getOffset() {
    return offset;
  }

  @Override
  public String toString() {
    return "DataService{" +
      "offset=" + offset +
      '}';
  }
}
