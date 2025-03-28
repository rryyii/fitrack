package fitrack_proj.panel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.UserInfo;
import net.miginfocom.swing.MigLayout;

public class DashPanel extends JPanel {

  /**
   * DashPanel main constructor creates the dashboard panel
   * 
   * @param cards JPanel of the current cards in the CardLayout
   * @param connection Established SQL database connection
   * @param user_id Currently logged in user's id
   */
  public DashPanel(JPanel cards, FitrackDatabase connection, int user_id) {
    super(new MigLayout());
    this.cards = cards;
    this.connection = connection;
    userInfo = new UserInfo(user_id, connection);


    status = new JTextArea();
    status.setEditable(false);
    String statusText = "Welcome back %s !\n Current Height: %d inches \n Current Weigh: %d lbs \n";
    status.setText(String.format(statusText, userInfo.getUsername(), userInfo.getHeight(),
        userInfo.getWeight()));
    add(status, "wrap");

    exerciseSelectLabel = new JLabel("Select performed exercise or activity type:");
    add(exerciseSelectLabel);

    exerciseBox = new JComboBox<>();
    exerciseBox.addItem("");
    exerciseBox.addItem("Sports");
    exerciseBox.addItem("Biking");
    exerciseBox.addItem("Conditioning (resistance training)");
    exerciseBox.addItem("Cardio");
    add(exerciseBox, "wrap");

    exerciseTimeLabel = new JLabel("Enter elapsed time (in minutes):");
    add(exerciseTimeLabel);
    elapsedField = new JTextField(10);
    add(elapsedField, "wrap");

    exerciseHistoryLabel = new JLabel("Exercise History:");
    add(exerciseHistoryLabel, "wrap");

    String column[] = {"Exercise Type", "Duration", "Date"};
    DefaultTableModel exerciseTable = new DefaultTableModel(column, 0);
    historyTable = new JTable(exerciseTable);
    historyTable.setVisible(true);
    updateHistory(exerciseTable);
    add(historyTable, "wrap");

    exerciseButton = new JButton("Submit");
    exerciseButton.addActionListener(e -> {
      Object selected = exerciseBox.getSelectedItem();
      String selectedText = elapsedField.getText();
      assert selected != null;
      connection.insertExercise(selected.toString(), Integer.parseInt(selectedText),
          userInfo.getUserId());
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          updateHistory(exerciseTable);
        }
      });
    });
    add(exerciseButton, "wrap 30px");


    nutritionLabel = new JLabel("Nutrition Tracker: ");
    add(nutritionLabel, "wrap");

    foodNameLabel = new JLabel("Food Name: ");
    add(foodNameLabel);
    foodNameField = new JTextField(10);
    add(foodNameField, "wrap");

    servingLabel = new JLabel("Servings: ");
    add(servingLabel);
    servingField = new JTextField(10);
    add(servingField, "wrap");

    foodHistoryLabel = new JLabel("Food History:");
    add(foodHistoryLabel, "wrap");


    String foodColumn[] = {"Food", "Calories", "Date"};
    DefaultTableModel nutritionTable = new DefaultTableModel(foodColumn, 0);
    foodTable = new JTable(nutritionTable);
    foodTable.setVisible(true);
    updateHistory(nutritionTable);
    add(foodTable, "wrap");

    foodButton = new JButton("Submit");
    foodButton.addActionListener(e -> {
      connection.insertNutrition(userInfo.getUserId(), foodNameField.getText(),
          Integer.parseInt(servingField.getText()));
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          updateNutrition(nutritionTable);
        }
      });
    });
    add(foodButton);
  }



  /**
   * Retrieves the given user's exercise history.
   *
   * @param connection Established SQL database connection
   * @param userid user_id of the user to retrieve from
   * @return Returns the ArrayList of type string containing the user's history
   */
  private static ResultSet retrieveHistory(FitrackDatabase connection, int userid) {
    return connection.retrieveHistory(userid);
  }

  /**
   * Dynamically updates the user's exercise table.
   * 
   * @param table The table to update with new data
   */
  private void updateHistory(DefaultTableModel table) {
    table.setRowCount(0);
    ResultSet exerciseSet = retrieveHistory(connection, userInfo.getUserId());
    try {
      while (exerciseSet.next()) {
        String exerciseType = exerciseSet.getString("exercise_type");
        int exerciseDuration = exerciseSet.getInt("exercise_time");
        Date exerciseDate = exerciseSet.getDate("date");
        Object[] results = {exerciseType, exerciseDuration, exerciseDate};
        table.addRow(results);
      }
    } catch (SQLException sqe) {
    }
  }

  /**
   * Retrieves the given user's exercise history.
   *
   * @param connection Established SQL database connection
   * @param userid user_id of the user to retrieve from
   * @return Returns the ArrayList of type string containing the user's history
   */
  private static ResultSet retrieveNutrition(FitrackDatabase connection, int userid) {
    return connection.retrieveNutrition(userid);
  }

  private ResultSet updateNutrition(DefaultTableModel table) {
    table.setRowCount(0);
    ResultSet exerciseSet = retrieveNutrition(connection, userInfo.getUserId());
    try {
      while (exerciseSet.next()) {
        String foodName = exerciseSet.getString("food_name");
        int serving_count = exerciseSet.getInt("serving_count");
        Date date = exerciseSet.getDate("date");
        Object[] results = {foodName, serving_count, date};
        table.addRow(results);
      }
    } catch (SQLException sqe) {
    }
    return exerciseSet;
  }

  private JPanel cards;
  private UserInfo userInfo;
  private FitrackDatabase connection;

  private JTextArea status;
  private JButton exerciseButton;
  private JButton foodButton;
  private JTextField elapsedField;
  private JTextField servingField;
  private JTextField foodNameField;
  private JLabel exerciseTimeLabel;
  private JLabel nutritionLabel;
  private JLabel foodNameLabel;
  private JLabel foodHistoryLabel;
  private JLabel servingLabel;
  private JLabel exerciseHistoryLabel;
  private JLabel exerciseSelectLabel;
  private JComboBox<String> exerciseBox;
  private JTable historyTable;
  private JTable foodTable;


}
