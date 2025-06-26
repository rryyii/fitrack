package fitrack_proj.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class LoginPanel extends JPanel {

  /**
   * LoginPanel main constructor creates the login page
   * 
   * @param cards JPanel of the current cards in the CardLayout
   * @param connection Established SQL database connection
   */
  public LoginPanel(JPanel cards) {
    super(new MigLayout("wrap 1, align center, insets 20"));
    add(createUserInfoPanel());
    SwingUtilities.invokeLater(() -> {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
      if (frame != null) {
        frame.pack();
      }
    });
  }

  private JPanel createUserInfoPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap 1", "align center"));
    panel.setBackground(new Color(25, 5, 22));
    panel.putClientProperty(FlatClientProperties.STYLE, "arc:8");
    JLabel welcomeLabel = new JLabel("Welcome to Fitrack");
    welcomeLabel.putClientProperty("FlatLaf.styleClass", "h1");

    loginField = new JTextField(15);
    loginField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    passField = new JPasswordField(15);
    passField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");

    loginButton = new JButton("Login");
    loginButton.putClientProperty("JButton.buttonType", "borderless");
    loginButton.putClientProperty(FlatClientProperties.STYLE, "font:bold; background: #8343bc");

    registerButton = new JButton("Register");
    registerButton.putClientProperty("JButton.buttonType", "borderless");   
    registerButton.putClientProperty(FlatClientProperties.STYLE, "font:bold; background: #8343bc");

    userLabel = new JLabel("Username: ");
    passLabel = new JLabel("Password: ");

    panel.add(welcomeLabel, "gapbottom 30");
    panel.add(userLabel);
    panel.add(loginField);
    panel.add(passLabel);
    panel.add(passField, "gapbottom 30");
    panel.add(loginButton, "align center");
    panel.add(registerButton, "align center");
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

}
