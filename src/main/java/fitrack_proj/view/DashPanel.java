package fitrack_proj.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatClientProperties;

import fitrack_proj.controller.DashController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.LoginDAO;
import net.miginfocom.swing.MigLayout;

/**
 * DashPanel class creates the dashboard panel for the user to view their
 * exercise and nutrition history.
 */
public class DashPanel extends JPanel {

	/**
	 * DashPanel main constructor creates the dashboard panel
	 * 
	 * @param cards      JPanel of the current cards in the CardLayout
	 * @param connection Established SQL database connection
	 * @param user_id    Currently logged in user's id
	 */
	public DashPanel(JPanel cards, FitrackDatabase connection, LoginDAO loginModel, int user_id,
			PanelController panelController, DashController dashController) {
		super(new MigLayout("align center"));
		userInfo = new User(user_id, loginModel);
		panelController.setUser(userInfo);
		add(createInfoPanel(), "wrap");
		dashController.setController(this, connection);

		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});

	}

	private JPanel createInfoPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(84, 89, 92));
		panel.putClientProperty(FlatClientProperties.STYLE, "arc: 8;");
		panel.setLayout(new MigLayout("insets 20"));
		JTextField username = new JTextField("Welcome back " + userInfo.getUsername());
		username.setEditable(false);
		username.setFocusable(false);
		panel.add(username, "wrap");
		JTextField weight = new JTextField("Weight: " + userInfo.getWeight());
		weight.setEditable(false);
		weight.setFocusable(false);

		panel.add(weight, "wrap");
		JTextField height = new JTextField("Height: " + userInfo.getHeight());
		height.setFocusable(false);
		height.setEditable(false);
		panel.add(height, "wrap");
		this.username = username;
		this.weight = weight;
		this.height = height;
		return panel;
	}

	public JTextField getUsername() {
		return username;
	}

	public JTextField getUserHeight() {
		return height;
	}

	public JTextField getWeight() {
		return weight;
	}

	private User userInfo;
	private JTextField username;
	private JTextField height;
	private JTextField weight;

}
