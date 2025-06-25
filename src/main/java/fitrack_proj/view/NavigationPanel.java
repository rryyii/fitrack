package fitrack_proj.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import fitrack_proj.controller.PanelController;
import fitrack_proj.model.User;
import net.miginfocom.swing.MigLayout;

public class NavigationPanel extends JPanel {
	public NavigationPanel(JPanel cards, PanelController controller, User user) {
		this.controller = controller;
		this.setLayout(new MigLayout());
		this.setBackground(new Color(0, 0, 0));
		this.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
		JButton home = new JButton("Home");
		home.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		home.addActionListener(e -> {
			this.controller.showDashPanel();
		});
		this.add(home);
		JButton profile = new JButton("Profile");
		profile.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		profile.addActionListener(e -> {
			controller.createProfileController();
			controller.showProfilePanel();
		});
		this.add(profile);
		JButton goals = new JButton("Goals");
		goals.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		goals.addActionListener(e -> {
			controller.createGoalController();
			controller.showGoalPanel();
		});
		this.add(goals);
		JButton logout = new JButton("Logout");
		logout.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		logout.addActionListener(e -> {
		});
		this.add(logout);
	}

	private PanelController controller;

}
