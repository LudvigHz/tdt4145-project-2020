import java.sql.*;
import java.util.*;

public class CategoryController extends BaseController {
  public CategoryController() {
    super("Kategori");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option = Main.UI.menu("Categories", Arrays.asList("Main", "List all", "Insert new"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          insertCategory();
          break;
      }
    }
  }

  public void insertCategory() {
    String categori = Main.UI.getUserInput("Name of category: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate("insert into Kategori values (null, '" + categori + "')");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
