package fitrack_proj.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.ExerciseDAO;
import fitrack_proj.model.dao.NutritionDAO;
import fitrack_proj.view.DashPanel;

public class DashController {

  public void setController(DashPanel panel, FitrackDatabase connection) {
    this.panel = panel;
    this.connection = connection;
    this.exerciseModel = new ExerciseDAO(connection.getConnection());
    this.nutritionModel = new NutritionDAO(connection.getConnection());
    this.foodButton = panel.getFoodButton();
    this.exerciseButton = panel.getExerciseButton();
    this.foodNameField = panel.getFoodNameField();
    this.servingField = panel.getServingField();
    this.exerciseBox = panel.getExerciseBox();
    this.elapsedField = panel.getElapsedField();
    this.userInfo = panel.getUserInfo();
    createDashListeners();
  }


  private void createDashListeners() {
    this.foodButton.addActionListener(e -> handleFood());
    this.exerciseButton.addActionListener(e -> handleExercise());
  }

  public void handleFood() {
    this.nutritionModel.insertNutrition(userInfo.getUserId(), foodNameField.getText(),
        Integer.parseInt(servingField.getText()));
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        updateNutrition();
      }
    });
  }

  public void handleExercise() {
    Object selected = exerciseBox.getSelectedItem();
    String selectedText = elapsedField.getText();
    assert selected != null;
    this.exerciseModel.insertExercise(selected.toString(), Integer.parseInt(selectedText),
        userInfo.getUserId());
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        updateHistory();
      }
    });
  }

  private boolean validateExercise() {
    return false;
  }

  private boolean validateFood() {
    return false;
  }



  /**
   * Dynamically updates the user's exercise table.
   * 
   * @param table The table to update with new data
   */
  private void updateHistory() {
    DefaultTableModel table = (DefaultTableModel) this.panel.getHistoryTable().getModel();
    table.setRowCount(0);
    ResultSet exerciseSet = this.exerciseModel.retrieveHistory(userInfo.getUserId());
    try {
      while (exerciseSet.next()) {
        String exerciseType = exerciseSet.getString("exercise_type");
        int exerciseDuration = exerciseSet.getInt("exercise_time");
        Date exerciseDate = exerciseSet.getDate("date");
        Object[] results = {exerciseType, exerciseDuration, exerciseDate};
        table.addRow(results);
      }
    } catch (SQLException sqe) {
      System.out.println("SQL error occured when retrieving exercise data: " + sqe);
    }
  }

  /// **
  // * Retrieves the given user's exercise history.
  // *
  // * @param connection Established SQL database connection
  // * @param userid user_id of the user to retrieve from
  // * @return Returns the ArrayList of type string containing the user's history
  // */
  // private static ResultSet retrieveNutrition(FitrackDatabase connection, int userid) {
  // return connection.retrieveNutrition(userid);
  // }

  /**
   * Updates the nutrition table with new data.
   * 
   * @return ResultSet exerciseSet that contains data for the user's nutrition history
   */
  private ResultSet updateNutrition() {
    DefaultTableModel table = (DefaultTableModel) this.panel.getFoodTable().getModel();
    table.setRowCount(0);
    ResultSet exerciseSet = this.nutritionModel.retrieveNutrition(userInfo.getUserId());
    try {
      while (exerciseSet.next()) {
        String foodName = exerciseSet.getString("food_name");
        int serving_count = exerciseSet.getInt("serving_count");
        Date date = exerciseSet.getDate("date");
        Object[] results = {foodName, serving_count, date};
        table.addRow(results);
      }
    } catch (SQLException sqe) {
      System.out.println("SQL error occured when retrieving nutrition data: " + sqe);
    }
    return exerciseSet;
  }

  private FitrackDatabase connection;
  private User userInfo;
  private ExerciseDAO exerciseModel;
  private NutritionDAO nutritionModel;

  private DashPanel panel;
  private JComboBox<String> exerciseBox;
  private JTextField elapsedField;
  private JTextField foodNameField;
  private JTextField servingField;
  private JButton foodButton;
  private JButton exerciseButton;
}
