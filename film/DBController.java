import java.io.*;
import java.sql.*;
import org.apache.commons.lang3.StringUtils;

public class DBController extends DBConn {
  public void loadFixtures() {
    String[] fixtures = {
      "fixtures/people.sql",
      "fixtures/FilmSelskap.sql",
      "fixtures/film.sql",
      "fixtures/musikk.sql",
      "fixtures/categories.sql",
    };
    try {
      Statement stmt = conn.createStatement();
      stmt.execute("use Film");

      for (String fixture : fixtures) {
        try {
          executeScript(fixture);
          Main.UI.success(
              "Loaded fixtures for: " + StringUtils.substringBetween(fixture, "/", ".sql"));
        } catch (Exception e2) {
          Main.UI.error(e2.toString());
        }
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void resetdb() {
    String script = "filmdbscriptv2.sql";

    try {
      executeScript(script);
      Main.UI.success("Created tables for film database!");
    } catch (Exception e2) {
      Main.UI.error(e2.toString());
    }
  }

  private void executeScript(String filename) throws IOException, SQLException {
    ScriptRunner runner = new ScriptRunner(conn, false, false);
    runner.runScript(new BufferedReader(new FileReader("../" + filename)));
  }
}
