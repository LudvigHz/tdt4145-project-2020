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

  private void header(String text, int len) {
    String line = StringUtils.center(" " + text + " ", len, '=');
    System.out.println(line);
  }

  private void border(int len) {
    System.out.println(StringUtils.repeat("=", len));
  }

  private void resetText() {
    System.out.print((char) 27 + "[0m");
  }

  public void error(String text) {
    System.out.println((char) 27 + "[31m" + text);
    resetText();
  }

  private void printMenuItem(int i, String text) {
    String line = "  " + StringUtils.rightPad(Integer.toString(i), 2) + " -- ";
    line += StringUtils.rightPad(text, 40);
    System.out.println(line);
  }

  public String getUserInput(String text) {
    System.out.print(text);
    String in = input.nextLine();
    return in;
  }

  public int getMenuOption(int max) {
    int option = -1;
    boolean error = false;
    while (option < 0 || option >= max) {
      if (error) {
        error("Please select a number between 0 and " + Integer.toString(max - 1));
      }
      System.out.print((char) 27 + "[33m" + "Select a number from the menu: ");
      option = input.nextInt();
      resetText();
      error = true;
    }
    input.nextLine();
    spacer(1);
    return option;
  }

  public int menu(String title, List<String> items) {
    spacer(2);
    header(title, 40);
    for (int i = 0; i < items.size(); i++) {
      String m = items.get(i);
      printMenuItem(i, m);
    }
    border(40);
    return getMenuOption(items.size());
  }

  private int tableRow(List<String> items) {
    String text =
        items.stream().reduce("", (st, item) -> st + "| " + StringUtils.rightPad(item, 30));
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
      if (cols == 0) {
        System.out.println("No data!\n");
        return;
      }
      for (int i = 1; i <= cols; i++) {
        colNames.add(meta.getColumnName(i));
      }

      spacer(2);
      header("Result data", 80);
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
      border(len);
      spacer(2);

    } catch (Exception e) {
      error(e.toString());
    }
  }
}
