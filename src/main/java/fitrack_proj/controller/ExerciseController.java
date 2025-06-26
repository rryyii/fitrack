package fitrack_proj.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.ExerciseDAO;
import fitrack_proj.view.ExercisePanel;

public class ExerciseController {
	public ExerciseController(ExercisePanel panel, FitrackDatabase connection, User user) {
		this.panel = panel;
		this.exerciseModel = new ExerciseDAO(connection.getConnection());
		this.exerciseButton = panel.getExerciseButton();
		this.userInfo = user;
		this.exerciseBox = panel.getExerciseBox();
		this.elapsedField = panel.getElapsedField();
		createExerciseListener();

	}

	private void createExerciseListener() {
		this.exerciseButton.addActionListener(e -> handleExercise());
	}

	private void handleExercise() {
		try {
			String selected = exerciseBox.getSelectedItem().toString();
			int elapsed = Integer.parseInt(elapsedField.getText());
			if (selected != null && !selected.isEmpty()) {
				this.exerciseModel.insertExercise(selected, elapsed, userInfo.getUserId());
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						updateHistory();
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "Please choose an exercise");
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Please enter a numerical value");
		}

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
				Object[] results = { exerciseType, exerciseDuration, exerciseDate };
				table.addRow(results);
			}
		} catch (SQLException sqe) {
			System.out.println("SQL error occured when retrieving exercise data: " + sqe);
		}
	}

	private User userInfo;
	private ExercisePanel panel;
	private ExerciseDAO exerciseModel;
	private JComboBox<String> exerciseBox;
	private JTextField elapsedField;
	private JButton exerciseButton;
}
