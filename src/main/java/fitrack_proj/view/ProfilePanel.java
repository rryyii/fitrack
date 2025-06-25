package fitrack_proj.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import net.miginfocom.swing.MigLayout;

/**
 * ProfilePanel class that creates and displays the user's profile information.
 */
public class ProfilePanel extends JPanel {

  /**
   * ProfilePanel main constructor
   */
  public ProfilePanel(User userInfo, JPanel cards, PanelController controller) {
    super(new MigLayout("wrap 1"));
    this.user = userInfo;
    this.cards = cards;


    add(controller.getMainPanel(), "dock west");
    add(createProfile());
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cards);
    frame.pack();
  }

  private JPanel createProfile() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap 2"));
    
    JLabel usernameLabel = new JLabel("Username: ");
    JButton userName = new JButton(user.getUsername());
    userName.setBorderPainted(false);
    panel.add(usernameLabel);
    panel.add(userName);
    

    JLabel genderLabel = new JLabel("Gender: ");
    JButton gender = new JButton(user.getGender());
    gender.setBorderPainted(false);
    panel.add(genderLabel);
    panel.add(gender);
;

    JLabel heightLabel = new JLabel("Height: ");
    JButton heightField = new JButton(String.valueOf(user.getHeight()));
    heightField.setBorderPainted(false);
    panel.add(heightLabel);
    panel.add(heightField);
    
    JLabel weightLabel = new JLabel("Weight: ");
    JButton weightField = new JButton(String.valueOf(user.getWeight()));
    weightField.setBorderPainted(false);
    panel.add(weightLabel);
    panel.add(weightField);    
    
    JLabel activityLabel = new JLabel("Activity Level: ");
    JButton activityField = new JButton(String.valueOf(user.getActivityLevel()));
    activityField.setBorderPainted(false);
    panel.add(activityLabel);
    panel.add(activityField);
    return panel;
  }


  private User user;
  private JPanel cards;
  private FitrackDatabase connection;
  private JTextField genderField;
  private JTextField usernameField;
}
