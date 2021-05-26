package howitzer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import howitzer.beans.HowitzerHistory;
import howitzer.resources.HowitzerBundle;

@Repository
public class HowitzerHistoryDAO {

  final static Logger log = Logger.getLogger(HowitzerHistoryDAO.class.getName());

  /**
   * @param conn
   * @param userId
   * @param distanceToTarget
   * @param angle
   * @param velocity
   * @param targetSize
   * @param result
   * @param distanceTraveled
   * @param distanceMissedBy
   * @param timeTraveled
   * @return
   * @throws SQLException
   */
  public int insertHistory(Connection conn, String userId, double distanceToTarget, double angle,
      double velocity, double targetSize, String result, double distanceTraveled, double distanceMissedBy,
      double timeTraveled) 
          throws SQLException {

    int cntr = 0;

    PreparedStatement ps = null;

    try {

      // INSERT INTO HowitzerHistory (userId, distanceToTarget, angle, velocity, targetSize, result, 
      // distanceTraveled, distanceMissedBy, timeTraveled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
      ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("insert.history"));
      ps.setString(1, userId);
      ps.setDouble(2, distanceToTarget);
      ps.setDouble(3, angle);
      ps.setDouble(4, velocity);
      ps.setDouble(5, targetSize);
      ps.setString(6, result);
      ps.setDouble(7, distanceTraveled);
      ps.setDouble(8, distanceMissedBy);
      ps.setDouble(9, timeTraveled);

      // Insert row
      cntr = ps.executeUpdate();

    } catch (SQLException e) {

      throw e;

    } finally {

      try {

        // Close PreparedStatement
        ps.close();

      } catch (SQLException e) {

        log.error("Error: " + e.getMessage());

      }

    }

    return cntr;

  }

  /**
   * @param conn
   * @param schUserId
   * @return
   * @throws SQLException
   */
  public int getCount(Connection conn, String schUserId) throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // Number of rows
    int cntr = 0;

    try {

      // If no search
      if (schUserId.isEmpty()) {
        
        // SELECT COUNT(1) cntr FROM HowitzerHistory
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.history.count"));
        
      // If search user Id  
      } else {

        // SELECT COUNT(1) cntr FROM HowitzerHistory WHERE userId LIKE ?
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.history.count.search.user.id"));

        ps.setString(1, "%" + schUserId + "%");
        
      }

      rs = ps.executeQuery();

      // If result set is not empty
      if (rs.next()) {

        cntr = rs.getInt("CNTR") - 1;

      }

    } catch (SQLException e) {

      throw e;

    } finally {

      try {

        // Close PreparedStatement
        ps.close();

        if (rs != null) {

          // Close ResultSet
          rs.close();

        }

      } catch (SQLException e) {

        log.error("Error: " + e.getMessage());

      }

    }

    return cntr;

  }

  /**
   * @param conn
   * @param fRow
   * @param pagSiz
   * @param schUserId
   * @return
   * @throws SQLException
   */
  public List<HowitzerHistory> getHistory(Connection conn, int fRow, int pagSiz, String schUserId)
      throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // User id
    String userId = null;

    // Distance to target
    double distanceToTarget = 0;
    
    // Muzzle angle
    double angle = 0;
    
    // Muzzle velocity
    double velocity = 0;
    
    // Target Diameter
    double targetSize;
    
    // Hit or miss
    String result;
    
    // Distance traveled
    double distanceTraveled;
    
    // Distance missed by
    double distanceMissedBy;

    // Time traveled
    double timeTraveled;
    
    // Time stamp
    String timeStamp = null;

    // History
    List<HowitzerHistory> history = new ArrayList<HowitzerHistory>();

    try {

      // If no search
      if (schUserId.isEmpty()) {
        
        // SELECT userId, distanceToTarget, angle, velocity, targetSize, result, distanceTraveled, distanceMissedBy, 
        // timeTraveled, timeStamp from HowitzerHistory LIMIT ?, ?
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.history.paging"));

        ps.setInt(1, fRow);
        ps.setInt(2, pagSiz);
      
      // If search user id
      } else {

        // SELECT userId, distanceToTarget, angle, velocity, targetSize, result, distanceTraveled, distanceMissedBy, 
        // timeTraveled, timeStamp from HowitzerHistory WHERE userID LIKE ? LIMIT ?, ?
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.history.paging.search.user.id"));

        ps.setString(1, "%" + schUserId + "%");
        ps.setInt(2, fRow);
        ps.setInt(3, pagSiz);
        
      }

      rs = ps.executeQuery();

      while (rs.next()) {

        userId = rs.getString("userId");
        distanceToTarget = rs.getDouble("distanceToTarget");
        angle = rs.getDouble("angle");
        velocity = rs.getDouble("velocity");
        targetSize = rs.getDouble("targetSize");
        result = rs.getString("result");
        distanceTraveled = rs.getDouble("distanceTraveled");
        distanceMissedBy = rs.getDouble("distanceMissedBy");
        timeTraveled = rs.getDouble("timeTraveled");
        timeStamp = rs.getString("timeStamp");

        HowitzerHistory howitzerHistory = new HowitzerHistory(userId, distanceToTarget, angle, 
            velocity, targetSize, result, distanceTraveled, distanceMissedBy, timeTraveled, timeStamp);

        history.add(howitzerHistory);

      }

    } catch (SQLException e) {

      throw e;

    } finally {

      try {

        // Close PreparedStatement
        ps.close();

        if (rs != null) {

          // Close ResultSet
          rs.close();

        }

      } catch (SQLException e) {

        log.error("Error: " + e.getMessage());

      }

    }

    return history;

  }

}
