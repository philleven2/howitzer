
package howitzer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariDataSource;
import howitzer.resources.HowitzerBundle;

@Service
public class ConnectionUtil {

  final static Logger log = Logger.getLogger(ConnectionUtil.class.getName());

  /**
   * Get connection using MysqlDataSource.
   * 
   * @return
   * @throws SQLException
   */
  public Connection getConnection() throws SQLException {

    Connection conn = null;

    try {

      // MySQL database
      String database = "Howitzer";

      // Connection pool
      String connPool = HowitzerBundle.getValueForKey("mysql.connection.pool");

      // If Hikari
      if (connPool.equals("hikari")) {

        conn = getConnectionHikari(database);
        
      } else {
        
        conn = getConnectionMySQL(database);
        
      }

    } catch (SQLException e) {

      throw e;

    }

    return conn;

  }

  /**
   * Get connection using MysqlDataSource.
   * 
   * @param database
   * @return
   * @throws SQLException
   */
  public Connection getConnectionMySQL(String database) throws SQLException {

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
   * Get connection using Hikari.
   * 
   * @param dataBase
   * @return
   * @throws SQLException
   */
  public Connection getConnectionHikari(String database) throws SQLException {

    HikariDataSource pooled = null;

    Connection conn = null;

    try {

        pooled = DataSourceHikariSingleton.instance().getPooledDataSource();

      // Get connection
      conn = pooled.getConnection();

    } catch (NullPointerException | SQLException e) {

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
  public void closeConn(Connection conn) throws SQLException {
    
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
  public void closeResultSet(ResultSet rs) throws SQLException {
    
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
  public void closePreparedStatement(PreparedStatement ps) throws SQLException {
    
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
