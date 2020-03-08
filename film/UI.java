import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class UI {

  private Scanner input;

  public UI() {
    input = new Scanner(System.in);
  }

  private void spacer(int spaces) {
    System.out.println(StringUtils.repeat("\n", spaces));
  }

  private void header(String text) {
    String line = StringUtils.center(" " + text + " ", 40, '=');
  }

  private void resetText() {
    System.out.print((char) 27 + "[0m");
  }

  public void error(String text) {
    System.out.println((char) 27 + "[31m" + text);
    resetText();
  }

  private interface MenuObjectInterface {
    public void handler();
  }

  // Class for constructing menu items to use with the UI
  public abstract class MenuObject implements MenuObjectInterface {
    public String text;
  }

  private void printMenuItem(int i, String text) {
    String line = "  " + StringUtils.rightPad(Integer.toString(i), 3) + " -- ";
    line += StringUtils.rightPad(text, 40);
  }

  public int getMenuOption(int max) {
    int option = -1;
    boolean error = false;
    while (option < 0 || option > max) {
      if (error) {
        error("Please select a number between 0 and " + Integer.toString(max));
      }
      System.out.print("Select a number from the menu: ");
      option = input.nextInt();
      error = true;
    }
    return option;
  }

  private void menu(String title, ArrayList<MenuObject> items) {
    spacer(2);
    header(title);
    for (int i = 0; i < items.size(); i++) {
      MenuObject m = items.get(i);
      printMenuItem(i, m.text);
    }

    int option = getMenuOption(items.size());

    MenuObject selectedObject = items.get(option);

    selectedObject.handler();
  }

  public void mainMenu() {
    spacer(4);
    System.out.println("menu here");
  }

  private int tableRow(List<String> items) {
    String text =
        items.stream().reduce("", (st, item) -> st + "| " + StringUtils.rightPad(item, 10));
    System.out.println(text);
    return text.length();
  }

  private void tableRowSep(int len) {
    System.out.println(StringUtils.repeat("-", len));
  }

  public void printItems(ResultSet data, ResultSetMetaData meta) {
    List<String> colNames = new ArrayList<String>();
    try {
      int cols = meta.getColumnCount();
      for (int i = 1; i <= cols; i++) {
        colNames.add(meta.getTableName(i));
      }

      int len = tableRow(colNames);
      tableRowSep(len);

      while (data.next()) {
        List<String> row = new ArrayList<String>();
        String itemString;
        for (int i = 1; i <= cols; i++) {
          row.add(data.getString(i));
        }
        tableRow(row);
      }

    } catch (Exception e) {
      error(e.toString());
    }
  }
}
