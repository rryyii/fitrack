package fitrack_proj.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalsDAO {

  public GoalsDAO(Connection connection) {
    this.connection = connection;
  }

  public ResultSet retrieveUserGoals(int userid) {
    ResultSet goalSet = null;
    String retrieve = "SELECT * FROM goal_history WHERE user_id=?;";
    try {
      PreparedStatement goals = connection.prepareStatement(retrieve);
      goals.setInt(1, userid);
      goalSet = goals.executeQuery();
    } catch (SQLException sqe) {
      System.out.println("Couldn't retrive user's current goals: " + sqe);
    }
    return goalSet;
  }

  public int setUserGoals(int userid, String field, int number, String value) {
    String goals = "INSERT INTO goal_history (user_id, " + field + ") VALUES (?, ?) "
        + "ON DUPLICATE KEY UPDATE " + field + " = VALUES(" + field + ")";
    try { 
        PreparedStatement update = connection.prepareStatement(goals);
        update.setInt(1, userid);
        if (value != null) {
            update.setString(2, value);
        } else {
            update.setInt(2, number);
        }
        return update.executeUpdate();
    } catch (SQLException sqe) {
        System.out.println("Couldn't update user's goal: " + sqe);
    }
    return -1;
}


  private Connection connection;

}
