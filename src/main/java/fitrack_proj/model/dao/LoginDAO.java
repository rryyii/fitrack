package fitrack_proj.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import fitrack_proj.util.PasswordEncryptor;

public class LoginDAO {


  public LoginDAO(Connection connection) {
    this.connection = connection;
  }

  /**
   * Verifies if the user successfully logged in.
   *
   * @param username Username of login request
   * @param password Password of login request
   * @return Returns true if successful login, false if failure
   */
  public int verifyLogin(String username, String password) {
    String login = "SELECT * FROM users_ft WHERE username=? AND password=?;";
    try {
      PreparedStatement query = connection.prepareStatement(login);
      query.setString(1, username);
      query.setString(2, PasswordEncryptor.hashPassword(password));
      ResultSet result = query.executeQuery();
      while (result.next()) {
        if (result.getString("username").equals(username)
            && result.getString("password").equals(PasswordEncryptor.hashPassword(password))) {
          return result.getInt("user_id");
        }
      }
    } catch (SQLException e) {
      System.out.println("Couldn't verify the users login successfully: " + e.getMessage());
    }
    return -1;
  }

  /**
   * Retrieves a user based on username and password.
   *
   * @param username Username of user to retrieve information from.
   * @param password Password of user to retrieve information from.
   * @return ResultSet of the retrieved user
   */
  public ResultSet retrieveUser(int user_id) {
    String sql = "SELECT * FROM users_ft WHERE user_id=?";
    PreparedStatement retrieve = null;
    ResultSet result = null;
    try {
      retrieve = connection.prepareStatement(sql);
      retrieve.setInt(1, user_id);
      result = retrieve.executeQuery();
    } catch (SQLException sqe) {
      System.out.println("Couldn't retrieve user successfully:" + sqe.getMessage());

    }
    return result;
  }

  private Connection connection;

}
