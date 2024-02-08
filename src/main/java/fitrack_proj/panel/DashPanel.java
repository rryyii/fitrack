package fitrack_proj.panel;

import fitrack_proj.app.FitnessHistory;
import fitrack_proj.util.FitUser;
import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.PasswordEncryptor;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
	private final FitrackDatabase connection;
	private FitUser userInfo;
	private final JPanel cards;
	private final JFrame frame;

	/**
	 * DashPanel constructor.
	 *
	 * @param cards      CardPanel to utilize
	 * @param connection FitrackDatabase to use for information
	 */
	public DashPanel(final JPanel cards, FitrackDatabase connection, JFrame frame) {
		this.cards = cards;
		this.connection = connection;
		this.frame = frame;
	}

	/**
	 * Sets the current user's information for the current DashPanel
	 *
	 * @param username username of the user to set as current
	 * @param password password of the user to set as current
	 */
	public void setUser(String username, String password) {
		userInfo = new FitUser();
		userInfo.setResults(connection.retrieveUser(username, PasswordEncryptor.hashPassword(password)));
		userInfo.setUsername(username);
		ResultSet results = userInfo.getResults();
		try {
			while (userInfo.getResults().next()) {
				userInfo.setUserId(results.getInt("user_id"));
				userInfo.setHeight(results.getInt("height"));
				userInfo.setWeight(results.getInt("weight"));
				userInfo.setAge(results.getInt("age"));
				userInfo.setGender(results.getString("gender"));
				userInfo.setActivity(results.getString("activity"));
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
		consts.insets = new Insets(5, 5, 5, 5);

		JTextArea status = new JTextArea();
		status.setEditable(false);
		status.setText(
				"Welcome back " + userInfo.getUsername() + "!\n" + "Current Height: " + userInfo.getHeight()
						+ " inches\n" + "Current Weight: " + userInfo.getWeight() + " lbs\n"
						+ "Projected Daily Calories Burnt: " + FitnessHistory.calculateCal(userInfo.getWeight(),
								userInfo.getHeight(), userInfo.getGender(), userInfo.getActivity(), userInfo.getAge())
						+ "\n");
		consts.gridx = 0;
		consts.gridy = 0;
		consts.gridwidth = 2;
		consts.fill = GridBagConstraints.HORIZONTAL; // Make the JTextArea horizontally expandable
		this.add(status, consts);

		consts.gridwidth = 1;
		consts.gridy = 1;
		JLabel selectActivity = new JLabel("Select performed exercise or activity type:");
		this.add(selectActivity, consts);

		consts.gridy = 2;
		final JComboBox<String> addExercise = new JComboBox<>();
		addExercise.addItem("");
		addExercise.addItem("Sports");
		addExercise.addItem("Biking");
		addExercise.addItem("Conditioning (resistance training)");
		addExercise.addItem("Cardio");
		this.add(addExercise, consts);

		consts.gridy = 3;
		JLabel exerciseTime = new JLabel("Enter elapsed time (in minutes):");
		this.add(exerciseTime, consts);
		consts.gridy = 4;
		final JTextField timeElapsed = new JTextField(10);
		this.add(timeElapsed, consts);

		consts.gridy = 6;
		JLabel historyLabel = new JLabel("Exercise History:");
		this.add(historyLabel, consts);

		consts.gridy = 7;
		String column[] = { "Exercise Type", "Duration", "Date" };
		DefaultTableModel dftm = new DefaultTableModel(column, 0);
		JTable table = new JTable(dftm);
		table.setVisible(true);
		updateHistory(dftm);
		this.add(table, consts);

		consts.gridy = 5;
		JButton exerciseButton = new JButton("Submit");
		exerciseButton.addActionListener(e -> {
			Object selected = addExercise.getSelectedItem();
			String selectedText = timeElapsed.getText();
			assert selected != null;
			connection.insertExercise(selected.toString(), Integer.parseInt(selectedText), userInfo.getUserId());
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					ResultSet result = updateHistory(dftm);
					showNutrition.updateChart(result);
					frame.pack();
				}
			});
		});
		this.add(exerciseButton, consts);

		consts.gridx = 0;
		consts.gridy = 8;
		consts.gridheight = 1;
		consts.gridwidth = 2;
		consts.fill = GridBagConstraints.HORIZONTAL;
		final JPanel nutritionPanel = showNutrition.createChart(retrieveHistory(connection, userInfo.getUserId()),
				this.frame);
		JButton chartButton = new JButton("Show Chart");
		chartButton.addActionListener(e -> {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					((CardLayout) cards.getLayout()).show(cards, "nutrition");
					frame.setSize(new Dimension(500, 500));
				}
			});
		});
		this.add(chartButton, consts);
		cards.add(nutritionPanel, "nutrition");
		clearHistory(dftm);
	}

	private ResultSet updateHistory(DefaultTableModel table) {
		table.setRowCount(0);
		ResultSet exerciseSet = retrieveHistory(connection, userInfo.getUserId());
		try {
			while (exerciseSet.next()) {
				String exerciseType = exerciseSet.getString("exercise_type");
				int exerciseDuration = exerciseSet.getInt("exercise_time");
				Date exerciseDate = exerciseSet.getDate("date");
				Object[] results = { exerciseType, exerciseDuration, exerciseDate };
				table.addRow(results);
			}

		} catch (SQLException sqe) {
			System.out.println("Failed to retrieve history");
		}
		return exerciseSet;
	}

	private void clearHistory(DefaultTableModel table) {
		JButton clearButton = new JButton();
		clearButton.setText("Clear All Exercise History");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						int paneResult = JOptionPane.showOptionDialog(clearButton,
								"Are you sure you want to clear your history?", "Confirmation",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (paneResult == JOptionPane.YES_OPTION) {
							connection.clearHistory(userInfo.getUserId());
							updateHistory(table);
						}
					}

				});

			}

		});
		this.add(clearButton);
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
