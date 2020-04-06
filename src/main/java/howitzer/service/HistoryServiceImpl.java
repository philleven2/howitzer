package howitzer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import howitzer.beans.HowitzerHistory;
import howitzer.dao.HowitzerHistoryDAO;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

  final static Logger log = Logger.getLogger(HistoryServiceImpl.class.getName());

  // Create HowitzerHistoryDAO
  HowitzerHistoryDAO howitzerHistoryDAO = new HowitzerHistoryDAO();

  /**
   * Default Constructor.
   */
  public HistoryServiceImpl() {

    // set default values

    log.debug("Created instance: " + this.toString());

  }

  @Override
  public int getCount(Connection conn) 
      throws SQLException {

    int cntr = 0;

    try {

      // Get number of rows
      cntr = howitzerHistoryDAO.getCount(conn);

    } catch (SQLException e) {

      throw e;

    }

    return cntr;

  }

  @Override
  public List<HowitzerHistory> getHistory(Connection conn, int fRow, int pagSiz)
      throws SQLException {

    List<HowitzerHistory> howitzerHistory = null;

    try {

      // Get history
      howitzerHistory = howitzerHistoryDAO.getHistory(conn, fRow, pagSiz);

    } catch (SQLException e) {

      throw e;

    }

    return howitzerHistory;

  }

}
