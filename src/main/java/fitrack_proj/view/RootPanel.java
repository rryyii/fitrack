package fitrack_proj.view;

import javax.swing.JPanel;
import fitrack_proj.controller.PanelController;
import net.miginfocom.swing.MigLayout;

public class RootPanel extends JPanel {
  
  public RootPanel(PanelController panelController) {
    
    this.setLayout(new MigLayout());
    this.panelController = panelController;
  }
  
  private PanelController panelController;
  
}
