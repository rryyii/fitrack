package fitrack_proj.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fitrack_proj.app.FitnessHistory;
import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.PasswordEncryptor;

/**
 * Utilpanel Class.
 *
 * @author yirw
 *
 */
public class DashPanel extends JPanel {

	private static final long serialVersionUID = 1717879360554347766L;
	private String system;
	private String measure;
	private int height;
	private int weight;
	private int age;
	private String activity;
	private int userId;
	private String gender;
	private String username;
	private FitrackDatabase connection;
	private JPanel cards;
	private ResultSet results;
	private String heightMeasure;

	private GridBagConstraints consts;

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
	 * @param username
	 * @param password
	 */
	public void setUser(String username, String password) {
		results = connection.retrieveUser(username, PasswordEncryptor.hashPassword(password));
		this.username = username;
		try {
			while (results.next()) {
				this.userId = results.getInt("id");
				this.height = results.getInt("height");
				this.weight = results.getInt("weight");
				this.age = results.getInt("age");
				this.gender = results.getString("gender");
				this.activity = results.getString("activity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes the CardLayout's current panel the Dash panel
	 *
	 */
	public void showPanel() {
//		JLabel test = new JLabel();
//		if (this.metric) {
//			test.setText("metric");
//		} else {
//			test.setText("imperial");
//		}
//		this.add(test);
		this.setLayout(new GridBagLayout());
		consts = new GridBagConstraints();
		JButton returnButton = new JButton();
		returnButton.setText("Return");
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout) cards.getLayout()).show(cards, "login");
			}
		});
		JTextArea status = new JTextArea();
		status.setEditable(false);
		status.setText("Welcome back " + username + "!\n" +
                "Current Height: " + height + " " + heightMeasure + "\n" +
				"Current Weight: " + weight + " " + measure + "\n" +
                "Current recommended calories: " +
                FitnessHistory.calculateCal(weight, height, gender, activity, age, measure) + "\n" +
				"Weight Goal Progress: ");
		consts.gridx = 0;
		consts.gridy = 0;
		this.add(status, consts);
		consts.gridx = 0;
		consts.gridy = 0;
		JTextArea test2 = new JTextArea();
		test2.setText("testestestsetestset");
		test2.setBackground(Color.WHITE);
		consts.gridx = 1;
		consts.gridy = 0;
		this.add(test2, consts);
		JTextArea test3 = new JTextArea();
		test3.setText("meoweoadosaodas");
		test3.setBackground(Color.BLUE);
		consts.gridx = 2;
		consts.gridy = 1;
		this.add(test3, consts);
//		JLabel selectActivity = new JLabel();
//		selectActivity.setText("Select performed exercise or activity type:");
//		final JComboBox<String> addExercise = new JComboBox<>();
//		addExercise.addItem("");
//		addExercise.addItem("Sports");
//		addExercise.addItem("Biking");
//		addExercise.addItem("Conditioning (resistance training)");
//		addExercise.addItem("Cardio");
//		final JTextField timeElapsed = new JTextField(10);
//		JLabel exerciseTime = new JLabel();
//		exerciseTime.setText("Enter elapsed time (in minutes):");
//		JButton registerExercise = new JButton();
//		registerExercise.setText("Submit");
//		registerExercise.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Object t = addExercise.getSelectedItem();
//				String m = timeElapsed.getText();
//				connection.insertExercise(t.toString(), Integer.parseInt(m), userId);
//			}
//		});

//		JLabel history = new JLabel();
//		StringBuilder test = new StringBuilder();
//		ArrayList<String> tests = FitnessHistory.retrieveHistory(connection, username, results, userId);
//		for (String s : tests) {
//			test.append(s + "\n");
//		}
//		history.setText(test.toString());
//		consts.gridx = 1; 
//		consts.gridy = 3;
//		this.add(selectActivity, consts);
//		consts.gridx = 1; 
//		consts.gridy = 4;
//		this.add(addExercise, consts);
//		consts.gridx = 2; 
//		consts.gridy = 5;
//		this.add(exerciseTime, consts);
//		consts.gridx = 2; 
//		consts.gridy = 6;
//		this.add(timeElapsed, consts);
//		consts.gridx = 1; 
//		consts.gridy = 1;
//		this.add(registerExercise, consts);
//		consts.gridx = 1; 
//		consts.gridy = 1;
//		this.add(history, consts);
	}
	
	public void setSystem(String measure, String heightMeasure) {
		this.measure = measure;
		this.heightMeasure = heightMeasure;
	}
}
