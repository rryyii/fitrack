package fitrack_proj.controller;

import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.view.DashPanel;
import fitrack_proj.view.LoginPanel;

public class LoginController {

  public LoginController(LoginPanel panel, JPanel cards, FitrackDatabase connection,
      PanelController panelController, DashController dashController) {
    this.connection = connection;
    this.model = new LoginDAO(connection.getConnection());
    this.cards = cards;
    this.panel = panel;
    this.panelController = panelController;
    this.dashController = dashController;
    userLogin();
  }

  private void userLogin() {
    panel.getLoginButton().addActionListener(e -> handleLogin());
  }

  public void handleLogin() {
    int result;
    String username = panel.getLoginField().getText();
    String password = panel.getPasswordField().getText();
    if ((result = model.verifyLogin(username, password)) == -1) {
      JOptionPane.showMessageDialog(null, "Invalid Username and Password.");
    } else {
      panelController.createDashPanel(result, connection, model, dashController);
      panelController.showDashPanel();
    }
  }

  private LoginDAO model;
  private FitrackDatabase connection;
  private JPanel cards;
  private LoginPanel panel;
  private PanelController panelController;
  private DashController dashController;

}
