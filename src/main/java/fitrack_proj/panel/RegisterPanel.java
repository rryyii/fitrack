package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import fitrack_proj.util.FitrackDatabase;
import net.miginfocom.swing.MigLayout;

/**
 * RegisterPanel class creates the registration panel for the user to register for an account.
 */
public class RegisterPanel extends JPanel implements ActionListener {

  /**
   * RegisterPanel main constructor
   *
   * @param cards Cards to allow for displaying different panels
   * @param connection Current connection to the database
   */
  public RegisterPanel(JPanel cards, FitrackDatabase connection) {
    super(new MigLayout());
    this.connection = connection;
    this.cards = cards;
    add(createRegisterComponents());
  }

  /**
   * Creates the main register panel for the user to input their information
   * 
   * @return JPanel panel of the main registration Swing components
   */
  public JPanel createRegisterComponents() {
    JPanel panel = new JPanel();
    usernameLabel = new JLabel("Enter a username: ");
    panel.add(usernameLabel);
    usernameField = new JTextField(15);
    panel.add(usernameField, "wrap");

    passwordLabel = new JLabel("Enter a password: ");
    panel.add(passwordLabel);
    passwordField = new JPasswordField(15);
    panel.add(passwordField, "wrap");

    genderLabel = new JLabel("Enter your sex: ");
    panel.add(genderLabel);

    genderField = new JComboBox<>();
    genderField.addItem("");
    genderField.addItem("Male");
    genderField.addItem("Female");
    panel.add(genderField, "wrap");

    weightLabel = new JLabel("Enter your weight: ");
    panel.add(weightLabel);
    weightField = new JTextField(3);
    panel.add(weightField, "wrap");

    heightLabel = new JLabel("Enter your height: ");
    panel.add(heightLabel);
    heightField = new JTextField(10);
    panel.add(heightField, "wrap");

    activityLabel = new JLabel("Enter your current activity level: ");
    panel.add(activityLabel);

    activityField = new JComboBox<>();
    activityField.addItem("");
    activityField.addItem("Little to no exercise");
    activityField.addItem("Moderate exercise");
    activityField.addItem("Active");
    activityField.addItem("Very Active");
    panel.add(activityField, "wrap");

    ageLabel = new JLabel("Enter your age: ");
    panel.add(ageLabel);
    ageField = new JTextField(3);
    panel.add(ageField, "wrap");

    submitButton = new JButton("Submit");
    submitButton.addActionListener(this);
    panel.add(submitButton);
    return panel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        if (connection.registerUser(usernameField.getText(), passwordField.getText(),
            Objects.requireNonNull(genderField.getSelectedItem()).toString(),
            Integer.parseInt(weightField.getText()), Integer.parseInt(heightField.getText()),
            Objects.requireNonNull(activityField.getSelectedItem()).toString(),
            Integer.parseInt(ageField.getText())) == -1) {
          JOptionPane.showMessageDialog(null, "Failed to register.");
        } else {
          JOptionPane.showMessageDialog(null, "Success!");
          ((CardLayout) cards.getLayout()).previous(cards);
        }
      }
    });
  }

  private JLabel usernameLabel;
  private JLabel passwordLabel;
  private JLabel genderLabel;
  private JLabel weightLabel;
  private JLabel heightLabel;
  private JLabel activityLabel;
  private JLabel ageLabel;
  private JTextField usernameField;
  private JTextField passwordField;
  private JComboBox<String> genderField;
  private JTextField weightField;
  private JTextField heightField;
  private JComboBox<String> activityField;
  private JTextField ageField;
  private JButton submitButton;
  private FitrackDatabase connection;
  private JPanel cards;
}
