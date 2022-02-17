package de.scrum_master.stackoverflow.q56772468

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

class CdmFile {
  String readFileContents(Path filePath) {
    StringBuilder fileContents = new StringBuilder()
    BufferedReader br = createBufferedReader(filePath)
    String line
    while ((line = br.readLine()) != null) {
      fileContents.append(line).append('\n')
    }
    fileContents
  }

  void eachLineInFileAsString(
    Path filePath,
    @ClosureParams(value = SimpleType, options = ['java.lang.String']) Closure applyLine
  ) {
    BufferedReader br = createBufferedReader(filePath)
    String line
    while ((line = br.readLine()) != null) {
      applyLine.call(line)
    }
  }

  protected BufferedReader createBufferedReader(Path filePath) {
    Files.newBufferedReader(filePath, StandardCharsets.UTF_8)
  }
}
