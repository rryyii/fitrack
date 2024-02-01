package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import fitrack_proj.util.FitrackDatabase;

/**
 * Primary JSwing Class.
 *
 * @author yirw
 *
 */
public class LoginPanel {

	private FitrackDatabase connection;
	private DashPanel dashPanel;

	/**
	 * Creates the main frame and panel for Fitrack.
	 *
	 * @param sql SQL connection to utilize
	 * @return The main JFrame utilized in the application
	 */
	public JFrame createMenu(FitrackDatabase sql) {
		FlatDarkLaf.setup();
		this.connection = sql;
		final JFrame frame = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		final JPanel cardPanel = new JPanel(new CardLayout());
		final JPanel mainScreen = new JPanel();
		dashPanel = new DashPanel(cardPanel, connection);
		final JButton login = new JButton();
		login.setText("Login");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridLayout loginLay = new GridLayout(2, 2);
				loginLay.setHgap(10);
				loginLay.setVgap(10);
				JTextField username = new JTextField(10);
				JTextField password = new JPasswordField(10);
				JPanel userLogin = new JPanel(loginLay);
				userLogin.add(new JLabel("Username:"));
				userLogin.add(username);
				userLogin.add(Box.createHorizontalStrut(15));
				userLogin.add(new JLabel("Password:"));
				userLogin.add(password);
				int result = JOptionPane.showConfirmDialog(null, userLogin, "Please enter your username and password.",
						JOptionPane.OK_CANCEL_OPTION);
				if (username.getText().isEmpty() ||
                    password.getText().isEmpty() && result != JOptionPane.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(login, "Please fill in all fields.");
				} else if (!connection.verifyLogin(username.getText(), password.getText())) {
					JOptionPane.showMessageDialog(login, "Invalid username and password.");
				} else {
					dashPanel.setUser(username.getText(), password.getText());
					dashPanel.showPanel();
					((CardLayout) cardPanel.getLayout()).show(cardPanel, "dash");
				}
			}
		});

		final JButton register = new JButton();
		register.setText("Register");
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridLayout registerLay = new GridLayout(7, 2);
				registerLay.setVgap(10);
				registerLay.setHgap(10);
				JTextField username = new JTextField(15);
				JTextField height = new JTextField(10);
				JTextField password = new JTextField(10);
				JTextField weight = new JTextField(10);
				JTextField age = new JTextField((10));
				JComboBox<String> gender = new JComboBox<>();
				gender.addItem("");
				gender.addItem("Male");
				gender.addItem("Female");
				gender.addItem("Other");
				JComboBox<String> activity = new JComboBox<>();
				activity.addItem("");
				activity.addItem("Little to no exercise");
				activity.addItem("Moderate exercise");
				activity.addItem("Active");
				activity.addItem("Very Active");

				JPanel userRegister = new JPanel();
				userRegister.setLayout(registerLay);
				userRegister.add(new JLabel(" Username:"));
				userRegister.add(username);
				userRegister.add(new JLabel("Password:"));
				userRegister.add(password);
				userRegister.add(new JLabel("Gender:"));
				userRegister.add(gender);
				userRegister.add(new JLabel("Age:"));
				userRegister.add(age);
				userRegister.add(new JLabel("Height (Cm):"));
				userRegister.add(height);
				userRegister.add(new JLabel("Weight (Kg):"));
				userRegister.add(weight);
				userRegister.add(new JLabel("Activity Level:"));
				userRegister.add(activity);

				JOptionPane.showConfirmDialog(null, userRegister, "User Registration", JOptionPane.OK_CANCEL_OPTION);
//				if (connection.registerUser(username.getText(), password.getText(), gender.getSelectedItem().toString(),
//						Integer.parseInt(weight.getText()), Integer.parseInt(height.getText()),
//						activity.getSelectedItem().toString(), Integer.parseInt(age.getText())) == -1) {
//				}
			}
		});

		mainScreen.add(login);
		mainScreen.add(register);

		cardPanel.add(mainScreen, "login");
		cardPanel.add(dashPanel, "dash");

		// Misc
		frame.add(cardPanel);
		frame.setTitle("Fitrack");
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		return frame;
	}

}
