package howitzer.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import howitzer.beans.Users;
import howitzer.resources.HowitzerBundle;
import howitzer.util.ConnectionUtil;

@Repository
public class UsersDAO {

  final static Logger log = Logger.getLogger(UsersDAO.class.getName());

  private ConnectionUtil connectionUtil;
  
  @Autowired
  public UsersDAO (ConnectionUtil connectionUtil) {
    
    this.connectionUtil = connectionUtil;
    
  }
  
  /**
   * @param conn
   * @return
   * @throws SQLException
   */
  public int getCount(Connection conn) throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // Number of rows
    int cntr = 0;

    try {

        // SELECT COUNT(1) cntr FROM Users
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.users.count"));

      rs = ps.executeQuery();

      // If result set is not empty
      if (rs.next()) {

        cntr = rs.getInt("CNTR");

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
   * @return
   * @throws SQLException
   */
  public List<Users> getUsers(Connection conn)
      throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // User id
    String userId = null;

    // Shots
    int shots = 0;
    
    // Hits
    int hits = 0;
    
    // Misses
    int misses = 0;
    
    // Avg hits
    BigDecimal avgHits;
    
    // Rank
    int rank;
    
    // Users
    List<Users> users = new ArrayList<Users>();

    try {

        // SELECT userId, shots, hits, misses, avgHits, userRank from Users;
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.users"));

        rs = ps.executeQuery();

        while (rs.next()) {

          userId = rs.getString("userId");
          shots = rs.getInt("shots");
          hits = rs.getInt("hits");
          misses = rs.getInt("misses");
          avgHits = rs.getBigDecimal("avgHits");
          rank = rs.getInt("userRank");

          Users user = new Users(userId, shots, hits, misses, avgHits, rank);

          users.add(user);

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

    return users;

  }

  /**
   * @param conn
   * @return
   * @throws SQLException
   */
  public List<Users> getUsersRanks(Connection conn)
      throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // User id
    String userId = null;

    // Shots
    int shots = 0;
    
    // Hits
    int hits = 0;
    
    // Misses
    int misses = 0;
    
    // Avg hits
    BigDecimal avgHits;
    
    // Rank
    int rank;
    
    // Users
    List<Users> users = new ArrayList<Users>();

    try {

        // SELECT userId, shots, hits, misses, avgHits, userRank from Users order by avgHits DESC;
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.users.ranks"));

        rs = ps.executeQuery();

        while (rs.next()) {

          userId = rs.getString("userId");
          shots = rs.getInt("shots");
          hits = rs.getInt("hits");
          misses = rs.getInt("misses");
          avgHits = rs.getBigDecimal("avgHits");
          rank = rs.getInt("userRank");

          Users user = new Users(userId, shots, hits, misses, avgHits, rank);

          users.add(user);

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

    return users;

  }

  /**
   * @param conn
   * @param frow
   * @param pagSiz
   * @return
   * @throws SQLException
   */
  public List<Users> getUsersRanksPaging(Connection conn, int fRow, int pagSiz)
      throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // User id
    String userId = null;

    // Shots
    int shots = 0;
    
    // Hits
    int hits = 0;
    
    // Misses
    int misses = 0;
    
    // Avg hits
    BigDecimal avgHits;
    
    // Rank
    int rank;
    
    // Users
    List<Users> users = new ArrayList<Users>();

    try {

        // SELECT userId, shots, hits, misses, avgHits, userRank from Users order by avgHits DESC, userId ASC LIMIT ?, ?;
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.users.ranks.paging"));

        ps.setInt(1, fRow);
        ps.setInt(2, pagSiz);

        rs = ps.executeQuery();

        while (rs.next()) {

          userId = rs.getString("userId");
          shots = rs.getInt("shots");
          hits = rs.getInt("hits");
          misses = rs.getInt("misses");
          avgHits = rs.getBigDecimal("avgHits");
          rank = rs.getInt("userRank");

          Users user = new Users(userId, shots, hits, misses, avgHits, rank);

          users.add(user);

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

    return users;

  }

  /**
   * @param conn
   * @param userId
   * @return
   * @throws SQLException
   */
  public Users getUser(Connection conn, String userId)
      throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // Shots
    int shots = 0;
    
    // Hits
    int hits = 0;
    
    // Misses
    int misses = 0;
    
    // Avg hits
    BigDecimal avgHits;
    
    // Rank
    int rank;

    // Users
    Users user = new Users();

    try {

        // SELECT userId, shots, hits, misses, avgHits, userRank from Users WHERE userId = ?;
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.user"));

        ps.setString(1, userId);
        
        rs = ps.executeQuery();

        if (rs.next()) {

          userId = rs.getString("userId");
          shots = rs.getInt("shots");
          hits = rs.getInt("hits");
          misses = rs.getInt("misses");
          avgHits = rs.getBigDecimal("avgHits");
          rank = rs.getInt("userRank");

          user = new Users(userId, shots, hits, misses, avgHits, rank);

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

    return user;

  }

  /**
   * @param conn
   * @param userId
   * @return
   * @throws SQLException
   */
  public double getUserAvg(Connection conn, String userId)
      throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;

    // Avg hits
    double avgHits = 0;
    
    try {

        // SELECT avgHits from Users WHERE UserId = ?;
        ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("get.users"));

        rs = ps.executeQuery();

        if (rs.next()) {

          avgHits = rs.getDouble("avgHits");

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

    return avgHits;

  }

  /**
   * @param conn
   * @param userId
   * @param shots
   * @param hits
   * @param misses
   * @param avgHits
   * @param rank
   * @return
   * @throws SQLException
   */
  public int updateUser(Connection conn, String userId, int shots, int hits, int misses, BigDecimal avgHits,
      int rank) 
      throws SQLException {

    int cntr = 0;

    PreparedStatement ps = null;

    try {

      // UPDATE Users SET shots = ?, hits = ?, misses = ?, avgHits = ?, userRank = ? WHERE userId = ?
      ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("update.user"));
      ps.setInt(1, shots);
      ps.setInt(2, hits);
      ps.setInt(3, misses);
      ps.setBigDecimal(4,avgHits);
      ps.setInt(5, rank);
      ps.setString(6, userId);

      // Update row
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
   * @param userId
   * @param rank
   * @return
   * @throws SQLException
   */
  public int updateUserRank(Connection conn, String userId, int rank) 
      throws SQLException {

    int cntr = 0;

    PreparedStatement ps = null;

    try {

      // UPDATE Users SET userRank = ? WHERE userId = ?
      ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("update.user.rank"));
      ps.setInt(1, rank);
      ps.setString(2, userId);

      // Update row
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
   * @param userId
   * @return
   * @throws SQLException
   */
  public boolean doesUserExist(Connection conn, String userId) throws SQLException {
    
    PreparedStatement ps = null;
    ResultSet rs = null;

    boolean userExists = false;

    try {

      // SELECT userId FROM Users WHERE userId = ?
      ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("does.user.exist"));
      ps.setString(1, userId);

      rs = ps.executeQuery();

      if (rs.next()) {

        userExists = true;
        
      }

    } catch (SQLException e) {

      log.error("Error: " + e.getMessage());
      e.printStackTrace();

      throw e;

    } finally {
      
      // Close PreparedStatement
      connectionUtil.closePreparedStatement(ps);

      // Close ResultSet
      connectionUtil.closeResultSet(rs);

    }

    return userExists;

  }

  /**
   * @param conn
   * @param userId
   * @param shots
   * @param hits
   * @param misses
   * @param avgHits
   * @param userRank
   * @return
   * @throws SQLException
   */
  public int insertUser(Connection conn, String userId, int shots, int hits, int misses,
      BigDecimal avgHits, int userRank) throws SQLException {

    PreparedStatement ps = null;

    int cntr = 0;

    try {

      // INSERT INTO Users (userId, shots, hits, misses, avgHits, userRank) VALUES(?, ?, ?, ?, ?, ?)
      ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("insert.user"));
      ps.setString(1, userId);
      ps.setInt(2, shots);
      ps.setInt(3, hits);
      ps.setInt(4, misses);
      ps.setBigDecimal(5, avgHits);
      ps.setInt(6, userRank);

      // Insert row
      cntr = ps.executeUpdate();

    } catch (SQLException e) {

      log.error("Error: " + e.getMessage());
      e.printStackTrace();

      throw e;

    } finally {
      
      // Close PreparedStatement
      connectionUtil.closePreparedStatement(ps);

    }

    return cntr;

  }

  /**
   * @param conn
   * @param userId
   * @return
   * @throws SQLException
   */
  public int deleteUser(Connection conn, String userId) throws SQLException {

    PreparedStatement ps = null;
    PreparedStatement ps2 = null;

    int cntr = 0;

    try {

      // DELETE FROM HowitzerHistory WHERE userId = ?
      ps2 = conn.prepareStatement(HowitzerBundle.getValueForKey2("delete.history"));
      ps2.setString(1, userId);

      // Delete row
      cntr = ps2.executeUpdate();

      
      // DELETE FROM Users WHERE userId = ?
      ps = conn.prepareStatement(HowitzerBundle.getValueForKey2("delete.user"));
      ps.setString(1, userId);

      // Delete row
      cntr = ps.executeUpdate();

      // Commit transaction
      conn.commit();

    } catch (SQLException e) {

      // Roll back transaction
      conn.rollback();

      log.error("Error: " + e.getMessage());
      e.printStackTrace();

      throw e;

    } finally {
      
      // Close PreparedStatements
      connectionUtil.closePreparedStatement(ps);
      connectionUtil.closePreparedStatement(ps2);

    }

    return cntr;

  }

}