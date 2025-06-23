package fitrack_proj.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import fitrack_proj.controller.DashController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.LoginDAO;
import net.miginfocom.swing.MigLayout;

/**
 * DashPanel class creates the dashboard panel for the user to view their exercise and nutrition
 * history.
 */
public class DashPanel extends JPanel {

  /**
   * DashPanel main constructor creates the dashboard panel
   * 
   * @param cards JPanel of the current cards in the CardLayout
   * @param connection Established SQL database connection
   * @param user_id Currently logged in user's id
   */
  public DashPanel(JPanel cards, FitrackDatabase connection, LoginDAO loginModel, int user_id,
      PanelController panelController, DashController dashController) {
    super(new MigLayout("wrap 1"));
    this.cards = cards;
    this.connection = connection;
    this.panelController = panelController;
    userInfo = new User(user_id, loginModel);

    status = new JTextArea();
    status.setEditable(false);
    String statusText =
        "Welcome back %s !\n Current Height: %d inches \n Current Weight: %d lbs \n";
    status.setText(String.format(statusText, userInfo.getUsername(), userInfo.getHeight(),
        userInfo.getWeight()));
    add(status, "wrap");
    
    panelController.setUser(userInfo);
    panelController.createMainPanel();
    
    add(panelController.getMainPanel(), "dock west");
    add(createExercisePanel());
    add(createNutritionPanel());
    dashController.setController(this, connection);
    this.dashController = dashController;
  }

  /**
   * Creates the main panel for inputting exercise into the user's history.
   * 
   * @return JPanel panel with the added exercise Swing components
   */
  public JPanel createExercisePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    exerciseSelectLabel = new JLabel("Select performed exercise or activity type:");
    panel.add(exerciseSelectLabel);

    exerciseBox = new JComboBox<>();
    exerciseBox.addItem("");
    exerciseBox.addItem("Sports");
    exerciseBox.addItem("Biking");
    exerciseBox.addItem("Conditioning (resistance training)");
    exerciseBox.addItem("Cardio");
    panel.add(exerciseBox, "wrap");

    exerciseTimeLabel = new JLabel("Enter elapsed time (in minutes):");
    panel.add(exerciseTimeLabel);
    elapsedField = new JTextField(10);
    panel.add(elapsedField, "wrap");

    exerciseHistoryLabel = new JLabel("Exercise History:");
    panel.add(exerciseHistoryLabel, "wrap");

    String column[] = {"Exercise Type", "Duration", "Date"};
    DefaultTableModel exerciseTable = new DefaultTableModel(column, 0);
    historyTable = new JTable(exerciseTable);
    historyTable.setFocusable(false);
    historyTable.setVisible(true);
        panel.add(historyTable, "wrap");

    exerciseButton = new JButton("Submit");

    panel.add(exerciseButton, "wrap 30px");
    return panel;
  }

  /**
   * Creates a new panel for inputting nutrition information for the user.
   * 
   * @return JPanel panel with the added nutritional Swing components
   */
  public JPanel createNutritionPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    nutritionLabel = new JLabel("Nutrition Tracker: ");
    panel.add(nutritionLabel, "wrap");

    foodNameLabel = new JLabel("Food Name: ");
    panel.add(foodNameLabel);
    foodNameField = new JTextField(10);
    panel.add(foodNameField, "wrap");

    servingLabel = new JLabel("Servings: ");
    panel.add(servingLabel);
    servingField = new JTextField(10);
    panel.add(servingField, "wrap");

    foodHistoryLabel = new JLabel("Food History:");
    panel.add(foodHistoryLabel, "wrap");


    String foodColumn[] = {"Food", "Calories", "Date"};
    DefaultTableModel nutritionTable = new DefaultTableModel(foodColumn, 0);
    foodTable = new JTable(nutritionTable);
    foodTable.setFocusable(false);
    foodTable.setVisible(true);
    panel.add(foodTable, "wrap");

    foodButton = new JButton("Submit");
    panel.add(foodButton);
    return panel;
  }

  public JButton getExerciseButton() {
    return exerciseButton;
  }

  public JButton getFoodButton() {
    return foodButton;
  }

  public JTextField getElapsedField() {
    return elapsedField;
  }

  public JTextField getServingField() {
    return servingField;
  }

  public JTextField getFoodNameField() {
    return foodNameField;
  }

  public JComboBox<String> getExerciseBox() {
    return exerciseBox;
  }

  public JTable getHistoryTable() {
    return historyTable;
  }

  public JTable getFoodTable() {
    return foodTable;
  }

  public User getUserInfo() {
    return userInfo;
  }

  private JPanel cards;
  private User userInfo;
  private FitrackDatabase connection;
  private PanelController panelController;
  private DashController dashController;

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
