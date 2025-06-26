package fitrack_proj.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import fitrack_proj.controller.PanelController;
import fitrack_proj.controller.ProfileController;
import fitrack_proj.model.User;
import net.miginfocom.swing.MigLayout;

/**
 * ProfilePanel class that creates and displays the user's profile information.
 */
public class ProfilePanel extends JPanel {

	/**
	 * ProfilePanel main constructor
	 */
	public ProfilePanel(User userInfo, JPanel cards, PanelController controller) {
		super(new MigLayout("wrap 1, insets 20"));
		this.user = userInfo;
		;
		this.profileController = controller.getProfileController();
		this.profileController.setConnection(controller.getConnection());
		this.profileController.setPanel(this);
		this.profileController.setUser(userInfo);

		add(createProfile());
		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});
	}

	private JPanel createProfile() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 2"));
		BufferedImage img = null;
		try {
		  img = ImageIO.read(new File("test.jpg"));
		} catch (IOException e) {
		  
		}
		JLabel usernameLabel = new JLabel("Username: ");
		JButton usernameField = new JButton(user.getUsername());
		usernameField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userInput = JOptionPane.showInputDialog(null, "New Username");
				if (userInput != null) {
					profileController.handleUsername(userInput);
				}
			}

		});
		usernameField.setBorderPainted(false);
		panel.add(usernameLabel);
		panel.add(usernameField, "gapbottom 20");

		JLabel genderLabel = new JLabel("Gender: ");
		JButton genderField = new JButton(user.getGender());
		genderField.setBorderPainted(false);
		panel.add(genderLabel);
		panel.add(genderField, "gapbottom 20");
		;

		JLabel heightLabel = new JLabel("Height: ");
		JButton heightField = new JButton(String.valueOf(user.getHeight()));
		heightField.setBorderPainted(false);
		panel.add(heightLabel);
		panel.add(heightField, "gapbottom 20");

		JLabel weightLabel = new JLabel("Weight: ");
		JButton weightField = new JButton(String.valueOf(user.getWeight()));
		weightField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userInput = JOptionPane.showInputDialog(null, "New Weight");
				if (userInput != null) {
					profileController.handleWeight(Integer.parseInt(userInput));
				}
			}

		});
		weightField.setBorderPainted(false);
		panel.add(weightLabel);
		panel.add(weightField, "gapbottom 20");

		JLabel activityLabel = new JLabel("Activity Level: ");
		JButton activityField = new JButton(String.valueOf(user.getActivityLevel()));
		activityField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> activityField = new JComboBox<>();
				activityField.addItem("");
				activityField.addItem("Little to no exercise");
				activityField.addItem("Moderate exercise");
				activityField.addItem("Active");
				activityField.addItem("Very Active");
				JOptionPane.showMessageDialog(null, activityField);
				String selected = activityField.getSelectedItem().toString();
				if (selected != null && !selected.equals("")) {
					profileController.handleActivity(selected);
				}
			}

		});
		activityField.setBorderPainted(false);
		panel.add(activityLabel);
		panel.add(activityField, "gapbottom 20");

		this.activityField = activityField;
		this.weightField = weightField;
		this.genderField = genderField;
		this.usernameField = usernameField;
		this.heightField = heightField;
		return panel;
	}

	public JButton getGenderField() {
		return genderField;
	}

	public JButton getUsernameField() {
		return usernameField;
	}

	public JButton getWeightField() {
		return weightField;
	}

	public JButton getActivityField() {
		return activityField;
	}

	public JButton getHeightField() {
		return heightField;
	}

	private User user;

	private ProfileController profileController;
	private JButton genderField;
	private JButton usernameField;
	private JButton weightField;
	private JButton heightField;
	private JButton activityField;
}
