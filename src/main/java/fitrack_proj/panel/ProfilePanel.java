package fitrack_proj.panel;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fitrack_proj.util.UserInfo;
import fitrack_proj.util.FitrackDatabase;
import net.miginfocom.swing.MigLayout;

/**
 * ProfilePanel class that creates and displays the user's profile information.
 */
public class ProfilePanel extends JPanel {
  
  /**
   * ProfilePanel main constructor 
   */
  public ProfilePanel(UserInfo userInfo, FitrackDatabase connection, JPanel cards) {
    super(new MigLayout("wrap 2"));
    this.user = userInfo;
    this.connection = connection;
    this.cards = cards;
    JLabel usernameLabel = new JLabel("Current Username: ");
    JTextField userName = new JTextField(user.getUsername());

    this.add(usernameLabel);
    this.add(userName);
    
    JLabel genderLabel = new JLabel("Current Gender: ");
    JTextField gender = new JTextField(user.getGender());
    this.add(genderLabel);
    this.add(gender);
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
  
  private UserInfo user;
  private JPanel cards;
  private FitrackDatabase connection;
}
