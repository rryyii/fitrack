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
		JButton nutrition = new JButton("Nutrition");
		nutrition.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		nutrition.addActionListener(e -> {
			controller.createNutritionPanel();
			controller.createNutritionController(user);
			controller.showNutritionPanel();
		});
		this.add(nutrition);
		JButton exercise = new JButton("Exercise");
		exercise.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		exercise.addActionListener(e -> {
			controller.createExercisePanel();
			controller.createExerciseController();
			controller.showExercisePanel();
		});
		this.add(exercise);
		JButton goals = new JButton("Goals");
		goals.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		goals.addActionListener(e -> {
			controller.createGoalController();
			controller.showGoalPanel();
		});
		this.add(goals);
		JButton profile = new JButton("Profile");
		profile.putClientProperty(FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_TOOLBAR_BUTTON);
		profile.addActionListener(e -> {
			controller.createProfileController();
			controller.showProfilePanel();
		});
		this.add(profile);

	}

	private PanelController controller;

}
