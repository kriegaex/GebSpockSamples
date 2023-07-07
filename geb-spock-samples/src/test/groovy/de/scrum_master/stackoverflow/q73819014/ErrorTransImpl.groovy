package de.scrum_master.stackoverflow.q73819014

import org.apache.log4j.Logger
import org.springframework.dao.DataAccessException

class ErrorTransImpl {
  private static /*final*/ Logger logger = Logger.getLogger(ErrorTransImpl)

  JdbcTemplate jdbcTemplate

  int errorCatcher(ErrorTrans transError) {
    int ct = 0
    if (transError != null) {
      String query = "INSERT INTO tab_1 (rw1,rw2,rw3,rw4,rw5) VALUES (?,?,?,?,?)"
      try {
        ct = jdbcTemplate.update(query, [transError.col1(), transError.col2(), transError.col3(), transError.col4(), transError.col5()])
      } catch (DataAccessException ex) {
        logger.error(ex)
      }
    }
    return ct
  }

  void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate
  }
}
