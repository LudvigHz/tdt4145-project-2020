import java.sql.*;
import java.util.*;

public class FilmController extends BaseController {

  private int userId;

  public FilmController() {
    super("Film");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option = Main.UI.menu("Movies", Arrays.asList("Main", "Movies", "Series", "Insert new"));
      switch (option) {
        case 0:
          break main;
        case 1:
          movieDetails();
          break;
        case 2:
          seriesDetails();
          break;
        case 3:
          createFilm();
          break;
      }
    }
  }

  public void movieDetails() {
    listAllMovies();
    String FilmID = Main.UI.getUserInput("Choose a movie: ");
    movies:
    while (true) {
      int options2 =
          Main.UI.menu(
              "Movies",
              Arrays.asList(
                  "Back",
                  "Rate",
                  "See actors",
                  "See directors",
                  "See writers",
                  "See ratings",
                  "See comments"));
      switch (options2) {
        case 0:
          break movies;
        case 1:
          if (!Main.uc.userMenu()) break;
          String text = Main.UI.getUserInput("Enter rating text: ");
          String rating = Main.UI.getUserInput("Enter rating (0 - 10): ");
          Main.uc.insertRatingQuery(rating, text, FilmID);
          break;
        case 2:
          this.listAllActors(FilmID);
          break;
        case 3:
          this.listAllDirectors(FilmID);
          break;
        case 4:
          this.listAllWriters(FilmID);
          break;
        case 5:
          listAllRatings(FilmID);
          break;
        case 6:
          listAllComments(FilmID);
          break;
      }
    }
  }

  public void seriesDetails() {
    Main.sc.listAllSeries();
    String FilmID = Main.UI.getUserInput("Choose a series: ");
    series:
    while (true) {
      int options2 =
          Main.UI.menu(
              "Movies",
              Arrays.asList(
                  "Back",
                  "Rate",
                  "See actors",
                  "See directors",
                  "See writers",
                  "See ratings",
                  "See comments",
                  "Episodes"));
      switch (options2) {
        case 0:
          break series;
        case 1:
          if (!Main.uc.userMenu()) break;
          String text = Main.UI.getUserInput("Enter rating text: ");
          String rating = Main.UI.getUserInput("Enter rating (0 - 10): ");
          Main.uc.insertRatingQuery(rating, text, FilmID);
          break;
        case 2:
          this.listAllActors(FilmID);
          break;
        case 3:
          this.listAllDirectors(FilmID);
          break;
        case 4:
          this.listAllWriters(FilmID);
          break;
        case 5:
          listAllRatings(FilmID);
          break;
        case 6:
          listAllComments(FilmID);
          break;
        case 7:
          episodeDetails(FilmID);
          break;
      }
    }
  }

  public void episodeDetails(String FilmID) {
    Main.sc.listAllSeasons(Integer.parseInt(FilmID));
    String seasonNumber = Main.UI.getUserInput("Choose a season: ");
    listAllEpisodes(FilmID, seasonNumber);
    String episodeNumber = Main.UI.getUserInput("Choose an episode");
    episodes:
    while (true) {
      int options2 =
          Main.UI.menu(
              "Movies",
              Arrays.asList(
                  "Back",
                  "Rate",
                  "See actors",
                  "See directors",
                  "See writers",
                  "See ratings",
                  "See comments"));
      switch (options2) {
        case 0:
          break episodes;
        case 1:
          if (!Main.uc.userMenu()) break;
          String text = Main.UI.getUserInput("Enter rating text: ");
          String rating = Main.UI.getUserInput("Enter rating (0 - 10): ");
          Main.uc.insertRatingQuery(rating, text, FilmID);
          break;
        case 2:
          this.listAllActors(episodeNumber);
          break;
        case 3:
          this.listAllDirectors(episodeNumber);
          break;
        case 4:
          this.listAllWriters(episodeNumber);
          break;
        case 5:
          listAllRatings(episodeNumber);
          break;
        case 6:
          listAllComments(episodeNumber);
          break;
      }
    }
  }

  public void listAllEpisodes(String FilmID, String seasonNumber) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select * from Episode natural join Film where sesongNr="
                  + seasonNumber
                  + " and SerieID="
                  + FilmID);
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllActors(String FilmID) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select navn, rolle from Person natural join SkueSpillerIFilm natural join Film where FilmID="
                  + FilmID);
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllWriters(String FilmID) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select navn from Person natural join ManusForfatterIFilm natural join Film where FilmID="
                  + FilmID);
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllRatings(String FilmID) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select rating, tekst, brukernavn from Bruker natural join Anmeldelse natural join (select FilmID, tittel as film from Film where FilmID="
                  + FilmID
                  + ") as f");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta, "Ratings");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllComments(String FilmID) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select tekst, brukernavn from Bruker natural join Kommentar natural join (select FilmID, tittel as film from Film where FilmID="
                  + FilmID
                  + ") as f");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta, "Comments");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllDirectors(String FilmID) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select navn from Person natural join RegissørIFilm natural join Film where FilmID="
                  + FilmID);
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listAllMovies() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery("select * from SpilleFilm natural join Utgivelse natural join Film");
      ResultSetMetaData meta = data.getMetaData();
      Main.UI.printItems(data, meta);
    } catch (Exception e) {
      Main.UI.error(e.toString());
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
    List<Integer> categoryKeys = getCategoryKeys();
    // declare variables that are gathered in if-statements
    int seriesKey = 0;
    int seasonNumber = 0;
    int lastEpisode = 0;
    boolean isEpisode = Main.UI.getUserInput("Is this an episode of a series [y/n]: ").equals("y");
    String length = "0";
    int companyKey = 0;
    String publishedOnVideo = "false";
    boolean isSeries = false;
    String firstSeasonOnVideo = "false";
    if (!isEpisode) {
      isSeries = Main.UI.getUserInput("Is this a series [y/n]: ").equals("y");
      companyKey = getCompany();
      if (isSeries) {
        firstSeasonOnVideo =
            Main.UI
                    .getUserInput(
                        "Has the first season of this series been released on video [y/n]: ")
                    .equals("y")
                ? "true"
                : "false";
      }
      if (!isSeries) {
        publishedOnVideo =
            Main.UI.getUserInput("Is the film published on video [y/n]: ").equals("y")
                ? "true"
                : "false";
        length = Main.UI.getUserInput("Enter the length of the movie in minutes: ");
      }
    }
    if (isEpisode) {
      seriesKey = Main.sc.getSeriesKey();
      seasonNumber = this.getSeason(seriesKey);
      lastEpisode = this.getLastEpisode(seriesKey, seasonNumber) + 1;
      length = Main.UI.getUserInput("Enter the length of the movie in minutes: ");
    }

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
      for (Integer categoryKey : categoryKeys) {
        stmt.executeUpdate(
            "insert into KategoriIFilm values (" + FilmID + ", " + categoryKey + ")");
      }
      if (!isEpisode) {
        stmt.executeUpdate("insert into Utgivelse values (" + FilmID + ", " + companyKey + ")");
        stmt.executeUpdate("insert into Serie values (" + FilmID + ")");
        // Create first season when creating a series
        if (isSeries) {
          stmt.executeUpdate(
              "insert into Sesong (FilmID, SesongNR, utgittPåVideo) values("
                  + FilmID
                  + ", "
                  + "1"
                  + ", "
                  + firstSeasonOnVideo
                  + ");");
        }
        if (!isSeries) {
          stmt.executeUpdate(
              "insert into SpilleFilm values ("
                  + FilmID
                  + ", "
                  + publishedOnVideo
                  + ", "
                  + length
                  + ")");
        }
      }
      if (isEpisode) {
        stmt.executeUpdate(
            "insert into Episode (FilmID, episodeNr, sesongNr, lengde, SerieID) values("
                + FilmID
                + ", "
                + lastEpisode
                + ", "
                + seasonNumber
                + ", "
                + length
                + ", "
                + seriesKey
                + ")");
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

  private int getSeason(int seriesKey) {
    while (true) {
      Main.sc.listAllSeasons(seriesKey);
      String seasonInput = Main.UI.getUserInput("Please choose a season: ");
      int seasonNumber = 0;
      try {
        seasonNumber = Integer.parseInt(seasonInput);
      } catch (Exception e) {
        Main.UI.error("Input must be a number");
        continue;
      }
      Collection<Integer> allKeys = new ArrayList<Integer>();
      try {
        Statement statement = conn.createStatement();
        ResultSet result =
            statement.executeQuery(
                "select SesongNR from Sesong as ses natural join Serie as ser natural join Utgivelse natural join Film where FilmID="
                    + seriesKey
                    + " order by SesongNr asc");
        while (result.next()) {
          allKeys.add(result.getInt(1));
        }
      } catch (Exception e) {
        Main.UI.error(e.toString());
      }
      if (!allKeys.contains(seasonNumber)) {
        Main.UI.error("Season does not exist");
        continue;
      }
      return seasonNumber;
    }
  }

  private int getLastEpisode(int seriesKey, int seasonNumber) {
    int lastEpisode = 0;
    try {
      Statement statement = conn.createStatement();
      ResultSet result =
          statement.executeQuery(
              "select * from Episode where sesongNr="
                  + seasonNumber
                  + " and SerieID="
                  + seriesKey
                  + " order by episodeNr desc");
      if (result.next()) {
        lastEpisode = result.getInt(1);
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
    return lastEpisode;
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

  private boolean tableContainsKey(int key, String table) {
    Collection<Integer> allKeys = new ArrayList<Integer>();
    try {
      Statement statement = conn.createStatement();
      ResultSet result = statement.executeQuery("select " + table + "ID from " + table);
      while (result.next()) {
        allKeys.add(result.getInt(1));
      }
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
    return allKeys.contains(key);
  }

  private List<Integer> getCategoryKeys() {
    List<Integer> categoryList = new ArrayList<Integer>();
    while (true) {
      Main.cc.listAllItems();
      String categoryInput = Main.UI.getUserInput("Please choose a category, enter 0 to proceed: ");
      if (categoryInput.equals("0") && categoryList.size() != 0) {
        break;
      } else if (categoryInput.equals("0")) {
        Main.UI.error("A film requires at least one category");
        continue;
      }
      int categoryKey = 0;
      try {
        categoryKey = Integer.parseInt(categoryInput);
      } catch (Exception e) {
        Main.UI.error("Input must be a number");
        continue;
      }
      if (!tableContainsKey(categoryKey, "Kategori")) {
        Main.UI.error("Category does not exist");
        continue;
      }
      categoryList.add(categoryKey);
    }
    return categoryList;
  }

  private int getCompany() {
    int companyKey = 0;
    while (true) {
      Main.fcc.listAllItems();
      String categoryInput = Main.UI.getUserInput("Please choose a company: ");
      try {
        companyKey = Integer.parseInt(categoryInput);
      } catch (Exception e) {
        Main.UI.error("Input must be a number");
        continue;
      }
      if (!tableContainsKey(companyKey, "FilmSelskap")) {
        Main.UI.error("Company does not exist");
        continue;
      }
      break;
    }
    return companyKey;
  }
}
