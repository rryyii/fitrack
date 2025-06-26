package fitrack_proj.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.NutritionDAO;
import fitrack_proj.view.NutritionPanel;

public class NutritionController {
  public NutritionController(NutritionPanel panel, FitrackDatabase connection, User user) {
    this.userInfo = user;
    this.panel = panel;
    this.nutritionModel = new NutritionDAO(connection.getConnection());
    this.carbField = panel.getCarbField();
    this.calorieField = panel.getCaloriesField();
    this.fatField = panel.getFatField();
    this.typeField = panel.getTypeField();
    this.proteinField = panel.getProteinField();
    this.caloriesBar = panel.getCaloriesBar();
    this.fatBar = panel.getFatBar();
    this.carbBar = panel.getCarbBar();
    this.proteinBar = panel.getProteinBar();
    this.foodNameField = panel.getFoodNameField();
    this.foodButton = panel.getFoodButton();
    this.servingField = panel.getServingField();
    createNutritionListener();
  }

  private void createNutritionListener() {
    this.foodButton.addActionListener(e -> handleFood());
  }

  private void handleFood() {
    String food = foodNameField.getText();
    int serving = Integer.parseInt(servingField.getText());
    int calories = Integer.parseInt(calorieField.getText());
    int protein = Integer.parseInt(proteinField.getText());
    int carbs = Integer.parseInt(carbField.getText());
    int fats = Integer.parseInt(fatField.getText());
    String type = typeField.getSelectedItem().toString();
    if (food != null && !food.isEmpty() && serving >= 0) {
      if (this.nutritionModel.insertNutrition(userInfo.getUserId(), food, serving, calories,
          protein, carbs, fats, type) != -1) {
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            updateNutrition();
            caloriesBar.setValue(caloriesBar.getValue() + calories);
            proteinBar.setValue(proteinBar.getValue() + protein);
            fatBar.setValue(fatBar.getValue() + fats);
            carbBar.setValue(carbBar.getValue() + carbs);
          }
        });
      } else {
        JOptionPane.showMessageDialog(null, "Failed to insert into food log.");
      }
    }
  }

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
        int calories = exerciseSet.getInt("calories");
        int protein = exerciseSet.getInt("protein");
        int carbs = exerciseSet.getInt("carbohydrates");
        int fats = exerciseSet.getInt("fats");
        String type = exerciseSet.getString("meal_type");
        Date date = exerciseSet.getDate("date");
        Object[] results = {foodName, serving_count, calories, protein, carbs, fats, type, date};
        table.addRow(results);
      }
    } catch (SQLException sqe) {
      System.out.println("SQL error occured when retrieving nutrition data: " + sqe);
    }
    return exerciseSet;
  }

  private NutritionPanel panel;
  private User userInfo;
  private NutritionDAO nutritionModel;
  private JProgressBar caloriesBar;
  private JProgressBar proteinBar;
  private JProgressBar carbBar;
  private JProgressBar fatBar;
  private JTextField foodNameField;
  private JTextField servingField;
  private JTextField calorieField;
  private JTextField proteinField;
  private JTextField carbField;
  private JTextField fatField;
  private JComboBox<String> typeField;
  private JButton foodButton;
}
