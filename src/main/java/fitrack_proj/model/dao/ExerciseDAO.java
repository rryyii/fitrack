package fitrack_proj.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class ExerciseDAO {
  private Connection connection;

  public ExerciseDAO(Connection connection) {
    this.connection = connection;
  }

  /**
   * Inserts the chosen exercise into the user's history.Ï
   *
   * @param exercise String value of the exercise done
   * @param time Time spent, in minutes, on the exercise
   * @param userId user_id of the user
   */
  public int insertExercise(String exercise, int time, int userId) {
    String insertExercise =
        "INSERT INTO user_history (user_id, exercise_type, exercise_time, date) "
            + "VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement insert = connection.prepareStatement(insertExercise);
      insert.setInt(1, userId);
      insert.setString(2, exercise);
      insert.setInt(3, time);
      long millis = System.currentTimeMillis();
      insert.setDate(4, new Date(millis));
      int result = insert.executeUpdate();
      return result;
    } catch (SQLException sqe) {
      System.out.println(
          "Failed to insert an exercise into the user_history database:" + sqe.getMessage());
    }
    return -1;
  }

  /**
   * Retrieves and returns a user's exercise history.
   *
   * @param username Username of user to retrieve history
   * @return ArrayList of the user's history if it exists
   */
  public ResultSet retrieveHistory(int userid) {
    ResultSet historySet = null;
    String retrieve = "SELECT * FROM user_history WHERE user_id=?;";
    try {
      PreparedStatement history = connection.prepareStatement(retrieve);
      history.setInt(1, userid);
      historySet = history.executeQuery();
    } catch (SQLException sqe) {
      System.out.println("Couldn't retrieve users history:" + sqe.getMessage());
    }
    assert historySet != null;
    return historySet;
  }


  public void clearHistory(int userId) {
    String clearHistory = "DELETE FROM user_history WHERE user_id = ?";
    try {
      PreparedStatement clear = connection.prepareStatement(clearHistory);
      clear.setInt(1, userId);
      clear.executeUpdate();
    } catch (SQLException sqe) {
      System.out.println("Failed to clear exercise history");
    }
  }

}
