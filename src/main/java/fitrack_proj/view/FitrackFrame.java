package fitrack_proj.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatDarculaLaf;

import fitrack_proj.controller.DashController;
import fitrack_proj.controller.LoginController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.controller.RegisterController;
import fitrack_proj.model.FitrackDatabase;

/**
 * 
 * LoginFrame class that creates the main JFrame and adds the base JPanels
 **/
public class FitrackFrame extends JFrame {

	public FitrackFrame() {
		FlatDarculaLaf.setup();
		JPanel cards = new JPanel(new CardLayout());
		DashController dashController = new DashController();
		FitrackDatabase connection = new FitrackDatabase();
		PanelController panelController = new PanelController(cards, (CardLayout) cards.getLayout(), connection);
		LoginPanel loginPanel = new LoginPanel(cards);

		new RootPanel();
		new LoginController(loginPanel, cards, connection, panelController, dashController, this);
		cards.add(loginPanel, "LOGINPANEL");

		new RegisterController(loginPanel, cards, connection, panelController);

		add(cards);
		pack();
		setTitle("Fitrack");
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}