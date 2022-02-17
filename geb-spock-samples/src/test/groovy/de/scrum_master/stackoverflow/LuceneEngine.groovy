package de.scrum_master.stackoverflow

import org.apache.lucene.document.TextField
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexableField

class LuceneEngine {
  TextField textField
  TextField fileField
  IndexWriter indexWriter
  String currentFilename
  Iterable<? extends IndexableField> singleLDoc

  def processLineBatch(List lineBatch, int deliveryNo) {
    String lDocText = lineBatch.join('\n').trim()
    textField.setStringValue(lDocText)
    fileField.setStringValue(currentFilename)
    indexWriter.addDocument(singleLDoc)
  }
}
