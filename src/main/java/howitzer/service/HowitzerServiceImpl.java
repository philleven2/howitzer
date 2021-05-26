package howitzer.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import howitzer.beans.FireShot;
import howitzer.beans.Users;
import howitzer.dao.HowitzerHistoryDAO;
import howitzer.dao.UsersDAO;
import howitzer.util.ConnectionUtil;

@Service("howitzerService")
public class HowitzerServiceImpl implements HowitzerService {

  final static Logger log = Logger.getLogger(HowitzerServiceImpl.class.getName());

  private UserService userService;
  private HowitzerHistoryDAO howitzerHistoryDAO;
  private UsersDAO usersDAO;
  private ConnectionUtil connectionUtil;

  /**
   * Default Constructor.
   */
  @Autowired
  public HowitzerServiceImpl(UserService userService, HowitzerHistoryDAO howitzerHistoryDAO, 
      UsersDAO usersDAO, ConnectionUtil connectionUtil) {

    this.userService = userService;
    this.howitzerHistoryDAO = howitzerHistoryDAO;
    this.usersDAO = usersDAO;
    this.connectionUtil = connectionUtil;

  }

  @Override
  public FireShot fireShot(FireShot fireShot) 
      throws SQLException {
    
    // User
    String userId = fireShot.getUserId();
    int shots = 0;
    int hits = 0;
    int misses = 0;
    int rank = 0;

    // Distance to target (meters)
    double distance = 0;
    
    // Muzzle angle (degrees)
    double angle = 0;
    
    // Initial muzzle velocity (meters/second)
    double velocity = 0;
    
    // Target diameter (meters)
    double targetSize = 0;
    
    // Gravity (meters/second^2)
    final double gravity = -9.81;
    
    // Steps
    final int steps = 10;

    // Horizontal distance traveled (meters)
    double x = 0;
    
    // Vertical distance traveled (meters)
    double y = 0;
    
    // Time passed (seconds)
    double time = 0;

    // Result
    String result = "miss";
    
    // Distance missed by (meters)
    double distanceMissedBy = 0;

    StringBuilder sb = new StringBuilder();
    
    try (Connection conn = connectionUtil.getConnection();) {
      
      // Get distance to target (meters)
      distance = fireShot.getDistanceToTarget();

      // Get muzzle angle
      angle = Math.toRadians(fireShot.getAngle());

      // Get initial muzzle velocity
      velocity = fireShot.getVelocity();
      
      // Get target diameter (meters)
      targetSize = fireShot.getTargetSize();

      // Horizontal velocity = velocity * cos(angle)
      double xVelocity = velocity * Math.cos(angle);
      
      // Vertical velocity = velocity * sin(angle)
      double yVelocity = velocity * Math.sin(angle);
      
      // Time  
      double totalTime = - 2.0 * yVelocity / gravity;
      
      // Time increment
      double timeIncrement = totalTime / steps;
      
      // Horizontal distance increment
      double xIncrement = xVelocity * timeIncrement;
      
      // Trajectory header
      sb.append("<table class='table table-striped custom' style='margin-top: 20px; font-size: 10px; width: 50% !important'>");
      sb.append("<tbody>");
      sb.append("<tr>");
      sb.append("<td align='right'>step</td>");
      sb.append("<td align='right'>x</td>");
      sb.append("<td align='right'>y</td>");
      sb.append("<td align='right'>t</td>");
      sb.append("</tr>");
      
      for (int i = 1; i <= steps; i++) {
        
          time += timeIncrement;
          x += xIncrement;
          y = yVelocity * time + 0.5 * gravity * time * time;
          
          // Trajectory step
          sb.append("<tr>");
          sb.append("<td align='right'>");
          sb.append(i);
          sb.append("</td>");
          
          sb.append("<td align='right'>");
          sb.append(round2(x));
          sb.append("</td>");

          sb.append("<td align='right'>");
          sb.append(round2(y));
          sb.append("</td>");

          sb.append("<td align='right'>");
          sb.append(round2(time));
          sb.append("</td>");

          sb.append("</tr>");

      }

      sb.append("</tbody>");
      sb.append("</table>");

      // Calculate radius of target
      double radius = targetSize / 2;
      
      // If hit
      if (x == distance || (x >= (distance - radius) && x <= (distance + radius))) {
        
        result = "hit";
        
      // If miss
      } else {
        
        distanceMissedBy = distance - x;
        
      }

      // Insert HowitzerHistory row
      howitzerHistoryDAO.insertHistory(conn, userId, fireShot.getDistanceToTarget(), 
          angle, velocity, targetSize, result, x, distanceMissedBy, time);
      
      // Get user row
      Users user = usersDAO.getUser(conn, userId);
      
      // Increment shots
      shots = user.getShots() + 1;

      // Increment hits and misses
      // If hit
      if (result.equals("hit")) {

        hits = user.getHits() + 1;
        misses = user.getMisses();

      // If miss 
      } else {

        misses = user.getMisses() + 1;
        hits = user.getHits();
        
      }

      // Calculate avg hits
      double numberOfHits = hits;
      double avg = numberOfHits / shots;
      
      // Calculate rank
      rank = userService.calcRank(conn, userId);
      
      // Update user
      usersDAO.updateUser(conn, userId, shots, hits, misses, BigDecimal.valueOf(avg), rank);
      
    } catch (SQLException e) {
      
      log.error("Error: " + e.getMessage());
      
    }
    
    fireShot.setResult(result);
    fireShot.setDistanceTraveled(x);
    fireShot.setDistanceMissedBy(distanceMissedBy);
    fireShot.setTimeTraveled(time);
    fireShot.setRank(rank);
    fireShot.setTrajectory(sb.toString());
    
    return fireShot;
    
  }

  public static double round2(double n) {
    
    return (int) (n * 100.0 + 0.5) / 100.0;
    
  }

}
