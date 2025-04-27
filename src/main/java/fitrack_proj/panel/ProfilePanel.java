package fitrack_proj.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 * ProfilePanel class that creates and displays the user's profile information.
 */
public class ProfilePanel extends JPanel {
  
  /**
   * ProfilePanel main constructor 
   */
  public ProfilePanel() {
    super(new MigLayout());
    JTextField text = new JTextField("hello");
    this.add(text);
  }
  
}
