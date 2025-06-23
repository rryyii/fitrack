package fitrack_proj.view;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
  public ProfilePanel(User userInfo,  JPanel cards, PanelController controller) {
    super(new MigLayout("wrap 2"));
    this.user = userInfo;
    this.cards = cards;
    
    createProfile();
    add(controller.getMainPanel(), "dock west");
  }
  
  private void createProfile() {
    JLabel usernameLabel = new JLabel("Current Username: ");
    JTextField userName = new JTextField(user.getUsername());

    this.add(usernameLabel);
    this.add(userName);

    JLabel genderLabel = new JLabel("Current Gender: ");
    JTextField gender = new JTextField(user.getGender());
    this.add(genderLabel);
    this.add(gender);
    
    JLabel activityLabel = new JLabel("Current Activity Level: ");
    JTextField activityField = new JTextField();
    this.add(activityLabel);
    this.add(activityField);
    
    JLabel heightLabel = new JLabel("Current Height: ");
    JTextField heightField = new JTextField();
    this.add(heightLabel);
    this.add(heightField);
  }


  private User user;
  private JPanel cards;
  private FitrackDatabase connection;
  private JTextField genderField;
  private JTextField usernameField;
}
