import java.sql.*;
import java.util.*;

public class FilmCompanyController extends BaseController {

  public FilmCompanyController() {
    super("FilmSelskap");
  }

  public void selectOperation() {

    main:
    while (true) {
      int option =
          Main.UI.menu(
              "Film company",
              Arrays.asList("Main", "List all", "Insert new", "View companies by category"));
      switch (option) {
        case 0:
          break main;
        case 1:
          listAllItems();
          break;
        case 2:
          createFilmCompany();
          break;
        case 3:
          listDetails();
          break;
      }
    }
  }

  public void createFilmCompany() {
    String name = Main.UI.getUserInput("Enter the name of the company: ");
    String URL = Main.UI.getUserInput("Enter the url of the company: ");
    String address = Main.UI.getUserInput("Enter the address of the company: ");
    String land = Main.UI.getUserInput("Enter the land of the company: ");

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(
          "insert into FilmSelskap (navn, URL, addresse, land) "
              + "values ('"
              + name
              + "','"
              + URL
              + "','"
              + address
              + "','"
              + land
              + "');");
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }

  public void listDetails() {
    Main.cc.listAllItems();
    int kategoriId = Integer.valueOf(Main.UI.getUserInput("Kategori ID: "));
    try {
      Statement catstmt = conn.createStatement();
      ResultSet catdata =
          catstmt.executeQuery("select kategori from Kategori where KategoriID = " + kategoriId);

      String catname = "";
      if (catdata.next()) {
        catname = catdata.getString(1);
      }

      Main.UI.success("Found companies with movies in the category: " + catname);

      Statement stmt = conn.createStatement();
      ResultSet data =
          stmt.executeQuery(
              "select FS.FilmSelskapID, FS.addresse,"
                  + " count(F.FilmID) as Antall_filmer,"
                  + " group_concat(F.tittel) as Filmer"
                  + " from FilmSelskap as FS"
                  + " natural join Utgivelse as U"
                  + " natural join Film as F"
                  + " natural join KategoriIFilm"
                  + " natural join Kategori as K"
                  + " where K.KategoriID = "
                  + Integer.toString(kategoriId)
                  + " group by FS.FilmSelskapID"
                  + " order by Antall_filmer");
      Main.UI.printItems(data, data.getMetaData(), "Companies by category: " + catname);
    } catch (Exception e) {
      Main.UI.error(e.toString());
    }
  }
}
