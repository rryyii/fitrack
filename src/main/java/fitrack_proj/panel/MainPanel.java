package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fitrack_proj.util.FitrackDatabase;
import fitrack_proj.util.UserInfo;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
	public MainPanel(JPanel cards, FitrackDatabase connection, int user_id) {
		this.cards = cards;
		this.connection = connection;
		this.user_id = user_id;
		this.userInfo = new UserInfo(user_id, connection);	
		CardLayout layout = (CardLayout) this.cards.getLayout();
		add(createMainPanel(layout));
		
	}

	public JPanel createMainPanel(CardLayout layout) {
		JPanel panel = new JPanel();
		panel.add(createMenuPanel(layout));
		return panel;
	}

	public JPanel createMenuPanel(CardLayout layout) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 1"));
		panel.setBackground(new Color(0, 0, 0));
		JButton home = new JButton("Home");
		home.addActionListener(e -> {
			layout.show(this.cards, "DASHPANEL");
		});
		panel.add(home);
		JButton profile = new JButton("Profile");
		profile.addActionListener(e -> {
			this.cards.add(new ProfilePanel(userInfo, connection, cards), "PROFILEPANEL");
			layout.show(this.cards, "PROFILEPANEL");
		});
		panel.add(profile);
		JButton goals = new JButton("Goals");
		goals.addActionListener(e -> {
			this.cards.add(new GoalPanel(userInfo, connection, cards), "GOALPANEL");
			layout.show(this.cards, "GOALPANEL");
		});
		panel.add(goals);
		JButton logout = new JButton("Logout");
		logout.addActionListener(e -> {
			this.cards.removeAll();
			this.cards.add(new LoginPanel(cards, connection));
			this.cards.repaint();
			this.cards.revalidate();
		});
		panel.add(logout);
		return panel;
	}

	private FitrackDatabase connection;
	private int user_id;
	private JPanel cards;
	private UserInfo userInfo;

}
