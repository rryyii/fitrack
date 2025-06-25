package fitrack_proj.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import fitrack_proj.controller.DashController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.model.enums.NutritionType;
import net.miginfocom.swing.MigLayout;

/**
 * DashPanel class creates the dashboard panel for the user to view their
 * exercise and nutrition history.
 */
public class DashPanel extends JPanel {

	/**
	 * DashPanel main constructor creates the dashboard panel
	 * 
	 * @param cards      JPanel of the current cards in the CardLayout
	 * @param connection Established SQL database connection
	 * @param user_id    Currently logged in user's id
	 */
	public DashPanel(JPanel cards, FitrackDatabase connection, LoginDAO loginModel, int user_id,
			PanelController panelController, DashController dashController) {
		super(new MigLayout("wrap 1"));
		userInfo = new User(user_id, loginModel);
		panelController.setUser(userInfo);
		add(createInfoPanel());
		add(createExercisePanel());
		add(createNutritionPanel());
		dashController.setController(this, connection);

		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});

	}

	private JPanel createInfoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		status = new JTextArea();
		status.setEditable(false);
		String statusText = "Welcome back %s !\n Current Height: %d inches \n Current Weight: %d lbs \n";
		status.setText(String.format(statusText, userInfo.getUsername(), userInfo.getHeight(), userInfo.getWeight()));
		panel.add(status, "wrap");
		UIManager.put("ProgressBar.horizontalSize", new Dimension(250, 15));
		caloriesBar = new JProgressBar(SwingConstants.HORIZONTAL);
		caloriesBar.setStringPainted(true);
		panel.add(caloriesBar, "wrap");
		proteinBar = new JProgressBar(SwingConstants.HORIZONTAL);
		proteinBar.setStringPainted(true);
		proteinBar.setMaximum(NutritionType.PROTEIN.getValue());
		panel.add(proteinBar, "wrap");
		carbBar = new JProgressBar(SwingConstants.HORIZONTAL);
		carbBar.setStringPainted(true);
		carbBar.setMaximum(NutritionType.CARBOHYDRATE.getValue());
		panel.add(carbBar, "wrap");
		fatBar = new JProgressBar(SwingConstants.HORIZONTAL);
		fatBar.setStringPainted(true);
		fatBar.setMaximum(NutritionType.FAT.getValue());
		panel.add(fatBar);
		return panel;
	}

	/**
	 * Creates the main panel for inputting exercise into the user's history.
	 * 
	 * @return JPanel panel with the added exercise Swing components
	 */
	private JPanel createExercisePanel() {
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

		String column[] = { "Exercise Type", "Duration", "Date" };
		DefaultTableModel exerciseTable = new DefaultTableModel(column, 0);
		historyTable = new JTable(exerciseTable);
		historyTable.setFocusable(false);
		historyTable.setVisible(true);
		JScrollPane scroll = new JScrollPane(historyTable);
		panel.add(scroll, "wrap");

		exerciseButton = new JButton("Submit");

		panel.add(exerciseButton, "wrap 30px");
		return panel;
	}

	/**
	 * Creates a new panel for inputting nutrition information for the user.
	 * 
	 * @return JPanel panel with the added nutritional Swing components
	 */
	private JPanel createNutritionPanel() {
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

		caloriesLabel = new JLabel("Calories: ");
		panel.add(caloriesLabel);
		caloriesField = new JTextField(10);
		panel.add(caloriesField, "wrap");

		proteinLabel = new JLabel("Protein: ");
		panel.add(proteinLabel);
		proteinField = new JTextField(10);
		panel.add(proteinField, "wrap");

		carbLabel = new JLabel("Carbohydrates: ");
		panel.add(carbLabel);
		carbField = new JTextField(10);
		panel.add(carbField, "wrap");

		fatLabel = new JLabel("Fats: ");
		panel.add(fatLabel);
		fatField = new JTextField(10);
		panel.add(fatField, "wrap");

		typeLabel = new JLabel("Meal Type: ");
		panel.add(typeLabel);
		typeField = new JComboBox<String>();
		typeField.addItem("Breakfast");
		typeField.addItem("Lunch");
		typeField.addItem("Dinner");
		typeField.addItem("Snack");
		panel.add(typeField, "wrap");

		foodHistoryLabel = new JLabel("Food History:");
		panel.add(foodHistoryLabel, "wrap");

		String foodColumn[] = { "Food", "Servings", "Calories", "Protein", "Carbs", "Fats", "Meal Type", "Date" };
		DefaultTableModel nutritionTable = new DefaultTableModel(foodColumn, 0);
		foodTable = new JTable(nutritionTable);
		foodTable.setFocusable(false);
		foodTable.setVisible(true);

		JScrollPane scroll = new JScrollPane(foodTable);
		panel.add(scroll, "wrap");

		foodButton = new JButton("Submit");
		panel.add(foodButton);
		return panel;
	}

	public JTextField getCaloriesField() {
		return caloriesField;
	}

	public JTextField getProteinField() {
		return proteinField;
	}

	public JTextField getCarbField() {
		return carbField;
	}

	public JTextField getFatField() {
		return fatField;
	}

	public JProgressBar getCaloriesBar() {
		return caloriesBar;
	}

	public JComboBox<String> getTypeField() {
		return typeField;
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

	public JProgressBar getProteinBar() {
		return proteinBar;
	}

	public JProgressBar getCarbBar() {
		return carbBar;
	}

	public JProgressBar getFatBar() {
		return fatBar;
	}

	private User userInfo;

	private JTextArea status;
	private JButton exerciseButton;
	private JButton foodButton;
	private JTextField elapsedField;
	private JTextField servingField;
	private JTextField foodNameField;
	private JTextField caloriesField;
	private JTextField proteinField;
	private JTextField carbField;
	private JTextField fatField;
	private JProgressBar caloriesBar;
	private JProgressBar proteinBar;
	private JProgressBar carbBar;
	private JProgressBar fatBar;
	private JComboBox<String> typeField;

	private JLabel caloriesLabel;
	private JLabel proteinLabel;
	private JLabel carbLabel;
	private JLabel fatLabel;
	private JLabel typeLabel;
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
