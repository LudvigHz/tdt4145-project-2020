import java.sql.*;
import java.util.*;

public class FilmController extends BaseController {

  public FilmController() {
    super("Film");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option = Main.UI.menu("Movies", Arrays.asList("Main", "List all", "Insert new"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          createFilm();
          break;
      }
    }
  }

  public void createFilm() {
    String title = Main.UI.getUserInput("Enter the title of the movie: ");
    String releaseYear = Main.UI.getUserInput("Enter the release year of the movie: ");
    String date =
        releaseYear + "-" + Main.UI.getUserInput("Enter the release date of the movie [MM-DD]: ");
    String description = Main.UI.getUserInput("Enter a brief description of the movie: ");
    List<Actor> actors = getActorKeys();
    List<Integer> directorKeys = getPersonKey("director");
    List<Integer> writerKeys = getPersonKey("writer");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into Film values ( NULL, '"
              + title
              + "', "
              + releaseYear
              + ", '"
              + date
              + "', '"
              + description
              + "')");
      ResultSet result = stmt.executeQuery("select last_insert_id()");
      int FilmID = 0;
      while (result.next()) {
        FilmID = result.getInt(1);
      }

      for (Actor actor : actors) {
        stmt.executeUpdate(
            "insert into SkueSpillerIFilm values ("
                + FilmID
                + ", "
                + actor.primaryKey
                + ", '"
                + actor.role
                + "')");
      }
      for (Integer directorKey : directorKeys) {
        stmt.executeUpdate(
            "insert into RegissørIFilm values (" + FilmID + ", " + directorKey + ")");
      }
      for (Integer writerKey : writerKeys) {
        stmt.executeUpdate(
            "insert into ManusForfatterIFilm values (" + FilmID + ", " + writerKey + ")");
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  private class Actor {
    String role;
    int primaryKey;

    public Actor(String role, int primaryKey) {
      this.role = role;
      this.primaryKey = primaryKey;
    }
  }

  private List<Actor> getActorKeys() {
    // Generate a list of all person primary keys
    Collection<Integer> allPeople = new ArrayList<Integer>();
    try {
      Statement statement = conn.createStatement();
      ResultSet result = statement.executeQuery("select PersonID from Person");
      while (result.next()) {
        allPeople.add(result.getInt(1));
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
    // List of keys to be returned
    List<Actor> actorList = new ArrayList<Actor>();
    while (true) {
      Main.pc.listAllItems();
      String personInput =
          Main.UI.getUserInput(
              "Please select a person for the actor role. In order to move on, enter 0: ");

      if (personInput.equals("0") && actorList.size() != 0) {
        break;
      } else if (personInput.equals("0")) {
        Main.UI.error("A film requires at least one actor");
        continue;
      }
      int personKey = 0;
      try {
        personKey = Integer.parseInt(personInput);
      } catch (Exception e) {
        Main.UI.error("Input must be a number");
        continue;
      }
      if (!allPeople.contains(personKey)) {
        Main.UI.error("Person does not exist");
        continue;
      }
      String role = Main.UI.getUserInput("Enter role: ");
      actorList.add(new Actor(role, personKey));
    }
    return actorList;
  }

  private List<Integer> getPersonKey(String relationName) {
    // Generate a list of all person primary keys
    Collection<Integer> allPeople = new ArrayList<Integer>();
    try {
      Statement statement = conn.createStatement();
      ResultSet result = statement.executeQuery("select PersonID from Person");
      while (result.next()) {
        allPeople.add(result.getInt(1));
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
    // List of keys to be returned
    List<Integer> personList = new ArrayList<Integer>();
    while (true) {
      Main.pc.listAllItems();
      String personInput =
          Main.UI.getUserInput(
              "Please select a person for the "
                  + relationName
                  + " role. In order to move on, enter 0: ");

      if (personInput.equals("0") && personList.size() != 0) {
        break;
      } else if (personInput.equals("0")) {
        Main.UI.error("A film requires at least one " + relationName);
        continue;
      }
      int personKey = 0;
      try {
        personKey = Integer.parseInt(personInput);
      } catch (Exception e) {
        Main.UI.error("Input must be a number");
        continue;
      }
      if (!allPeople.contains(personKey)) {
        Main.UI.error("Person does not exist");
        continue;
      }
      personList.add(personKey);
    }
    return personList;
  }
}
