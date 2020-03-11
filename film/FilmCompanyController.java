import java.sql.*;
import java.util.*;

public class FilmCompanyController extends BaseController {

  public FilmCompanyController() {
    super("FilmSelskap");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option = Main.UI.menu("Person", Arrays.asList("Main", "List all", "Insert new"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          createFilmCompany();
          break;
      }
    }
  }

  public void createFilmCompany() {
    String name = Main.UI.getUserInput("Enter the name of the company: ");
    String URL = Main.UI.getUserInput("Enter the url of the company: ");
    String address = Main.UI.getUserInput("Enter the address of the company: ");
    String land = Main.UI.getUserInput("Enter the land of the company: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into FilmSelskap (navn, URL, addresse, land) "
              + "values ('"
              + name
              + "','"
              + URL
              + "','"
              + address
              + "','"
              + land
              + "');");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
