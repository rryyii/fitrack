package fitrack_proj.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import fitrack_proj.controller.DashController;
import fitrack_proj.controller.LoginController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.controller.RegisterController;
import fitrack_proj.model.FitrackDatabase;

/**
 * LoginFrame class that creates the main JFrame and adds the base JPanels
 * 
 */
public class FitrackFrame extends JFrame {

  /**
   * LoginFrame Main Constructor
   */
  public FitrackFrame() {
    FlatDarculaLaf.setup();
    JPanel cards = new JPanel(new CardLayout());

    DashController dashController = new DashController();
    PanelController panelController = new PanelController(cards, (CardLayout) cards.getLayout());
    FitrackDatabase connection = new FitrackDatabase();
    LoginPanel loginPanel = new LoginPanel(cards);
    new LoginController(loginPanel, cards, connection, panelController, dashController);
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
