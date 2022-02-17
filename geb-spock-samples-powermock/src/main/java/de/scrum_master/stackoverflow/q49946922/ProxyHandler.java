package de.scrum_master.stackoverflow.q49946922;

public class ProxyHandler {
  public void process(String str) {
    String[] params = str.split(" ");
    PersistManager.proxy(params[0], params[1], params[2]);
  }
}
