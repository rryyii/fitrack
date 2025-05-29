package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.UserInfo;
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
  public DashPanel(JPanel cards, FitrackDatabase connection, int user_id) {
    super(new MigLayout("wrap 1"));
  
    this.cards = cards;
    this.connection = connection;
    userInfo = new UserInfo(user_id, connection);
    CardLayout layout = (CardLayout) this.cards.getLayout();


    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cards);

    status = new JTextArea();
    status.setEditable(false);
    String statusText = "Welcome back %s !\n Current Height: %d inches \n Current Weight: %d lbs \n";
    status.setText(String.format(statusText, userInfo.getUsername(), userInfo.getHeight(),
        userInfo.getWeight()));
    add(status, "wrap");

    add(createExercisePanel());
    add(createNutritionPanel());
    add(createMenuPanel(layout), "dock west");
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
    historyTable.setVisible(true);
    updateHistory();
    panel.add(historyTable, "wrap");

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
          updateHistory();
        }
      });
    });
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
    foodTable.setVisible(true);
    updateHistory();
    panel.add(foodTable, "wrap");

    foodButton = new JButton("Submit");
    foodButton.addActionListener(e -> {
      connection.insertNutrition(userInfo.getUserId(), foodNameField.getText(),
          Integer.parseInt(servingField.getText()));
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          updateNutrition();
        }
      });
    });
    panel.add(foodButton);
    return panel;
  }

  /**
   * Creates a menu bar for the main JFrame.
   * 
   * @param layout CardLayout so the menu bar items can show the selected panel
   * @return JMenuBar menuBar with the added Swing components 
   */
  public JMenuBar createMenuBar(CardLayout layout) {
    JMenuBar menuBar = new JMenuBar();
    JMenu profileMenu = new JMenu("Profile");
    JMenuItem home = new JMenuItem("Home");
    home.addActionListener(e -> {
      layout.show(this.cards, "DASHPANEL");
    });
    JMenuItem profile = new JMenuItem("Profile");
    profile.addActionListener(e -> {
      this.cards.add(new ProfilePanel(userInfo, connection, cards), "PROFILEPANEL");
      layout.show(this.cards, "PROFILEPANEL");
    });
    JMenuItem goals = new JMenuItem("Goals");
    goals.addActionListener(e -> {
      this.cards.add(new GoalPanel(userInfo, connection, cards), "GOALPANEL");
      layout.show(this.cards, "GOALPANEL");
    });
    JMenuItem logout = new JMenuItem("Logout");
    logout.addActionListener(e -> {
      this.cards.removeAll();
      this.cards.add(new LoginPanel(cards, connection));
      this.cards.repaint();
      this.cards.revalidate();
    });
    menuBar.add(profileMenu);
    profileMenu.add(home);
    profileMenu.add(profile);
    profileMenu.add(goals);
    profileMenu.add(logout);
    return menuBar;
  }
  
  public JPanel createMenuPanel(CardLayout layout) {
	  JPanel panel = new JPanel();
	  panel.setLayout(new MigLayout("wrap 1"));
	  panel.setBackground(new Color(0, 0, 0));
	  JButton home = new JButton("Home");
	  home.addActionListener(e -> {
	      layout.show(this.cards, "DASHPANEL");
	  });
	  panel.add(home);
	  JButton profile = new JButton("Profile");
	  profile.addActionListener(e -> {
		   this.cards.add(new ProfilePanel(userInfo, connection, cards), "PROFILEPANEL");
		      layout.show(this.cards, "PROFILEPANEL");
	  });
	  panel.add(profile);
	  JButton goals = new JButton("Goals");
	  goals.addActionListener(e -> {
	      this.cards.add(new GoalPanel(userInfo, connection, cards), "GOALPANEL");
	      layout.show(this.cards, "GOALPANEL");
	  });
	  panel.add(goals);
	  JButton logout = new JButton("Logout");
	  logout.addActionListener(e -> {
	      this.cards.removeAll();
	      this.cards.add(new LoginPanel(cards, connection));
	      this.cards.repaint();
	      this.cards.revalidate();
	  });
	  panel.add(logout);
	  return panel;
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
  private void updateHistory() {
    DefaultTableModel table = (DefaultTableModel) historyTable.getModel();
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

  /**
   * Updates the nutrition table with new data.
   * 
   * @return ResultSet exerciseSet that contains data for the user's nutrition history
   */
  private ResultSet updateNutrition() {
    DefaultTableModel table = (DefaultTableModel) foodTable.getModel();
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
