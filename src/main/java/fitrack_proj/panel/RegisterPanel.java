package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import fitrack_proj.util.FitrackDatabase;
import net.miginfocom.swing.MigLayout;

/**
 * RegisterPanel class creates the registration panel for the user to
 * register for an account.
 */
public class RegisterPanel extends JPanel implements ActionListener {

  public RegisterPanel(JPanel cards, FitrackDatabase connection) {
    super(new MigLayout());
    this.connection = connection;
    this.cards = cards;
    usernameLabel = new JLabel("Enter a username: ");
    add(usernameLabel);
    usernameField = new JTextField(15);
    add(usernameField, "wrap");

    passwordLabel = new JLabel("Enter a password: ");
    add(passwordLabel);
    passwordField = new JPasswordField(15);
    add(passwordField, "wrap");

    genderLabel = new JLabel("Enter your sex: ");
    add(genderLabel);

    genderField = new JComboBox<>();
    genderField.addItem("");
    genderField.addItem("Male");
    genderField.addItem("Female");
    add(genderField, "wrap");

    weightLabel = new JLabel("Enter your weight: ");
    add(weightLabel);
    weightField = new JTextField(3);
    add(weightField, "wrap");

    heightLabel = new JLabel("Enter your height: ");
    add(heightLabel);
    heightField = new JTextField(10);
    add(heightField, "wrap");

    activityLabel = new JLabel("Enter your current activity level: ");
    add(activityLabel);

    activityField = new JComboBox<>();
    activityField.addItem("");
    activityField.addItem("Little to no exercise");
    activityField.addItem("Moderate exercise");
    activityField.addItem("Active");
    activityField.addItem("Very Active");
    add(activityField, "wrap");

    ageLabel = new JLabel("Enter your age: ");
    add(ageLabel);
    ageField = new JTextField(3);
    add(ageField, "wrap");

    submitButton = new JButton("Submit");
    submitButton.addActionListener(this);
    add(submitButton);

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
