package fitrack_proj.app;

import fitrack_proj.panel.LoginPanel;
import fitrack_proj.util.FitrackDatabase;

import javax.swing.*;

/**
 * Primary Fittrack Functionality Class.
 *
 * @author yirw
 */
public class Fitrack {

	/**
	 * Main Method.
	 *
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		Fitrack instance = new Fitrack();
		instance.createInstance();
	}

	/**
	 * Creates an instance of Fitrack application.
	 */
	public void createInstance() {
		LoginPanel menu = new LoginPanel();
		JFrame gui = menu.createMenu(new FitrackDatabase());
	}
}
