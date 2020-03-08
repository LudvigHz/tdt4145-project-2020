import java.sql.*;
import java.util.Arrays;

public class PersonController extends BaseController {

  public PersonController() {
    super("Person");
  }

  public void selectOperation() {
    int option = Main.UI.menu("Person", Arrays.asList("List all", "Insert new"));
    // TODO switch
  }

  public void insertPerson() {
    String name, year, country;
    name = Main.UI.getUserInput("Name: ");
    year = Main.UI.getUserInput("Birth year: ");
    country = Main.UI.getUserInput("Country: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into Person (navn, fødselsår, fødselsland) "
              + "values ('"
              + name
              + "','"
              + year
              + "','"
              + country
              + "');");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
