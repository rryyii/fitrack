package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.PasswordEncryptor;
import net.miginfocom.swing.MigLayout;

public class LoginPanel extends JPanel implements ActionListener {

  /**
   * LoginPanel main constructor creates the login page 
   * @param cards JPanel of the current cards in the CardLayout
   * @param connection Established SQL database connection
   */
  public LoginPanel(JPanel cards, FitrackDatabase connection) {
    super(new MigLayout("wrap 1"));
    this.cards = cards;
    add(createUserInfoPanel(connection));
  }
  
  public JPanel createUserInfoPanel(FitrackDatabase connection) {
    JPanel panel = new JPanel();
    loginField = new JTextField(15);
    passField = new JPasswordField(15);

    loginButton = new JButton("Login");
    CardLayout cardLayout = (CardLayout) cards.getLayout();
    loginButton
        .addActionListener(new LoginListener(loginField, passField, connection, cards, cardLayout));

    registerButton = new JButton("Register");
    registerButton.addActionListener(this);
    
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

  @Override
  public void actionPerformed(ActionEvent e) {
    CardLayout cardLayout = (CardLayout) cards.getLayout();
    cardLayout.show(cards, "REGISTERPANEL");
  }

  private JButton loginButton;
  private JButton registerButton;
  private JTextField loginField;
  private JPasswordField passField;
  private JLabel userLabel;
  private JLabel passLabel;
  private JPanel cards;

}


/**
 * Helper class for listening and performing login verification
 */
class LoginListener implements ActionListener {
  public LoginListener(JTextField loginField, JTextField passField, FitrackDatabase connection,
      JPanel cards, CardLayout cardLayout) {
    this.loginField = loginField;
    this.passField = passField;
    this.connection = connection;
    this.cards = cards;
    this.cardLayout = cardLayout;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        int result;
        if ((result = connection.verifyLogin(loginField.getText(), passField.getText())) == -1) {
          JOptionPane.showMessageDialog(null, "Invalid Username and Password.");
        } else {
          cards.add(new DashPanel(cards, connection, result), "DASHPANEL");
          cardLayout.show(cards, "DASHPANEL");
        }
      }
    });

  }

  private JTextField loginField;
  private CardLayout cardLayout;
  private JPanel cards;
  private JTextField passField;
  private FitrackDatabase connection;

}
