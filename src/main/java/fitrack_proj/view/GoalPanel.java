package fitrack_proj.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import fitrack_proj.controller.GoalController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import net.miginfocom.swing.MigLayout;

/**
 * GoalPanel class creates the main panel for a user's fitness/nutrition goals
 */
public class GoalPanel extends JPanel {

	/**
	 * GoalPanel Main Constructor
	 */
	public GoalPanel(User user, JPanel cards, PanelController controller) {
		super(new MigLayout("wrap 1, insets 20"));
		this.user = user;
		this.cards = cards;
		this.goalController = controller.getGoalController();
		this.goalController.setConnection(controller.getConnection());
		this.goalController.setPanel(this);

		add(createGoalList());

		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});

	}

	/**
	 * Creates a new panel for the user's goals list
	 * 
	 * @return JPanel panel with the added Swing components
	 */
	private JPanel createGoalList() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 2"));

		JLabel goalWeightLabel = new JLabel("Goal Weight");
		JButton goalWeightField = new JButton(String.valueOf(user.getWeightGoal()));
		goalWeightField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userInput = JOptionPane.showInputDialog(null, "New Goal Weight");
				if (userInput != null) {
					goalController.handleGoal(userInput, user);
				}
			}

		});
		goalWeightField.setBorderPainted(false);
		panel.add(goalWeightLabel);
		panel.add(goalWeightField, "gapbottom 20");

		JLabel workoutNumberLabel = new JLabel("Workouts per Week");
		JButton workoutNumberField = new JButton(String.valueOf(user.getWorkoutPerWeek()));
		workoutNumberField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userInput = JOptionPane.showInputDialog(null, "New Workout per Week Goal");
				if (userInput != null) {
					goalController.handleNumber(userInput, user);
				}
			}

		});
		workoutNumberField.setBorderPainted(false);
		panel.add(workoutNumberLabel);
		panel.add(workoutNumberField, "gapbottom 20");

		JLabel workoutMinutesLabel = new JLabel("Minutes per Week");
		JButton workoutMinutesField = new JButton(String.valueOf(user.getMinutesPerWeek()));
		workoutMinutesField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userInput = JOptionPane.showInputDialog(null, "New Minutes per Week Goal");
				if (userInput != null) {
					goalController.handleMinutes(userInput, user);
				}
			}

		});
		workoutMinutesField.setBorderPainted(false);
		panel.add(workoutMinutesLabel);
		panel.add(workoutMinutesField, "gapbottom 20");

		this.workoutNumberField = workoutNumberField;
		this.workoutMinutesField = workoutMinutesField;

		this.goalWeightField = goalWeightField;

		return panel;
	}

	public JButton getCurrentActivityField() {
		return currentActivityField;
	}

	public JButton getCurrentWeightField() {
		return currentWeightField;
	}

	public JButton getGoalWeightField() {
		return goalWeightField;
	}

	public JButton getWorkoutNumberField() {
		return workoutNumberField;
	}

	public JButton getWorkoutMinutesField() {
		return workoutMinutesField;
	}

	private User user;
	private FitrackDatabase connection;
	private JPanel cards;
	private GoalController goalController;

	private JButton currentActivityField;
	private JButton currentWeightField;
	private JButton goalWeightField;
	private JButton workoutNumberField;
	private JButton workoutMinutesField;
}
