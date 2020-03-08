import java.sql.*;

public abstract class DBConn {
  public static Connection conn;
  private static String DB_URL = "jdbc:mysql://localhost:3306/";
  private static String DB_NAME = "filmdb";
  private static String DB_USER = "root";
  private static String DB_PASS = "root";

  public DBConn() {}

  private void createDb(String name) {
    try {
      System.out.printf("Creating database %s%n ...\n\n", name);
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      Statement stmt = conn.createStatement();
      stmt.execute("create database " + name);
      conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASS);
    } catch (Exception e) {
      throw new RuntimeException("Could not create database", e);
    }
  }

  public void connect() {
    try {
      conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASS);
    } catch (Exception e) {
      System.out.println("Could not connect to database:" + e.toString());
      // throw new RuntimeException("Could not connect to database", e);
      createDb(DB_NAME);
    }
  }
}
