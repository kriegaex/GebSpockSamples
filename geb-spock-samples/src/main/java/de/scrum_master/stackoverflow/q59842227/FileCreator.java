package de.scrum_master.stackoverflow.q59842227;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FileCreator {
  private static final Random RANDOM = new Random();

  public boolean createIndexInfoFile(File indexInfoFile) throws IOException {
    if (indexInfoFile.createNewFile()) {
      System.out.println("File \"" + indexInfoFile + "\" created");
      return true;
    }
    System.out.println("File \"" + indexInfoFile + "\" NOT created");
    return false;
  }

  public static void main(String[] args) throws IOException {
    new FileCreator().createIndexInfoFile(
      new File("_abc_" + RANDOM.nextInt(10000) + ".txt")
    );
  }
}
