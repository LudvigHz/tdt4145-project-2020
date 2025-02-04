import java.util.Arrays;

public class Main {

  public static UI UI = new UI();

  public static PersonController pc = new PersonController();
  public static UserController uc = new UserController();
  public static DBController db = new DBController();
  public static FilmController fc = new FilmController();
  public static CategoryController cc = new CategoryController();
  public static FilmCompanyController fcc = new FilmCompanyController();
  public static SeasonController sc = new SeasonController();
  public static MusicController mc = new MusicController();

  public static void main(String[] args) {
    db.connect();

    main:
    while (true) {
      int option =
          UI.menu(
              "Main menu",
              Arrays.asList(
                  "Exit",
                  "People",
                  "Movies",
                  "Ratings",
                  "Categories",
                  "Companies",
                  "Seasons",
                  "Music",
                  "Load fixtures",
                  "Reset database"));
      switch (option) {
        case 0:
          break main;
        case 1:
          pc.selectOperation();
          break;
        case 2:
          fc.selectOperation();
          break;
        case 3:
          uc.selectOperation();
          break;
        case 4:
          cc.selectOperation();
          break;
        case 5:
          fcc.selectOperation();
          break;
        case 6:
          sc.selectOperation();
          break;
        case 7:
          mc.selectOperation();
          break;
        case 8:
          db.loadFixtures();
          break;
        case 9:
          db.resetdb();
      }
    }
  }
}
