package howitzer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import howitzer.beans.Users;

public interface UserService {

  public int getCount(Connection conn) 
      throws SQLException;

  public List<Users> getUsers(Connection conn)
      throws SQLException;

  public List<Users> getUsersRanksPaging(Connection conn, int fRow, int pagSiz)
      throws SQLException;

  public int calcRank(Connection conn, String userId)
    throws SQLException;
  
  public void rankUsers(Connection conn)
    throws SQLException;
  
}
