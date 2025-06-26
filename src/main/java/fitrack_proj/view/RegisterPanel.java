package fitrack_proj.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

/**
 * RegisterPanel class creates the registration panel for the user to register for an account.
 */
public class RegisterPanel extends JPanel {

  /**
   * RegisterPanel main constructor
   *
   * @param cards Cards to allow for displaying different panels
   * @param connection Current connection to the database
   */
  public RegisterPanel(JPanel cards) {
    super(new MigLayout("wrap", "align center"));
    add(createHead());
    add(createRegisterComponents());
    SwingUtilities.invokeLater(() -> {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
      if (frame != null) {
        frame.pack();
      }
    });
  }

  private JPanel createHead() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    panel.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.setBackground(new Color(25, 5, 22));
    JLabel registerLabel = new JLabel(
        "Sign up to Fitrack to start tracking your daily exercise and nutrition for free!");
    registerLabel.putClientProperty("FlatLaf.styleClass", "h4");
    panel.add(registerLabel);
    return panel;
  }

  /**
   * Creates the main register panel for the user to input their information
   * 
   * @return JPanel panel of the main registration Swing components
   */
  private JPanel createRegisterComponents() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap 1, insets 20"));
    usernameLabel = new JLabel("Username");
    panel.add(usernameLabel);
    usernameField = new JTextField(15);
    usernameField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(usernameField, "wrap");
    JLabel usernameHelp = new JLabel(
        "Username must be at least 8 characters and only contain alpha-numeric symbols.");
    usernameHelp.putClientProperty("FlatLaf.styleClass", "mini");
    panel.add(usernameHelp);

    passwordLabel = new JLabel("Password: ");
    panel.add(passwordLabel);
    passwordField = new JPasswordField(15);
    passwordField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(passwordField, "wrap");
    JLabel passwordHelp = new JLabel("Password must be at least 15 characters.");
    passwordHelp.putClientProperty("FlatLaf.styleClass", "mini");
    panel.add(passwordHelp);

    genderLabel = new JLabel("Sex: ");
    panel.add(genderLabel);

    genderField = new JComboBox<>();
    genderField.addItem("");
    genderField.addItem("Male");
    genderField.addItem("Female");
    genderField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(genderField, "wrap");

    weightLabel = new JLabel("Weight: ");
    panel.add(weightLabel);
    weightField = new JTextField(3);
    weightField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(weightField, "wrap");

    heightLabel = new JLabel("Height: ");
    panel.add(heightLabel);
    heightField = new JTextField(10);
    heightField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(heightField, "wrap");

    activityLabel = new JLabel("Activity level: ");
    panel.add(activityLabel);

    activityField = new JComboBox<>();
    activityField.addItem("");
    activityField.addItem("Little to no exercise");
    activityField.addItem("Moderate exercise");
    activityField.addItem("Active");
    activityField.addItem("Very Active");
    activityField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(activityField, "wrap");

    ageLabel = new JLabel("Age: ");
    panel.add(ageLabel);
    ageField = new JTextField(3);
    ageField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(ageField, "wrap");

    submitButton = new JButton("Submit");
    submitButton.putClientProperty("JButton.buttonType", "borderless");
    submitButton.putClientProperty(FlatClientProperties.STYLE, "background: #8343bc;");
    panel.add(submitButton, "gaptop 30");
    return panel;
  }

  public String getUserName() {
    return this.usernameField.getText();
  }

  public String getPassword() {
    return this.passwordField.getText();
  }

  public String getGender() {
    return this.genderField.getSelectedItem().toString();
  }

  public int getWeight() {
    return Integer.parseInt(this.weightField.getText());
  }

  public int getUserHeight() {
    return Integer.parseInt(this.heightField.getText());
  }

  public int getAge() {
    return Integer.parseInt(this.ageField.getText());
  }

  public String getActivity() {
    return this.activityField.getSelectedItem().toString();
  }

  public JButton getSubmit() {
    return this.submitButton;
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
}
