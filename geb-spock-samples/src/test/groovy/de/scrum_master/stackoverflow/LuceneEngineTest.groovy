package de.scrum_master.stackoverflow

import org.apache.lucene.document.TextField
import org.apache.lucene.index.IndexWriter
import spock.lang.Specification

class LuceneEngineTest extends Specification {
  def "fileField should be set for each line batch"() {
    given:
    LuceneEngine le = new LuceneEngine()
    le.indexWriter = Mock(IndexWriter) {
      addDocument(_) >> null
    }
    le.currentFilename = 'dummy filename'
    TextField textField = GroovyMock(global: true)
    le.fileField = le.textField = textField

    when:
    le.processLineBatch(['dummy text'], 0)

    then:
    1 * le.fileField.setStringValue('dummy filename') >> null
  }
}
