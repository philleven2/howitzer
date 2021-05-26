package howitzer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import howitzer.beans.HowitzerHistory;
import howitzer.dao.HowitzerHistoryDAO;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

  final static Logger log = Logger.getLogger(HistoryServiceImpl.class.getName());

  private HowitzerHistoryDAO howitzerHistoryDAO;

  /**
   * Default Constructor.
   */
  @Autowired
  public HistoryServiceImpl(HowitzerHistoryDAO howitzerHistoryDAO) {

    this.howitzerHistoryDAO = howitzerHistoryDAO; 
  }

  @Override
  public int getCount(Connection conn, String schUserId) 
      throws SQLException {

    int cntr = 0;

    try {

      // Get number of rows
      cntr = howitzerHistoryDAO.getCount(conn, schUserId);

    } catch (SQLException e) {

      throw e;

    }

    return cntr;

  }

  @Override
  public List<HowitzerHistory> getHistory(Connection conn, int fRow, int pagSiz, String schUserId)
      throws SQLException {

    List<HowitzerHistory> howitzerHistory = null;

    try {

      // Get history
      howitzerHistory = howitzerHistoryDAO.getHistory(conn, fRow, pagSiz, schUserId);

    } catch (SQLException e) {

      throw e;

    }

    return howitzerHistory;

  }

}
