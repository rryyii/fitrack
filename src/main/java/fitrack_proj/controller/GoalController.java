package fitrack_proj.controller;

import javax.swing.SwingUtilities;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.GoalsDAO;
import fitrack_proj.view.GoalPanel;

public class GoalController {

	public void handleMinutes(String userInput, User user) {
		int result = model.setUserGoals(user.getUserId(), "workout_per_week", Integer.parseInt(userInput), null);
		SwingUtilities.invokeLater(() -> {
			panel.getWorkoutMinutesField().setText(userInput);
		});
	}

	public void handleNumber(String userInput, User user) {
		int result = model.setUserGoals(user.getUserId(), "workout_per_week", Integer.parseInt(userInput), null);
		SwingUtilities.invokeLater(() -> {
			panel.getWorkoutNumberField().setText(userInput);
		});
	}

	public void handleGoal(String userInput, User user) {
		int result = model.setUserGoals(user.getUserId(), "weight_goal", Integer.parseInt(userInput), null);
		if (result != -1) {
			SwingUtilities.invokeLater(() -> {
				panel.getGoalWeightField().setText(userInput);

			});
		} else {
			System.err.println("Failed to set weight goal.");
		}
	}

	public void setConnection(FitrackDatabase connection) {
		this.model = new GoalsDAO(connection.getConnection());
	}

	public void setPanel(GoalPanel panel) {
		this.panel = panel;
	}

	private GoalsDAO model;
	private GoalPanel panel;



}
