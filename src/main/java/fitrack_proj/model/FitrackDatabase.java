package fitrack_proj.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fitrack_proj.util.H2DatabaseUtil;
import fitrack_proj.util.PasswordEncryptor;

public class FitrackDatabase {

  public FitrackDatabase() {
    getcurrentConnection();
  }

  public Connection getConnection() {
    return this.currentConnection;
  }

  private void getcurrentConnection() {
    try {
      String url = System.getenv("DB_URL");
      String username = System.getenv("DB_USER");
      String password = System.getenv("DB_PASSWORD");
      currentConnection = DriverManager.getConnection(url, username, password);
      H2DatabaseUtil.initializeDatabase();
    } catch (SQLException e) {
      System.out.println("Couldn't establish a currentConnection successfully: " + e.getMessage());
    }

  }

  private Connection currentConnection;


}
