package fitrack_proj.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

/**
 * RegisterPanel class creates the registration panel for the user to register
 * for an account.
 */
public class RegisterPanel extends JPanel {

	/**
	 * RegisterPanel main constructor
	 *
	 * @param cards      Cards to allow for displaying different panels
	 * @param connection Current connection to the database
	 */
	public RegisterPanel(JPanel cards) {
		super(new MigLayout());
		add(createRegisterComponents());
		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});
	}

	/**
	 * Creates the main register panel for the user to input their information
	 * 
	 * @return JPanel panel of the main registration Swing components
	 */
	private JPanel createRegisterComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 1"));
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
		panel.add(submitButton);
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
