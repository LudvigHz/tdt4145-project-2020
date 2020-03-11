import java.sql.*;
import java.util.Arrays;

public class PersonController extends BaseController {

  public PersonController() {
    super("Person");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option =
          Main.UI.menu("Person", Arrays.asList("Main", "List all", "Insert new", "See all roles"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          insertPerson();
          break;
        case 3:
          listAllRoles();
          break;
      }
    }
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

  public void listPeopleShort() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data = stmt.executeQuery("select PersonID as id, navn as Name from Person");
      Main.UI.printItems(data, data.getMetaData());
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllRoles() {
    listPeopleShort();
    int pid = Integer.valueOf(Main.UI.getUserInput("User ID: "));

    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select SF.rolle as Rolle, F.tittel as Film, F.utgivelsesår as År from SkueSpillerIFilm as SF natural join Film as F ");
      Main.UI.printItems(data, data.getMetaData());
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
