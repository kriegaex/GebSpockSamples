package de.scrum_master.testing;

public interface ISquare {
  int getLength();

  default int getArea() {
    return getLength() * getLength();
  }
}
