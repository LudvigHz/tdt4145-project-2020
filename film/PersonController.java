import java.sql.*;
import java.util.Arrays;

public class PersonController extends BaseController {

  private String selectedName;
  private int selectedId;

  public PersonController() {
    super("Person");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option =
          Main.UI.menu(
              "Person",
              Arrays.asList("Main", "List all", "Insert new", "See all roles", "Select a person"));
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
        case 4:
          personMenu();
          break;
      }
    }
  }

  public void personMenu() {
    listPeopleShort();
    selectedId = Integer.valueOf(Main.UI.getUserInput("Select a person ID: "));
    selectedName = getPersonName(selectedId);
    if (selectedName == "") {
      return;
    }
    Main.UI.success("Selected person: " + selectedName);
    listPersonDetails(selectedId);

    main:
    while (true) {
      int option =
          Main.UI.menu(
              "Menu for " + selectedName,
              Arrays.asList(
                  "Back",
                  "See all roles",
                  "See all movies",
                  "See directed/written movies",
                  "See music"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listRoles(selectedId);
          break;
        case 2:
          listMovies(selectedId);
          break;
        case 3:
          listDirectedWritten(selectedId);
          break;
        case 4:
          listMusic(selectedId);
          break;
      }
    }
  }

  public String getPersonName(int id) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery("select navn from Person where PersonID = " + Integer.toString(id));
      if (data.next()) {
        return data.getString(1);
      }
      return "";
    } catch (Exception e) {
      Main.UI.error(e.toString());
      return "";
    }
  }

  public void listPersonDetails(int id) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select navn as Name, fødselsår, fødselsland from Person where PersonID = "
                  + String.valueOf(id));
      Main.UI.printItems(data, data.getMetaData(), "Details for user: " + Integer.toString(id));
    } catch (Exception e) {
      Main.UI.error(e.toString());
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

  public void listRoles(int id) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select SF.rolle as Rolle, F.tittel as Film, F.utgivelsesår as År from SkueSpillerIFilm as SF natural join Film as F where SF.PersonID = "
                  + Integer.toString(id));
      Main.UI.printItems(data, data.getMetaData(), "Roles for " + selectedName);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listMovies(int id) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select F.tittel as Film, F.utgivelsesår as År from SkueSpillerIFilm as SF natural join Film as F where SF.PersonID = "
                  + Integer.toString(id));
      Main.UI.printItems(
          data, data.getMetaData(), "Movies that " + selectedName + " has played in");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listDirectedWritten(int id) {
    try {
      Statement stmt1 = conn.createStatement();
      ResultSet data1 =
          stmt1.executeQuery(
              "select F.tittel as Film, F.utgivelsesår as År from RegissørIFilm as SF natural join Film as F where SF.PersonID = "
                  + Integer.toString(id));
      Main.UI.printItems(data1, data1.getMetaData(), "Directed movies");

      Statement stmt2 = conn.createStatement();
      ResultSet data2 =
          stmt2.executeQuery(
              "select F.tittel as Film, F.utgivelsesår as År from ManusForfatterIFilm as SF natural join Film as F where SF.PersonID = "
                  + Integer.toString(id));
      Main.UI.printItems(data2, data2.getMetaData(), "Screenplay written movies");

    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listMusic(int id) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select M.MusikkID, F.navn as Fremforer, K.navn as Komponist, GROUP_CONCAT(Fi.tittel) as Filmer"
                  + " from Musikk as M"
                  + " left join Person as K on M.komponist = K.PersonID"
                  + " left join Person as F on M.fremforer = F.PersonID"
                  + " natural join FilmMusikk natural join Film as Fi"
                  + " GROUP BY M.MusikkID"
                  + " having M.MusikkID in (select M.MusikkID from Musikk as M where M.komponist = "
                  + Integer.toString(id)
                  + " or M.fremforer = "
                  + Integer.toString(id)
                  + ")");

      Main.UI.printItems(data, data.getMetaData(), "Music for person " + selectedName);
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
              "select SF.rolle as Rolle, F.tittel as Film, F.utgivelsesår as År from SkueSpillerIFilm as SF natural join Film as F where SF.PersonID = "
                  + Integer.toString(pid));
      Main.UI.printItems(data, data.getMetaData());
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
