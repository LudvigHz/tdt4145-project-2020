import java.util.Arrays;

public class Main {

  public static UI UI = new UI();

  public static PersonController pc = new PersonController();

  public static void main(String[] args) {
    Test conn = new Test();
    conn.connect();

    main:
    while (true) {
      int option = UI.menu("Main menu", Arrays.asList("Exit", "People", "Movies"));
      switch (option) {
        case 0:
          break main;
        case 1:
          pc.selectOperation();
          break;
        case 2:
          // TODO
          break;
      }
    }
  }
}
