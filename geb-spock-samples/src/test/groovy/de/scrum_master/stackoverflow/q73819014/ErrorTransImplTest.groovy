package de.scrum_master.stackoverflow.q73819014

import org.apache.log4j.Appender
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.springframework.dao.DataAccessException
import spock.lang.Ignore
import spock.lang.Specification

class ErrorTransImplTest extends Specification {
  def 'throw DataAccess Exception upon incorrect update'() {
    given:
    def log = Mock(Logger)
    def jdbcTemplate = Mock(JdbcTemplate) {
      update(_, _) >> { throw new DataAccessException("uh-oh") }
    }
    def originalLogger = ErrorTransImpl.logger
    ErrorTransImpl.logger = log
    def errTransImpl = new ErrorTransImpl()
    errTransImpl.jdbcTemplate = jdbcTemplate

    when:
    errTransImpl.errorCatcher(new ErrorTrans())

    then:
    1 * log.error(_)

    cleanup:
    ErrorTransImpl.logger = originalLogger
  }

  @Ignore('Appender accessor methods are no-ops in SLF4J')
  def 'throw DataAccess Exception upon incorrect update_1'() {
    setup:
    def appender = Mock(Appender)
    def logger = Logger.getLogger(ErrorTransImpl)
    logger.setLevel(Level.ERROR)
    println logger.getAllAppenders().collect().size()
    logger.addAppender(appender)
    println logger.getAllAppenders().collect().size()
    def jdbcTemplate = Mock(JdbcTemplate) {
      update(_, _) >> { throw new DataAccessException("uh-oh") }
    }
    def errTransImpl = new ErrorTransImpl()
    errTransImpl.jdbcTemplate = jdbcTemplate

    when:
    errTransImpl.errorCatcher(new ErrorTrans())

    then:
    1 * appender._(*_)
  }

}
