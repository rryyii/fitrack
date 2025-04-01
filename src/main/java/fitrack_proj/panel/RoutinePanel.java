package fitrack_proj.panel;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class RoutinePanel extends JPanel {

    /**
     * RoutinePanel constructor.
     *
     * @param cards CardLayout to use for return button
     */
    public RoutinePanel(final JPanel cards) {
        super(new MigLayout("wrap 3"));
        this.cards = cards;
    }

    private static final long serialVersionUID = 2223906239612989135L;
    private JPanel cards;

}
