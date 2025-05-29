package fitrack_proj.panel;

import javax.swing.JPanel;

import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.UserInfo;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

/**
 * GoalPanel class creates the main panel for a user's fitness/nutrition goals
 */
public class GoalPanel extends JPanel {

	/**
	 * GoalPanel Main Constructor
	 */
	public GoalPanel(UserInfo user, FitrackDatabase connection, JPanel cards) {
		super(new MigLayout("wrap 1"));
		this.user = user;
		this.connection = connection;
		this.cards = cards;
		add(createGoalList());
		add(createFitnessGoals());
		CardLayout layout = (CardLayout) this.cards.getLayout();
		add(createMenuPanel(layout), "dock west");
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
			this.cards.add(new ProfilePanel(user, connection, cards), "PROFILEPANEL");
			layout.show(this.cards, "PROFILEPANEL");
		});
		panel.add(profile);
		JButton goals = new JButton("Goals");
		goals.addActionListener(e -> {
			this.cards.add(new GoalPanel(user, connection, cards), "GOALPANEL");
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
	 * Creates a new panel for the user's goals list
	 * 
	 * @return JPanel panel with the added Swing components
	 */
	public JPanel createGoalList() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 2"));

		JLabel currentWeightLabel = new JLabel("Current Weight");
		JLabel currentWeightField = new JLabel(String.valueOf(user.getWeight()));
		currentWeightField.addMouseListener(new GoalListener("currentWeight", connection, user));
		panel.add(currentWeightLabel);
		panel.add(currentWeightField);

		JLabel goalWeightLabel = new JLabel("Goal Weight");
		JLabel goalWeightField = new JLabel(String.valueOf(user.getWeightGoal()));
		goalWeightField.addMouseListener(new GoalListener("goalWeight", connection, user));
		panel.add(goalWeightLabel);
		panel.add(goalWeightField);

		JLabel currentActivityLabel = new JLabel("Activity Level");
		JLabel currentActivityField = new JLabel(user.getActivityLevel());
		currentActivityField.addMouseListener(new GoalListener("activityLevel", connection, user));
		panel.add(currentActivityLabel);
		panel.add(currentActivityField);

		return panel;
	}

	/**
	 * Creates a new panel for the user's fitness goals
	 * 
	 * @return JPanel panel with the added Swing components
	 */
	public JPanel createFitnessGoals() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 2"));

		JLabel workoutNumberLabel = new JLabel("Workouts per Week");
		JLabel workoutNumberField = new JLabel(String.valueOf(user.getWorkoutPerWeek()));
		workoutNumberField.addMouseListener(new GoalListener("workoutsPerWeek", connection, user));
		panel.add(workoutNumberLabel);
		panel.add(workoutNumberField);

		JLabel workoutMinutesLabel = new JLabel("Minutes per Week");
		JLabel workoutMinutesField = new JLabel(String.valueOf(user.getMinutesPerWeek()));
		workoutMinutesField.addMouseListener(new GoalListener("minutesPerWeek", connection, user));
		panel.add(workoutMinutesLabel);
		panel.add(workoutMinutesField);

		return panel;
	}

	private UserInfo user;
	private FitrackDatabase connection;
	private JPanel cards;
}

class GoalListener implements MouseListener {
	public GoalListener(String goalToChange, FitrackDatabase connection, UserInfo user) {
		this.goalToChange = goalToChange;
		this.user = user;
		this.connection = connection;
	}

	public String goalToChange;
	private UserInfo user;
	private FitrackDatabase connection;

	@Override
	public void mouseClicked(MouseEvent e) {
		String userInput = "";
		int result = 0;
		switch (this.goalToChange) {
		case "currentWeight":
			userInput = JOptionPane.showInputDialog(null, "Change Current Weight");
			result = connection.setUserGoals(user.getUserId(), "weight", Integer.parseInt(userInput), null);
			return;
		case "goalWeight":
			userInput = JOptionPane.showInputDialog(null, "New Goal Weight");
			result = connection.setUserGoals(user.getUserId(), "weight_goal", Integer.parseInt(userInput), null);
			return;
		case "minutesPerWeek":
			userInput = JOptionPane.showInputDialog(null, "New Minutes per Week Goal");
			result = connection.setUserGoals(user.getUserId(), "workout_minutes_per_week", Integer.parseInt(userInput),
					null);
			return;
		case "workoutsPerWeek":
			userInput = JOptionPane.showInputDialog(null, "New Workout per Week Goal");
			result = connection.setUserGoals(user.getUserId(), "workout_per_week", Integer.parseInt(userInput), null);
			return;
		case "activityLevel":
			JComboBox<String> activityField = new JComboBox<>();
			activityField.addItem("");
			activityField.addItem("Little to no exercise");
			activityField.addItem("Moderate exercise");
			activityField.addItem("Active");
			activityField.addItem("Very Active");
			JOptionPane.showMessageDialog(null, activityField);
			result = connection.setUserGoals(user.getUserId(), "activity", 0,
					activityField.getSelectedItem().toString());
			return;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
