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
    panelController.setRegisterController(this);
    registerUser();
  }

  public void registerUser() {
    this.panel = panelController.createRegisterPanel();
    this.loginPanel.getRegisterButton().addActionListener(e -> panelController.showRegisterPanel());
    this.panel.getSubmit().addActionListener(e -> handleRegistration());
  }

  private void handleRegistration() {
    String username = panel.getUserName();
    String password = panel.getPassword();
    String gender = panel.getGender();
    int weight = (panel.getWeight() != -1) ? panel.getWeight() : -1;
    int height = (panel.getUserHeight() != -1) ? panel.getUserHeight() : -1;
    String activity = panel.getActivity();
    int age = (panel.getAge() != -1) ? panel.getAge() : -1;

    boolean validate = validateInput(username, password, gender, weight, height, activity, age);

    if (!validate) {
      JOptionPane.showMessageDialog(null, "Invalid Inputs.");
      panelController.showRegisterPanel();
    } else {
      if (model.registerUser(username, password, gender, weight, height, activity, age) == 1) {
        JOptionPane.showMessageDialog(null, "Success!");
        ((CardLayout) cards.getLayout()).previous(cards);
      }
    }
  }

  private boolean validateInput(String username, String password, String gender, int weight,
      int height, String activity, int age) {
    boolean result = true;
    if (username.isEmpty() || username.length() < 8) {
      System.out.println("username");
      result = false;
    }
    if (password.length() < 18) {
      result = false;
      System.out.println("pass");
    }
    if (gender.isEmpty() || gender.isBlank()) {
      System.out.println("gender");
      result = false;
    }
    if (weight <= 0 || height <= 0) {
      System.out.println("height or weight");
      result = false;
    }
    if (activity.isEmpty() || activity.isBlank()) {
      System.out.println("activity");
      result = false;
    }
    if (age <= 0 || age > 120) {
      result = false;
      System.out.println("age");
    }
    return result;
  }

  private RegisterDAO model;
  private RegisterPanel panel;
  private LoginPanel loginPanel;
  private JPanel cards;
  private PanelController panelController;

}
