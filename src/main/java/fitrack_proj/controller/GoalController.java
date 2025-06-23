package fitrack_proj.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.GoalsDAO;

public class GoalController {

  public GoalController() {

  }

  private User user;
  private FitrackDatabase connection;
  private GoalsDAO model;


  // class GoalListener implements MouseListener {
  // public GoalListener(String goalToChange, FitrackDatabase connection, User user) {
  // this.goalToChange = goalToChange;
  // this.user = user;
  // this.connection = connection;
  // }
  //
  // public String goalToChange;
  // private User user;
  // private FitrackDatabase connection;
  //
  // @Override
  // public void mouseClicked(MouseEvent e) {
  // String userInput = "";
  // int result = 0;
  // switch (this.goalToChange) {
  // case "currentWeight":
  // userInput = JOptionPane.showInputDialog(null, "Change Current Weight");
  // result = connection.setUserGoals(user.getUserId(), "weight", Integer.parseInt(userInput),
  // null);
  // return;
  // case "goalWeight":
  // userInput = JOptionPane.showInputDialog(null, "New Goal Weight");
  // result = connection.setUserGoals(user.getUserId(), "weight_goal", Integer.parseInt(userInput),
  // null);
  // return;
  // case "minutesPerWeek":
  // userInput = JOptionPane.showInputDialog(null, "New Minutes per Week Goal");
  // result = connection.setUserGoals(user.getUserId(), "workout_minutes_per_week",
  // Integer.parseInt(userInput),
  // null);
  // return;
  // case "workoutsPerWeek":
  // userInput = JOptionPane.showInputDialog(null, "New Workout per Week Goal");
  // result = connection.setUserGoals(user.getUserId(), "workout_per_week",
  // Integer.parseInt(userInput), null);
  // return;
  // case "activityLevel":
  // JComboBox<String> activityField = new JComboBox<>();
  // activityField.addItem("");
  // activityField.addItem("Little to no exercise");
  // activityField.addItem("Moderate exercise");
  // activityField.addItem("Active");
  // activityField.addItem("Very Active");
  // JOptionPane.showMessageDialog(null, activityField);
  // result = connection.setUserGoals(user.getUserId(), "activity", 0,
  // activityField.getSelectedItem().toString());
  // return;
  // }
  //
  // }
  //
  // @Override
  // public void mousePressed(MouseEvent e) {
  // // TODO Auto-generated method stub
  //
  // }
  //
  // @Override
  // public void mouseReleased(MouseEvent e) {
  // // TODO Auto-generated method stub
  //
  // }
  //
  // @Override
  // public void mouseEntered(MouseEvent e) {
  // // TODO Auto-generated method stub
  //
  // }
  //
  // @Override
  // public void mouseExited(MouseEvent e) {
  // // TODO Auto-generated method stub
  //
  // }
  //
  // }

}
