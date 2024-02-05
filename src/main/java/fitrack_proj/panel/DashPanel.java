package fitrack_proj.panel;

import fitrack_proj.app.FitnessHistory;
import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.PasswordEncryptor;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * DashPanel Class.
 *
 * @author yirw
 */
public class DashPanel extends JPanel {
	private static final long serialVersionUID = -2136961495276530281L;
	private int userId;
	private int height;
	private int weight;
	private int age;
	private String activity;
	private String gender;
	private String username;
	private final FitrackDatabase connection;
	private final JPanel cards;
	private ResultSet results;

	/**
	 * DashPanel constructor.
	 *
	 * @param cards      CardPanel to utilize
	 * @param connection FitrackDatabase to use for information
	 */
	public DashPanel(final JPanel cards, FitrackDatabase connection) {
		this.cards = cards;
		this.connection = connection;
	}

	/**
	 * Sets the current user's information for the current DashPanel
	 *
	 * @param username username of the user to set as current
	 * @param password password of the user to set as current
	 */
	public void setUser(String username, String password) {
		results = connection.retrieveUser(username, PasswordEncryptor.hashPassword(password));
		this.username = username;
		try {
			while (results.next()) {
				this.userId = results.getInt("user_id");
				this.height = results.getInt("height");
				this.weight = results.getInt("weight");
				this.age = results.getInt("age");
				this.gender = results.getString("gender");
				this.activity = results.getString("activity");
			}
		} catch (SQLException sqe) {
			System.out.println("Failed to set the user to the current DashPanel.");
		}
	}

	/**
	 * Makes the CardLayout's current panel the Dash panel
	 * 
	 */
	public void showPanel() {
		NutritionPanel showNutrition = new NutritionPanel(cards);
		this.removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints consts = new GridBagConstraints();
		consts.insets = new Insets(5, 5, 5, 5); // Add spacing between components

		JTextArea status = new JTextArea();
		status.setEditable(false);
		status.setText("Welcome back " + username + "!\n" + "Current Height: " + height + " inches\n"
				+ "Current Weight: " + weight + " lbs\n" + "Projected Daily Calories Burnt: "
				+ FitnessHistory.calculateCal(weight, height, gender, activity, age) + "\n");
		consts.gridy = 1;
		consts.fill = GridBagConstraints.HORIZONTAL; // Make the JTextArea horizontally expandable
		this.add(status, consts);

		JLabel selectActivity = new JLabel("Select performed exercise or activity type:");
		consts.gridy = 2;
		consts.fill = GridBagConstraints.NONE; // Reset fill to default
		this.add(selectActivity, consts);

		final JComboBox<String> addExercise = new JComboBox<>();
		addExercise.addItem("");
		addExercise.addItem("Sports");
		addExercise.addItem("Biking");
		addExercise.addItem("Conditioning (resistance training)");
		addExercise.addItem("Cardio");
		consts.gridy = 3;
		this.add(addExercise, consts);

		final JTextField timeElapsed = new JTextField(10);
		JLabel exerciseTime = new JLabel("Enter elapsed time (in minutes):");
		consts.gridy = 4;
		this.add(exerciseTime, consts);
		consts.gridy = 5;
		this.add(timeElapsed, consts);

		JLabel history = new JLabel("Exercise History:");
		consts.gridy = 7;
		this.add(history, consts);
		JTextArea historyText = new JTextArea();
		updateHistory(historyText);
		consts.gridy = 8;
		this.add(historyText, consts);

		JButton registerExercise = new JButton("Submit");
		registerExercise.addActionListener(e -> {
			Object selected = addExercise.getSelectedItem();
			String selectedText = timeElapsed.getText();
			assert selected != null;
			connection.insertExercise(selected.toString(), Integer.parseInt(selectedText), userId);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					updateHistory(historyText);
				}
			});
		});
		consts.gridy = 6;
		this.add(registerExercise, consts);

		final JPanel nutritionPanel = showNutrition.createChart(retrieveHistory(connection, userId));
		JButton chartButton = new JButton("Show Chart");
		chartButton.addActionListener(e -> {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					((CardLayout) cards.getLayout()).show(cards, "nutrition");
				}
			});
		});
		consts.gridx = 0;
		consts.gridy = 0;
		this.add(chartButton, consts);
		cards.add(nutritionPanel, "nutrition");
	}

	private ResultSet updateHistory(JTextArea historyText) {
		historyText.setText("");
		ResultSet exerciseSet = retrieveHistory(connection, userId);
		try {
			while (exerciseSet.next()) {
				String exerciseType = exerciseSet.getString("exercise_type");
				int exerciseDuration = exerciseSet.getInt("exercise_time");
				Date exerciseDate = exerciseSet.getDate("date");
				String output = exerciseType + " " + exerciseDuration + " " + exerciseDate;
				historyText.append(output + "\n");
			}

		} catch (SQLException sqe) {
			System.out.println("Failed to retrieve history");
		}
		return exerciseSet;
	}

	/**
	 * Retrieves the given user's exercise history.
	 *
	 * @param connection Established SQL database connection
	 * @param userid     user_id of the user to retrieve from
	 * @return Returns the ArrayList of type string containing the user's history
	 */
	private static ResultSet retrieveHistory(FitrackDatabase connection, int userid) {
		return connection.retrieveHistory(userid);
	}
}
