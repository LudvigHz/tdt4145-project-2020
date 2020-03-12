import java.sql.*;
import java.util.*;

public class MusicController extends BaseController {

  private int userId;

  public MusicController() {
    super("Musikk");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option = Main.UI.menu("Music", Arrays.asList("Main", "List all", "Insert new"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          insertMusic();
          break;
      }
    }
  }

  @Override
  public void listAllItems() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select M.MusikkID as ID, K.navn as Komponist, F.navn as Fremforer from Musikk as M"
                  + " join Person as K on M.komponist = K.PersonID"
                  + " join Person as F on M.fremforer = F.PersonID;");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void insertMusic() {
    String komponist, fremforer;
    Main.pc.listPeopleShort();
    komponist = Main.UI.getUserInput("Komponist: ");
    Main.pc.listPeopleShort();
    fremforer = Main.UI.getUserInput("Fremfører: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into Musikk (komponist, fremforer) "
              + "values ('"
              + komponist
              + "','"
              + fremforer
              + "');");
      Main.UI.success("Inserted new music.");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void insertFilmMusic(String filmId, String musicId) {
    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into FilmMusikk (MusikkID, FilmID) values (" + musicId + ", " + filmId + ")");
      Main.UI.success("Added new music to the movie");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
