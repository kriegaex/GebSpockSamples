package de.scrum_master.stackoverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LambdaArgument {
  AnotherObj anotherObj = new AnotherObj();
  Caller caller = new Caller();

  public List<String> fetchProducts() {
    String userId = anotherObj.getId();
    return caller.call(client -> {
      return client.getProducts(userId);
    });
  }

  public static class Caller {
    public List<String> call(Function<Client, List<String>> function) {
      return function.apply(new Client());
    }
  }

  public static class Client {
    public List<String> getProducts(String userId) {
      List<String> products = new ArrayList<>();
      products.add(userId);
      products.add("Two");
      products.add("Three");
      return products;
    }
  }

  public static class AnotherObj {
    public String getId() {
      return "AB-123";
    }
  }
}
