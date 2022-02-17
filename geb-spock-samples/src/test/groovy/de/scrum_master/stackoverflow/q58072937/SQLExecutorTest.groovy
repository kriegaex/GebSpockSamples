package de.scrum_master.stackoverflow.q58072937

import spock.lang.Specification

import java.sql.SQLException
import java.sql.Statement

class SQLExecutorTest extends Specification {
  def test() {
    given:
    def sqlExecutor = new SQLExecutor()
    def statement = Mock(Statement) {
      executeUpdate(_) >> {
        throw new SQLException("uh-oh")
      }
    }

    when:
    sqlExecutor.executeDataLoad("dummy", statement)

    then:
    def exception = thrown RuntimeException
    exception.cause instanceof SQLException
  }
}
