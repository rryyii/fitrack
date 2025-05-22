package fitrack_proj.panel;

import javax.swing.JPanel;
import fitrack_proj.util.UserInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
  public GoalPanel(UserInfo user) {
    super(new MigLayout("wrap 1"));
    this.user = user;
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
    currentWeightField.addMouseListener(new GoalListener("currentWeight"));
    panel.add(currentWeightLabel);
    panel.add(currentWeightField);
    
    JLabel goalWeightLabel = new JLabel("Goal Weight");
    JLabel goalWeightField = new JLabel("100");
    goalWeightField.addMouseListener(new GoalListener("goalWeight"));
    panel.add(goalWeightLabel);
    panel.add(goalWeightField);
    
    JLabel currentActivityLabel = new JLabel("Activity Level");
    JLabel currentActivityField = new JLabel(user.getActivityLevel());
    currentActivityField.addMouseListener(new GoalListener("activityLevel"));
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
    JLabel workoutNumberField = new JLabel("6");
    workoutNumberField.addMouseListener(new GoalListener("workoutsPerWeek"));
    panel.add(workoutNumberLabel);
    panel.add(workoutNumberField);
    
    JLabel workoutMinutesLabel = new JLabel("Minutes per Week");
    JLabel workoutMinutesField = new JLabel("600");
   workoutMinutesField.addMouseListener(new GoalListener("minutesPerWeek"));
    panel.add(workoutMinutesLabel);
    panel.add(workoutMinutesField);
    
    return panel;
  }
  
  private UserInfo user;
}

class GoalListener implements MouseListener {
  public GoalListener(String goalToChange) {
    this.goalToChange = goalToChange;
  }

  public String goalToChange;

  @Override
  public void mouseClicked(MouseEvent e) {
    String userInput = "";
    switch (this.goalToChange) {
      case "currentWeight":
        userInput = JOptionPane.showInputDialog(null, "Change Current Weight");
        return;
      case "goalWeight":
        userInput = JOptionPane.showInputDialog(null, "New Goal Weight");
        return;
      case "minutesPerWeek":
        userInput = JOptionPane.showInputDialog(null, "New Minutes per Week Goal");
        return;
      case "workoutsPerWeek":
        userInput = JOptionPane.showInputDialog(null, "New Workout per Week Goal");
        return;
      case "activityLevel": 
        userInput = JOptionPane.showInputDialog(null, "Change Current Activity Level");
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
