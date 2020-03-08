import java.sql.*;

public class BaseController extends DBConn {

  protected String table;

  public BaseController(String table) {
    this.table = table;
  }

  public void listAllItems() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data = stmt.executeQuery("select * from " + table + ";");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
