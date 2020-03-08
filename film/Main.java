import java.util.Arrays;

public class Main {

  public static UI UI = new UI();

  public static PersonController pc = new PersonController();
  public static UserController uc = new UserController();
  public static DBController db = new DBController();

  public static void main(String[] args) {
    db.connect();

    main:
    while (true) {
      int option =
          UI.menu(
              "Main menu", Arrays.asList("Exit", "People", "Movies", "Ratings", "Load fixtures"));
      switch (option) {
        case 0:
          break main;
        case 1:
          pc.selectOperation();
          break;
        case 2:
          // TODO
          break;
        case 3:
          uc.selectOperation();
          break;
        case 4:
          db.loadFixtures();
          break;
      }
    }
  }
}
