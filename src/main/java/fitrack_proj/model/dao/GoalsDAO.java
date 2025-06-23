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
    return 0;
  }

  private Connection connection;

}
