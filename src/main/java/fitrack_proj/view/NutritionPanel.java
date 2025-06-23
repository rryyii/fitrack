package fitrack_proj.view;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;

/**
 * NutritionPanel Class.
 *
 * @author ryanyi
 *
 */
public class NutritionPanel extends JPanel {

  /**
   * NutritionPanel constructor.
   *
   * @param cards CardLayout to use for return button
   */
  public NutritionPanel(final JPanel cards) {
    super(new MigLayout("wrap 3"));
    this.cards = cards;

  }


  private JPanel cards;
  private static final long serialVersionUID = 2223906239612989135L;
}
