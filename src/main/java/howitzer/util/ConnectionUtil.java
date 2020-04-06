
package howitzer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionUtil {

  final static Logger log = Logger.getLogger(ConnectionUtil.class.getName());

  /**
   * Get connection using MysqlDataSource.
   * 
   * @return
   * @throws SQLException
   */
  public static Connection getConnection() throws SQLException {

    MysqlDataSource dataSource = null;

    Connection conn = null;

    try {

      // Get data source
      dataSource = DataSourceMySQLSingleton.instance().getDataSource();

      // Get connection
      conn = dataSource.getConnection();

    } catch (SQLException e) {

      throw e;

    }

    return conn;

  }

  /**
   *  Close Connection
   *  
   * @param conn
   * @throws SQLException
   */
  public static void closeConn(Connection conn) throws SQLException {
    
    try {
      
      if (conn != null) {
        
        conn.close();
        
      }
      
    } catch (SQLException e) {
      
      log.error(e.getMessage());
      throw e;

    }
    
  }

  /**
   *  Close ResultSet
   *  
   * @param conn
   * @throws SQLException
   */
  public static void closeResultSet(ResultSet rs) throws SQLException {
    
    try {
      
      if (rs != null) {
        
        rs.close();
        
      }
      
    } catch (SQLException e) {
      
      log.error(e.getMessage());
      throw e;

    }
    
  }

  /**
   *  Close PreparedStatement
   *  
   * @param conn
   * @throws SQLException
   */
  public static void closePreparedStatement(PreparedStatement ps) throws SQLException {
    
    try {
      
      if (ps != null) {
        
        ps.close();
        
      }
      
    } catch (SQLException e) {
      
      log.error(e.getMessage());
      throw e;

    }
    
  }

}
