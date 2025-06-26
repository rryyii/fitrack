package fitrack_proj.view;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class RootPanel extends JPanel {
    public RootPanel() {
        this.setLayout(new MigLayout("wrap 1"));
    }

    public void setNavPanel(NavigationPanel panel) {
        this.add(panel, "growx");
    }

    public void setMainPanel(JPanel cards) {
        this.add(cards);
    }
}