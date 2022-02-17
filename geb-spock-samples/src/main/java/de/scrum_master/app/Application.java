package de.scrum_master.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Application {
  public static void main(String[] args) throws IOException {
    readResource();
  }

  private static void readResource() throws IOException {
    try (
      BufferedReader buffer = new BufferedReader(
        new InputStreamReader(
          Application.class.getClassLoader().getResourceAsStream("resource.txt")
        )
      )
    ) {
      System.out.println(buffer.lines().collect(Collectors.joining("\n")));
    }
  }
}
