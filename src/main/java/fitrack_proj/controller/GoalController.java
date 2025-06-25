package fitrack_proj.controller;

import javax.swing.JButton;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.GoalsDAO;
import fitrack_proj.view.GoalPanel;

public class GoalController {

  public void handleMinutes(String userInput, User user) {
    int result =
        model.setUserGoals(user.getUserId(), "workout_per_week", Integer.parseInt(userInput), null);
    panel.getWorkoutMinutesField().setText(userInput);
  }

  public void handleNumber(String userInput, User user) {
    int result =
        model.setUserGoals(user.getUserId(), "workout_per_week", Integer.parseInt(userInput), null);
    panel.getWorkoutNumberField().setText(userInput);
  }


  public void handleGoal(String userInput, User user) {
    int result =
        model.setUserGoals(user.getUserId(), "weight_goal", Integer.parseInt(userInput), null);
    panel.getGoalWeightField().setText(userInput);
  }


  public void setConnection(FitrackDatabase connection) {
    this.connection = connection;
    this.model = new GoalsDAO(connection.getConnection());
  }



  public void setPanel(GoalPanel panel) {
    this.panel = panel;
  }


  private FitrackDatabase connection;
  private GoalsDAO model;
  private GoalPanel panel;

  private JButton currentActivityField;
  private JButton currentWeightField;
  private JButton goalWeightField;
  private JButton workoutNumberField;
  private JButton workoutMinutesField;

}
