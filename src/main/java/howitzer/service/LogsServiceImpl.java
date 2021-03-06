package howitzer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import howitzer.beans.Logs;
import howitzer.dao.LogsDAO;
import howitzer.exception.HowitzerException;

@Service("logsService")
public class LogsServiceImpl implements LogsService {

  private static final Logger log = Logger.getLogger(LogsServiceImpl.class.getName());

  private LogsDAO logsDAO;

  /**
   * Default Constructor.
   */
  @Autowired
  public LogsServiceImpl(LogsDAO logsDAO) {

    this.logsDAO = logsDAO;
    
  }

  @Override
  public int getCount(Connection conn, String schText) {

    int cntr = 0;

    try {

      // Get number of rows
      cntr = logsDAO.getCount(conn, schText);

    } catch (SQLException | HowitzerException e) {

      log.error(e.getMessage(), e);
      e.printStackTrace();

    }

    return cntr;

  }

  @Override
  public List<Logs> getLogs(Connection conn, int fRow, int pagSiz, String schText) {

    List<Logs> logs = null;

    try {

      // Get logs
      logs = logsDAO.getLogs(conn, fRow, pagSiz, schText);

    } catch (SQLException | HowitzerException e) {

      log.error(e.getMessage(), e);
      e.printStackTrace();

    }

    return logs;

  }

  @Override
  public void truncateLogs(Connection conn) throws SQLException {
    
    try {

      // Truncate logs
      logsDAO.truncateLogs(conn);

    } catch (SQLException e) {

      log.error(e.getMessage(), e);
      e.printStackTrace();

    }

  }

}