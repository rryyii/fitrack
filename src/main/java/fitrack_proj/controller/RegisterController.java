package fitrack_proj.controller;

import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.dao.RegisterDAO;
import fitrack_proj.view.LoginPanel;
import fitrack_proj.view.RegisterPanel;

public class RegisterController {

  public RegisterController(LoginPanel loginPanel, JPanel cards, FitrackDatabase connection,
      PanelController panelController) {
    this.cards = cards;
    this.panelController = panelController;
    this.loginPanel = loginPanel;
    this.model = new RegisterDAO(connection.getConnection());

    registerUser();
  }

  private void registerUser() {
    this.panel = panelController.createRegisterPanel();
    this.loginPanel.getRegisterButton().addActionListener(e -> panelController.showRegisterPanel());
    this.panel.getSubmit().addActionListener(e -> handleRegistration());
  }

  private void handleRegistration() {
    String username = panel.getUserName();
    String password = panel.getPassword();
    String gender = panel.getGender();
    int weight = panel.getWeight();
    int height = panel.getHeight();
    String activity = panel.getActivity();
    int age = panel.getAge();

    if (model.registerUser(username, password, gender, weight, height, activity, age) == -1) {
      JOptionPane.showMessageDialog(null, "Failed to register.");
      ((CardLayout) cards.getLayout()).show(null, "REGISTERPANEL");
    } else {
      JOptionPane.showMessageDialog(null, "Success!");
      ((CardLayout) cards.getLayout()).previous(cards);
    }
  }

  private boolean validateUsername(String username) {
    if (username.length() == 0) {
      return false;
    }
    return true;
  }

  private boolean validateNumbers(int value) {
    return false;
  }



  private RegisterDAO model;
  private RegisterPanel panel;
  private LoginPanel loginPanel;
  private JPanel cards;
  private PanelController panelController;

}
