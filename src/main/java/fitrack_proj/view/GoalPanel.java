package fitrack_proj.view;

import javax.swing.JPanel;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;

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
  public GoalPanel(User user, JPanel cards, PanelController controller) {
    super(new MigLayout("wrap 1"));
    this.user = user;
    this.cards = cards;
    
    
    add(controller.getMainPanel(), "dock west");
    add(createGoalList());
    add(createFitnessGoals());
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
    // currentWeightField.addMouseListener(new GoalListener("currentWeight", connection, user));
    panel.add(currentWeightLabel);
    panel.add(currentWeightField);

    JLabel goalWeightLabel = new JLabel("Goal Weight");
    JLabel goalWeightField = new JLabel(String.valueOf(user.getWeightGoal()));
    // goalWeightField.addMouseListener(new GoalListener("goalWeight", connection, user));
    panel.add(goalWeightLabel);
    panel.add(goalWeightField);

    JLabel currentActivityLabel = new JLabel("Activity Level");
    JLabel currentActivityField = new JLabel(user.getActivityLevel());
    // currentActivityField.addMouseListener(new GoalListener("activityLevel", connection, user));
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
    // workoutNumberField.addMouseListener(new GoalListener("workoutsPerWeek", connection, user));
    panel.add(workoutNumberLabel);
    panel.add(workoutNumberField);

    JLabel workoutMinutesLabel = new JLabel("Minutes per Week");
    JLabel workoutMinutesField = new JLabel(String.valueOf(user.getMinutesPerWeek()));
    // workoutMinutesField.addMouseListener(new GoalListener("minutesPerWeek", connection, user));
    panel.add(workoutMinutesLabel);
    panel.add(workoutMinutesField);

    return panel;
  }

  private User user;
  private FitrackDatabase connection;
  private JPanel cards;
}
