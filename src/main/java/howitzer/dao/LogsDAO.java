package howitzer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import howitzer.beans.Logs;
import howitzer.exception.HowitzerException;
import howitzer.resources.HowitzerBundle;
import howitzer.util.ConnectionUtil;

public class LogsDAO {

  private static final Logger log = Logger.getLogger(LogsDAO.class.getName());

  /**
   * @param conn
   * @param schText
   * @return
   * @throws SQLException
   */
  public int getCount(Connection conn, String schText) throws SQLException, HowitzerException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // Number of rows
    int cntr = 0;

    try {

      // If no search
      if (schText.isEmpty()) {

        // SELECT COUNT(1) cntr FROM Logs
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.logs.count"));

      // If search text
      } else {
        
        // SELECT COUNT(1) cntr FROM Logs WHERE message LIKE ?
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.logs.count.search.text"));

        ps.setString(1, "%" + schText + "%");
        
      }

      rs = ps.executeQuery();

      // If result set is not empty
      if (rs.next()) {

        cntr = rs.getInt("CNTR") - 1;

      }

    } catch (SQLException e) {

      log.error("Error: " + e.getMessage());
      e.printStackTrace();

      throw e;

    } finally {

      // Close PreparedStatement
      ConnectionUtil.closePreparedStatement(ps);

      // Close ResultSet
      ConnectionUtil.closeResultSet(rs);

    }

    return cntr;

  }

  /**
   * @param conn
   * @param fRow
   * @param pagSiz
   * @param schText
   * @return
   * @throws SQLException
   */
  public List<Logs> getLogs(Connection conn, int fRow, int pagSiz, String schText)
      throws SQLException, HowitzerException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // Logs
    String dateCreated = null;
    String logger = null;
    String level = null;
    String message = null;

    // Logs
    List<Logs> logs = new ArrayList<Logs>();

    try {

      // If no search
      if (schText.isEmpty()) {

        // SELECT dateCreated, logger, level, message FROM Logs ORDER BY dateCreated DESC LIMIT ?, ?
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.logs.paging"));

        ps.setInt(1, fRow);
        ps.setInt(2, pagSiz);

      // If search text
      } else {

        // SELECT dateCreated, logger, level, message FROM Logs WHERE message LIKE ? ORDER BY dateCreated DESC LIMIT ?, ?
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.logs.paging.search.text"));

        ps.setString(1, "%" + schText + "%");
        ps.setInt(2, fRow);
        ps.setInt(3, pagSiz);
        
      }

      rs = ps.executeQuery();

      while (rs.next()) {

        dateCreated = rs.getString("dateCreated");
        logger = rs.getString("logger");
        level = rs.getString("level");
        message = rs.getString("message");

        Logs log = new Logs(dateCreated, logger, level, message);

        logs.add(log);

      }

    } catch (SQLException e) {

      log.error("Error: " + e.getMessage());
      e.printStackTrace();

      throw e;

    } finally {

      // Close PreparedStatement
      ConnectionUtil.closePreparedStatement(ps);

      // Close ResultSet
      ConnectionUtil.closeResultSet(rs);

    }

    return logs;

  }

  /**
   * @param conn
   * @throws SQLException
   */
  public void truncateLogs(Connection conn) throws SQLException {
    
    PreparedStatement ps = null;
    
    String sql = null;

    try {
      
      sql = "SET FOREIGN_KEY_CHECKS = 0;";
      ps = conn.prepareStatement(sql);
      ps.executeUpdate();
      
      // Truncate Logs
      sql = "TRUNCATE TABLE Logs";
      ps = conn.prepareStatement(sql);
      ps.executeUpdate();

      sql = "SET FOREIGN_KEY_CHECKS = 1;";
      ps = conn.prepareStatement(sql);
      ps.executeUpdate();

    } catch(SQLException e) {
      
      log.error("Error: " + e.getMessage());
      e.printStackTrace();

      throw e;

    } finally {
      
      // Close PreparedStatement
      ConnectionUtil.closePreparedStatement(ps);
      
    }
    
  }
  
}