package fitrack_proj.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import fitrack_proj.util.PasswordEncryptor;

public class RegisterDAO {


  public RegisterDAO(Connection connection) {
    this.connection = connection;
  }

  /**
   * Registers the user with the given information.
   *
   * @param username Username of new user
   * @param password Password of new user
   * @param gender Gender of new user
   * @param height Height of new user
   * @param weight Weight of new user
   * @param activity Activity level of new user
   * @param age Age of new user
   * @return Successful registration returns 1 for success and -1 for failure
   */
  public int registerUser(String username, String password, String gender, int height, int weight,
      String activity, int age) {
    String register =
        "INSERT INTO users_ft (username, password, gender, weight, height, activity, age) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement query = connection.prepareStatement(register);
      query.setString(1, username);
      query.setString(2, PasswordEncryptor.hashPassword(password));
      query.setString(3, gender);
      query.setInt(4, weight);
      query.setInt(5, height);
      query.setString(6, activity);
      query.setInt(7, age);
      int result = query.executeUpdate();
      return result;
    } catch (SQLException e) {
      System.out.println("Couldn't register user successfully:" + e.getMessage());
    }
    return -1;
  }

  private Connection connection;

}
