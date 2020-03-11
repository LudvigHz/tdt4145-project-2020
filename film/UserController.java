import java.sql.*;
import java.util.Arrays;

public class UserController extends BaseController {

  private int userId;

  public UserController() {
    super("Bruker");
  }

  public void selectOperation() {

    boolean result = userMenu();

    if (!result) {
      return;
    }

    main:
    while (true) {
      int option =
          Main.UI.menu(
              "User menu",
              Arrays.asList(
                  "Main", "List all ratings", "List all comments", "New rating", "New comment"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllRatings();
          break;
        case 2:
          listAllComments();
          break;
        case 3:
          insertRating();
          break;
        case 4:
          insertComment();
          break;
      }
    }
  }

  public boolean userMenu() {
    int option = Main.UI.menu("User", Arrays.asList("Cancel", "Select user", "New user"));

    switch (option) {
      case 0:
        return false;
      case 1:
        return selectUser();
      case 2:
        return insertUser();
    }
    return false;
  }

  private boolean selectUser() {
    String userName = Main.UI.getUserInput("Enter username: ");
    try {
      Statement stmt = conn.createStatement();
      ResultSet user =
          stmt.executeQuery("Select * from Bruker where brukernavn like '" + userName + "';");
      if (!user.next()) {
        Main.UI.error("User not found");
        return false;
      }
      userId = user.getInt(1);
    } catch (Exception e) {
      Main.UI.error(e.toString());
      return false;
    }
    return true;
  }

  private boolean insertUser() {
    String userName = Main.UI.getUserInput("UserName: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate("insert into Bruker (brukernavn) values ('" + userName + "');");
      stmt = conn.createStatement();
      ResultSet user =
          stmt.executeQuery("Select * from Bruker where brukernavn like '" + userName + "';");
      if (!user.next()) {
        return false;
      }
      userId = user.getInt(1);
    } catch (Exception e) {
      Main.UI.error(e.toString());
      return false;
    }
    return true;
  }

  public void insertRatingQuery(String rating, String text, String filmId) {
    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into Anmeldelse (FilmID, tekst, rating, BrukerID) "
              + "values ('"
              + filmId
              + "','"
              + text
              + "','"
              + rating
              + "','"
              + userId
              + "');");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void insertRating() {
    String rating, text, filmId;
    filmId = Main.UI.getUserInput("Enter id of movie to be rated: ");
    text = Main.UI.getUserInput("Enter rating text: ");
    rating = Main.UI.getUserInput("Enter rating (0 - 10): ");
    insertRatingQuery(rating, text, filmId);
  }

  public void insertComment() {
    String text, filmId;
    filmId = Main.UI.getUserInput("Enter id of movie to be commented: ");
    text = Main.UI.getUserInput("Enter text: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into Kommentar (FilmID, BrukerID, tekst) "
              + "values ('"
              + filmId
              + "','"
              + userId
              + "','"
              + text
              + "');");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllRatings() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "Select F.tittel as Film, B.brukernavn as Bruker, A.tekst as Tekst, A.rating as Rating from Anmeldelse as A natural join Film as F natural join Bruker as B where B.BrukerID like "
                  + userId);
      Main.UI.printItems(data, data.getMetaData());
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllComments() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "Select F.tittel as Film, B.brukernavn as Bruker, A.tekst as Tekst from Kommentar as A natural join Film as F natural join Bruker as B where B.BrukerID like "
                  + userId);
      Main.UI.printItems(data, data.getMetaData());
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
