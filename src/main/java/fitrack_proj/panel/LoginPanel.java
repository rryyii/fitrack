package fitrack_proj.panel;

import com.formdev.flatlaf.FlatDarkLaf;
import fitrack_proj.util.FitrackDatabase;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Main panel that contains login and register functions.
 *
 * @author yirw
 */
public class LoginPanel {

	private FitrackDatabase connection;
	private DashPanel dashPanel;

	/**
	 * Creates the main frame and panel for Fitrack.
	 *
	 * @param sql Established SQL database connection
	 * @return Returns a JFrame of the created LoginPanel
	 */
	public JFrame createMenu(FitrackDatabase sql) {
		FlatDarkLaf.setup();
		this.connection = sql;
		final JFrame frame = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		final JPanel cardPanel = new JPanel(new CardLayout());
		final JPanel mainPanel = new JPanel();
		dashPanel = new DashPanel(cardPanel, connection, frame);
		final JButton loginButton = new JButton();
		loginButton.setText("Login");
		// Creates the ActionListener for the login button
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						dashPanel.removeAll();
						GridLayout loginLayout = new GridLayout(2, 2);
						loginLayout.setHgap(10);
						loginLayout.setVgap(10);
						JTextField usernameField = new JTextField(10);
						JTextField passwordField = new JPasswordField(10);
						JPanel userLogin = new JPanel(loginLayout);
						userLogin.add(new JLabel("Username:"));
						userLogin.add(usernameField);
						userLogin.add(Box.createHorizontalStrut(15));
						userLogin.add(new JLabel("Password:"));
						userLogin.add(passwordField);
						int result = JOptionPane.showConfirmDialog(null, userLogin,
								"Please enter your Username and Password.", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.CANCEL_OPTION) {
						} else if ((usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
							JOptionPane.showMessageDialog(loginButton, "Please fill in all fields.");
						} else if (!connection.verifyLogin(usernameField.getText(), passwordField.getText())) {
							JOptionPane.showMessageDialog(loginButton, "Invalid Username and Password.");
						} else {
							dashPanel.setUser(usernameField.getText(), passwordField.getText());
							dashPanel.showPanel();
							((CardLayout) cardPanel.getLayout()).show(cardPanel, "dash");
							frame.pack();
						}
					}
				});
			}
		});

		final JButton registerButton = new JButton();
		registerButton.setText("Register");
		registerButton.addActionListener(e -> {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					GridLayout registerLayout = new GridLayout(7, 2);
					registerLayout.setVgap(10);
					registerLayout.setHgap(10);
					JTextField usernameField = new JTextField(15);
					JTextField heightField = new JTextField(10);
					JTextField passwordField = new JTextField(10);
					JTextField weight = new JTextField(10);
					JTextField age = new JTextField((10));
					JComboBox<String> genderBox = new JComboBox<>();
					genderBox.addItem("");
					genderBox.addItem("Male");
					genderBox.addItem("Female");
					genderBox.addItem("Other");
					// Choose Activity level
					JComboBox<String> activityBox = new JComboBox<>();
					activityBox.addItem("");
					activityBox.addItem("Little to no exercise");
					activityBox.addItem("Moderate exercise");
					activityBox.addItem("Active");
					activityBox.addItem("Very Active");
					// Registration details
					JPanel registerPanel = new JPanel();
					registerPanel.setLayout(registerLayout);
					registerPanel.add(new JLabel(" Username:"));
					registerPanel.add(usernameField);
					registerPanel.add(new JLabel("Password:"));
					registerPanel.add(passwordField);
					registerPanel.add(new JLabel("Gender:"));
					registerPanel.add(genderBox);
					registerPanel.add(new JLabel("Age:"));
					registerPanel.add(age);
					registerPanel.add(new JLabel("Height (Inches):"));
					registerPanel.add(heightField);
					registerPanel.add(new JLabel("Weight (Lbs):"));
					registerPanel.add(weight);
					registerPanel.add(new JLabel("Activity Level:"));
					registerPanel.add(activityBox);

					int result = JOptionPane.showConfirmDialog(null, registerPanel, "User Registration",
							JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.CANCEL_OPTION) {
					} else if (connection.registerUser(usernameField.getText(), passwordField.getText(),
							Objects.requireNonNull(genderBox.getSelectedItem()).toString(),
							Integer.parseInt(weight.getText()), Integer.parseInt(heightField.getText()),
							Objects.requireNonNull(activityBox.getSelectedItem()).toString(),
							Integer.parseInt(age.getText())) == -1) {
						System.out.println("Failed to register user");
					}
				}
			});

		});

		mainPanel.add(loginButton);
		mainPanel.add(registerButton);

		cardPanel.add(mainPanel, "login");
		cardPanel.add(dashPanel, "dash");
		
		frame.add(cardPanel);
		// Adjust frame settings
		frame.setTitle("Fitrack");
		frame.setMinimumSize(new Dimension(250, 250));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		return frame;
	}

}
