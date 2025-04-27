package fitrack_proj.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 * GoalPanel class creates the main panel for a user's fitness/nutrition goals
 */
public class GoalPanel extends JPanel {
  
  /**
   * GoalPanel Main Constructor
   */
  public GoalPanel() {
    super(new MigLayout("wrap 1"));
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
    JTextField currentWeightField = new JTextField();
    panel.add(currentWeightLabel);
    panel.add(currentWeightField);
    JLabel goalWeightLabel = new JLabel("Goal Weight");
    JTextField goalWeightField = new JTextField();
    panel.add(goalWeightLabel);
    panel.add(goalWeightField);
    JLabel currentActivityLabel = new JLabel("Activity Level");
    JTextField currentActivityField = new JTextField();
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
    JTextField workoutNumberField = new JTextField();
    panel.add(workoutNumberLabel);
    panel.add(workoutNumberField);
    JLabel workoutMinutesLabel = new JLabel("Minutes per Week");
    JTextField workoutMinutesField = new JTextField();
    panel.add(workoutMinutesLabel);
    panel.add(workoutMinutesField);
    return panel;
  }
}
