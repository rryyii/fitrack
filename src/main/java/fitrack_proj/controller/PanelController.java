package fitrack_proj.controller;

import java.awt.CardLayout;
import javax.swing.JPanel;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.view.DashPanel;
import fitrack_proj.view.GoalPanel;
import fitrack_proj.view.MainPanel;
import fitrack_proj.view.ProfilePanel;
import fitrack_proj.view.RegisterPanel;

public class PanelController {

  public PanelController(JPanel cards, CardLayout layout) {
    this.cards = cards;
    this.layout = layout;
  }

  public MainPanel getMainPanel() {
    return this.mainPanel;
  }

  public void createMainPanel() {
    this.mainPanel = new MainPanel(this.cards, this, user);
  }


  public void showRegisterPanel() {
    if (registerPanel == null) {
      createRegisterPanel();
    } else {
      layout.show(cards, "REGISTERPANEL");
    }
  }

  public RegisterPanel createRegisterPanel() {
    this.registerPanel = new RegisterPanel(cards);
    this.cards.add(registerPanel, "REGISTERPANEL");
    return this.registerPanel;
  }

  public void showDashPanel() {
    if (dashPanel == null) {
      System.out.println("No currently initialized DashPanel.");
    } else {
      layout.show(cards, "DASHPANEL");
    }
  }

  public void createDashPanel(int user_id, FitrackDatabase connection, LoginDAO loginModel,
      DashController dashController) {
    this.dashPanel = new DashPanel(cards, connection, loginModel, user_id, this, dashController);
    this.loginDAO = loginModel;
    this.dashController = dashController;
    this.cards.add(dashPanel, "DASHPANEL");
  }
  
  public void showGoalPanel() {
    if (goalPanel == null) {
      System.out.println("No currently initialized GoalPanel.");
    } else {
      layout.show(cards, "GOALPANEL");
    }
  }
  
  public void createGoalPanel(User user) {
    this.goalPanel = new GoalPanel(user, cards, this);
    this.cards.add(goalPanel, "GOALPANEL");
  }
  
  public void showProfilePanel() {
    if (profilePanel == null) {
      System.out.println("No currently initialized ProfilePanel.");
    } else {
      layout.show(cards, "PROFILEPANEL");
    }
  }
  
  public void createProfilelPanel(User user) {
    this.profilePanel = new ProfilePanel(user, cards, this);
    this.cards.add(profilePanel, "PROFILEPANEL");
  }
  
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public LoginDAO getLoginDAO() {
    return this.loginDAO;
  }
  
  public DashController getDashController() {
    return this.dashController;
  }

  private LoginDAO loginDAO;
  private DashController dashController;
  private User user;
  private JPanel cards;
  private CardLayout layout;
  private ProfilePanel profilePanel;
  private RegisterPanel registerPanel;
  private MainPanel mainPanel;
  private DashPanel dashPanel;
  private GoalPanel goalPanel;

}
