package de.scrum_master.stackoverflow.q55914920;

class ServiceClass {
  private CacheService cacheService = new CacheService();

  public String doSomething(String id) {
    CacheObj obj = cacheService.get(id);
    // while executing this, val is returning **null**, but it should ideally return "test"
    // as it is stubbed in specification class
    String val = obj.getValue("thisID");
    return val;
  }
}
