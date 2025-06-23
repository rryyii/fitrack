package fitrack_proj.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
  public MainPanel(JPanel cards, PanelController controller, User user) {
    this.cards = cards;
    this.userInfo = user;
    this.layout = (CardLayout) this.cards.getLayout();
    this.controller = controller;
    add(createMainPanel(), "dock west");
  }

  public JPanel createMainPanel() {
    JPanel panel = new JPanel();
    panel.add(createMenuPanel());
    return panel;
  }

  public JPanel createMenuPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap 1"));
    panel.setBackground(new Color(0, 0, 0));
    JButton home = new JButton("Home");
    home.addActionListener(e -> {
      this.controller.showDashPanel();
    });
    panel.add(home);
    JButton profile = new JButton("Profile");
    profile.addActionListener(e -> {
      controller.createProfilelPanel(userInfo);
      controller.showProfilePanel();
    });
    panel.add(profile);
    JButton goals = new JButton("Goals");
    goals.addActionListener(e -> {
      controller.createGoalPanel(userInfo);
      controller.showGoalPanel();
    });
    panel.add(goals);
    JButton logout = new JButton("Logout");
    logout.addActionListener(e -> {
    });
    panel.add(logout);
    return panel;
  }

  private FitrackDatabase connection;
  private JPanel cards;
  private CardLayout layout;
  private User userInfo;
  private PanelController controller;

}
