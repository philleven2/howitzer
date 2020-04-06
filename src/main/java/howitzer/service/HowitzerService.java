package howitzer.service;

import java.sql.SQLException;
import howitzer.beans.FireShot;

public interface HowitzerService {

  public FireShot fireShot(FireShot fireShot) 
          throws SQLException;
  
}
