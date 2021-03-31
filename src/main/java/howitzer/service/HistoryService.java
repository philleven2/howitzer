package howitzer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import howitzer.beans.HowitzerHistory;

public interface HistoryService {

  public int getCount(Connection conn, String schUserId) 
      throws SQLException;

  public List<HowitzerHistory> getHistory(Connection conn, int fRow, int pagSiz, String schUserId)
      throws SQLException;
  
}
