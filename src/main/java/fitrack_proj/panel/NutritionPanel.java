package fitrack_proj.panel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.ChartTheme;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
