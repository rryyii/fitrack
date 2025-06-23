package fitrack_proj.view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class LoginPanel extends JPanel {

  /**
   * LoginPanel main constructor creates the login page
   * 
   * @param cards JPanel of the current cards in the CardLayout
   * @param connection Established SQL database connection
   */
  public LoginPanel(JPanel cards) {
    super(new MigLayout("wrap 1"));
    this.cards = cards;
    add(createUserInfoPanel());
  }

  public JPanel createUserInfoPanel() {
    JPanel panel = new JPanel();
    loginField = new JTextField(15);
    passField = new JPasswordField(15);

    loginButton = new JButton("Login");

    registerButton = new JButton("Register");

    userLabel = new JLabel("Username: ");
    passLabel = new JLabel("Password: ");

    this.add(userLabel);
    this.add(loginField);
    this.add(passLabel);
    this.add(passField);
    this.add(loginButton);
    this.add(registerButton);
    return panel;
  }


  public JButton getLoginButton() {
    return this.loginButton;
  }

  public JButton getRegisterButton() {
    return this.registerButton;
  }

  public JTextField getLoginField() {
    return this.loginField;
  }

  public JPasswordField getPasswordField() {
    return this.passField;
  }

  private JButton loginButton;
  private JButton registerButton;
  private JTextField loginField;
  private JPasswordField passField;
  private JLabel userLabel;
  private JLabel passLabel;
  private JPanel cards;

}
