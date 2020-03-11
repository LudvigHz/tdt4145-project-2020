import java.sql.*;
import java.util.*;

public class SeasonController extends BaseController {
  public SeasonController() {
    super("Sesong");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option = Main.UI.menu("Seasons", Arrays.asList("Main", "List all", "Insert new"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          addSeason();
          break;
      }
    }
  }

  @Override
  public void listAllItems() {
    int seriesKey = this.getSeriesKey();
    listAllSeasons(seriesKey);
  }

  public void listAllSeasons(int seriesKey) {
    try {
      Statement statement = conn.createStatement();
      ResultSet data =
          statement.executeQuery(
              "select SesongNR from Sesong as ses natural join Serie as ser natural join Utgivelse natural join Film where FilmID="
                  + seriesKey
                  + " order by SesongNr asc");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllSeries() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery("select * from Serie natural join Utgivelse natural join Film");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  private boolean seriesExists(int seriesKey) {
    Collection<Integer> allKeys = new ArrayList<Integer>();
    try {
      Statement statement = conn.createStatement();
      ResultSet result = statement.executeQuery("select FilmID from Serie");
      while (result.next()) {
        allKeys.add(result.getInt(1));
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
    return allKeys.contains(seriesKey);
  }

  public void addSeason() {
    int seriesKey = this.getSeriesKey();
    int seasonNumber = this.getLastSeason(seriesKey) + 1;
    String publishedOnVideo =
        Main.UI.getUserInput("Is the season published on video [y/n]: ").equals("y")
            ? "true"
            : "false";
    try {
      Statement statement = conn.createStatement();
      statement.executeUpdate(
          "insert into Sesong (FilmID, SesongNR, utgittPåVideo) values("
              + seriesKey
              + ", "
              + seasonNumber
              + ", "
              + publishedOnVideo
              + ");");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public int getSeriesKey() {
    while (true) {
      listAllSeries();
      String seriesInput = Main.UI.getUserInput("Enter the filmID of the series you change: ");
      int seriesKey = 0;
      try {
        seriesKey = Integer.parseInt(seriesInput);
      } catch (Exception e) {
        Main.UI.error("Input must be a number");
        continue;
      }
      if (!seriesExists(seriesKey)) {
        Main.UI.error("Series does not exist");
        continue;
      }
      return seriesKey;
    }
  }

  private int getLastSeason(int seriesKey) {
    int lastSeason = 0;
    try {
      Statement statement = conn.createStatement();
      ResultSet result =
          statement.executeQuery(
              "select SesongNR from Sesong as ses natural join Serie as ser natural join Utgivelse natural join Film where FilmID="
                  + seriesKey
                  + " order by SesongNr desc");
      if (result.next()) {
        lastSeason = result.getInt(1);
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
    return lastSeason;
  }
}
