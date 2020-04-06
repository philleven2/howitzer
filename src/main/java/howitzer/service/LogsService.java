package howitzer.service;

import java.sql.Connection;
import java.util.List;
import howitzer.beans.Logs;

public interface LogsService {

  public int getCount(Connection conn, String schText);

  public List<Logs> getLogs(Connection conn, int fRow, int pagSiz, String schText);

}