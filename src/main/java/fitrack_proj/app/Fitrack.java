package fitrack_proj.app;

import javax.swing.JFrame;

import fitrack_proj.panel.LoginPanel;
import fitrack_proj.util.FitrackDatabase;

/**
 * Primary Fittrack Functionality Class.
 *
 * @author yirw
 *
 */
public class Fitrack {
	
	/**
	 * Creates an instance of Fitrack application.
	 */
	public void createInstance() {
		LoginPanel menu = new LoginPanel();
		JFrame gui = menu.createMenu(new FitrackDatabase());
	}
	
	/**
	 * Main Method.
	 *
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		Fitrack instance = new Fitrack();
		instance.createInstance();
	}
}
