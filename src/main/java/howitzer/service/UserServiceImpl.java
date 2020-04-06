package howitzer.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import howitzer.beans.Users;
import howitzer.dao.UsersDAO;

@Service("userService")
public class UserServiceImpl implements UserService {

  final static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

  // Create UsersDAO
  UsersDAO usersDAO = new UsersDAO();

  /**
   * Default Constructor.
   */
  public UserServiceImpl() {

    // set default values

    log.debug("Created instance: " + this.toString());

  }

  @Override
  public int getCount(Connection conn) 
      throws SQLException {

    int cntr = 0;

    try {

      // Get number of rows
      cntr = usersDAO.getCount(conn);

    } catch (SQLException e) {

      throw e;

    }

    return cntr;

  }

  @Override
  public List<Users> getUsers(Connection conn)
      throws SQLException {

    List<Users> users = null;

    try {

      // Get users
      users = usersDAO.getUsers(conn);

    } catch (SQLException e) {

      throw e;

    }

    return users;

  }

  @Override
  public List<Users> getUsersRanksPaging(Connection conn, int fRow, int pagSiz)
      throws SQLException {

    List<Users> users = null;

    try {

      // Get users
      users = usersDAO.getUsersRanksPaging(conn, fRow, pagSiz);

    } catch (SQLException e) {

      throw e;

    }

    return users;

  }

  @Override
  public int calcRank(Connection conn, String userId)
    throws SQLException {
    
    // Rank
    int rank = 0;
    
    // Users average hits
    BigDecimal userAvgHits;
    double avgHits = 0;
    
    // Users
    List<Users> users = new ArrayList<Users>();

    try {

      // Get users average hits
      avgHits = usersDAO.getUserAvg(conn, userId);
      
      // Get users
      users = usersDAO.getUsers(conn);
      
      // Iterate over users
      for (int i = 0; i < users.size(); i++) {
        
        // Get user avg hits
        userAvgHits = users.get(i).getAvgHits();
        double userAvg = userAvgHits.doubleValue();
        
        // If users avg hits found
        if (avgHits == userAvg) {
          
          rank = i + 1;
          break;
          
        }
        
      } // Iterate over users
      
    } catch (SQLException e) {
      
      log.error("Error: " + e.getMessage());
      
    }

    return rank; 
    
  }

  @Override
  public void rankUsers(Connection conn)
      throws SQLException {
      
      // Rank
      int rank = 0;
      
      // Average hits
      BigDecimal userAvgHits;
      double avgHits = 0;
      double lastAvgHits = -1;
      
      // Users
      List<Users> users = new ArrayList<Users>();

      try {

        // Get users
        users = usersDAO.getUsersRanks(conn);
        
        // Iterate over users
        for (int i = 0; i < users.size(); i++) {
          
          // Get user
          Users user = users.get(i);
          
          // Get user avg hits
          userAvgHits = user.getAvgHits();
          avgHits = userAvgHits.doubleValue();
          
          // If avg hits has changed
          if (avgHits != lastAvgHits) {
            
            rank = i + 1;
            
          }

          // Update users rank
          usersDAO.updateUserRank(conn, user.getUserId(), rank);

          lastAvgHits = avgHits;

        } // Iterate over users
        
      } catch (SQLException e) {
        
        log.error("Error: " + e.getMessage());
        
      }
      
    }

}
