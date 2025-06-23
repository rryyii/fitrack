package fitrack_proj.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class NutritionDAO {

  public NutritionDAO(Connection connection) {
    this.connection = connection;
  }

  public int insertNutrition(int userId, String foodName, Integer servingCount) {
    String insertNutrition =
        "INSERT INTO nutrition_history (user_id, date, food_name, serving_count) VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement insert = connection.prepareStatement(insertNutrition);
      insert.setInt(1, userId);
      long millis = System.currentTimeMillis();
      insert.setDate(2, new Date(millis));
      insert.setString(3, foodName);
      insert.setInt(4, servingCount);
      int result = insert.executeUpdate();
      return result;
    } catch (SQLException sqe) {
      System.out.println(
          "Failed to insert a food log into the nutrition_history database: " + sqe.getMessage());
    }
    return -1;
  }

  /**
   * Retrieves and returns a user's nutrition history.
   *
   * @param username Username of user to retrieve history
   * @return ArrayList of the user's history if it exists
   */
  public ResultSet retrieveNutrition(int userid) {
    ResultSet historySet = null;
    String retrieve = "SELECT * FROM nutrition_history WHERE user_id=?;";
    try {
      PreparedStatement history = connection.prepareStatement(retrieve);
      history.setInt(1, userid);
      historySet = history.executeQuery();
    } catch (SQLException sqe) {
      System.out.println("Couldn't retrieve user's nutrition history: " + sqe);
    }
    return historySet;
  }

  private Connection connection;

}
