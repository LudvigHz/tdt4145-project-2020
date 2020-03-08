package film;

import java.sql.*;

public abstract class DBConn {
  protected Connection conn;

  public DBConn() {}

  public void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      // TODO
    } catch (Exception e) {
      throw new RuntimeException("Could not connect to database", e);
    }
  }
}
