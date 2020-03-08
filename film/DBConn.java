package film;

import java.sql.*;

public abstract class DBConn {
  protected Connection conn;
  private static String url = "jdbc:mysql://localhost:3306/filmdb";

  public DBConn() {}

  public void connect() {
    try {
      conn = DriverManager.getConnection(url);
    } catch (Exception e) {
      throw new RuntimeException("Could not connect to database", e);
    }
  }
}
