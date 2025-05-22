package fitrack_proj.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import fitrack_proj.util.FitrackDatabase;

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
    FitrackDatabase connection = new FitrackDatabase();
    JPanel cards = new JPanel(new CardLayout());
    cards.add(new LoginPanel(cards, connection), "LOGINPANEL");
    cards.add(new RegisterPanel(cards, connection), "REGISTERPANEL");
    add(cards);
    
    pack();
    setTitle("Fitrack");
    setMinimumSize(new Dimension(700, 700));
    setMaximumSize(new Dimension(700, 700));
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
