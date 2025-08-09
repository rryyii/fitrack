package fitrack_proj.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.view.LoginPanel;

public class LoginController {

	public LoginController(LoginPanel panel, JPanel cards, FitrackDatabase connection, PanelController panelController,
			DashController dashController, JFrame frame) {
		this.connection = connection;
		this.model = new LoginDAO(connection.getConnection());
		this.panel = panel;
		this.panelController = panelController;
		this.frame = frame;
		this.dashController = dashController;
		userLogin();
	}

	private void userLogin() {
		panel.getLoginButton().addActionListener(e -> handleLogin());
	}

	public void handleLogin() {
		int result;
		String username = panel.getLoginField().getText();
		String password = panel.getPasswordField().getText();
		if (username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill out both fields");
		} else if ((result = model.verifyLogin(username, password)) == -1) {
			JOptionPane.showMessageDialog(null, "Invalid Username and Password.");
		} else {
			panelController.createDashPanel(result, connection, model, dashController);
			panelController.createRootPanel();
			frame.add(panelController.getRootPanel());
			panelController.showDashPanel();
			panelController.createNavigationPanel();
			panelController.setRootPanel();
		}
	}

	private JFrame frame;
	private LoginDAO model;
	private FitrackDatabase connection;
	private LoginPanel panel;
	private PanelController panelController;
	private DashController dashController;

}