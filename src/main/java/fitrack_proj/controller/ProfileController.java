package fitrack_proj.controller;

import javax.swing.SwingUtilities;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.ProfileDAO;
import fitrack_proj.view.DashPanel;
import fitrack_proj.view.ProfilePanel;

public class ProfileController {

	public void handleUsername(String username) {
		int result = model.updateUsername(user.getUserId(), username);
		if (result != -1) {
			SwingUtilities.invokeLater(() -> {
				panel.getUsernameField().setText(username);
				dash.getUsername().setText(username);
			});
		} else {
			System.err.println("Failed to set username.");
		}

	}

	public void handleWeight(int weight) {
		int result = model.updateWeight(user.getUserId(), weight);
		if (result != -1) {
			SwingUtilities.invokeLater(() -> {
				panel.getWeightField().setText(String.valueOf(weight));
				dash.getWeight().setText(String.valueOf(weight));
			});
		} else {
			System.err.println("Failed to set weight");
		}

	}

	public void handleActivity(String activity) {
		int result = model.updateActivity(user.getUserId(), activity);
		if (result != -1) {
			SwingUtilities.invokeLater(() -> {
				panel.getActivityField().setText(activity);
			});
		} else {
			System.err.println("Failed to set activity.");
		}

	}

	public void setPanel(ProfilePanel panel) {
		this.panel = panel;
	}

	public void setDashPanel(DashPanel dash) {
		this.dash = dash;
	}

	public void setConnection(FitrackDatabase connection) {
		this.connection = connection;
		this.model = new ProfileDAO(this.connection.getConnection());
	}

	public void setUser(User user) {
		this.user = user;
	}

	private User user;
	private FitrackDatabase connection;
	private ProfileDAO model;
	private DashPanel dash;
	private ProfilePanel panel;
}
