package fitrack_proj.panel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.ChartTheme;

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

  private JPanel cards;
  private PieChart chart;
  private ResultSet historySet;
  private static final long serialVersionUID = 2223906239612989135L;

  /**
   * NutritionPanel constructor.
   *
   * @param cards CardLayout to use for return button
   */
  public NutritionPanel(final JPanel cards) {
    this.cards = cards;
  }

  /**
   * Creates the PieChart to add to the Panel.
   *
   * @param historySet ResultSet of the user's history to use to fill chart
   * @return The JPanel containing the PieChart
   */
  public JPanel createChart(ResultSet historySet, JFrame frame) {
    this.historySet = historySet;
    this.setLayout(new GridBagLayout());
    GridBagConstraints consts = new GridBagConstraints();
    consts.insets = new Insets(5, 5, 5, 5);

    JButton returnButton = new JButton("Return");
    returnButton.addActionListener(e -> {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          ((CardLayout) cards.getLayout()).show(cards, "dash");
          frame.pack();
        }
      });
    });
    consts.gridx = 0;
    consts.gridy = 0;

    this.chart = buildChart();
    updateChart(this.historySet);

    chart.setTitle("Exercise Distribution:");
    XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
    consts.gridx = 10;
    consts.gridy = 10;
    chartPanel.add(returnButton, consts);
    return chartPanel;
  }

  /**
   * Updates the chart to contain the information in the ResultSet.
   *
   * @return Returns a HashMap of type String, Integer that contains the information for the chart
   *         series
   */
  public HashMap<String, Integer> updateChart(ResultSet historySet) {
    HashMap<String, Integer> map = new HashMap<>();
    map.clear();
    try {
      while (historySet.next()) {
        String exerciseType = historySet.getString("exercise_type");
        int exerciseDuration = historySet.getInt("exercise_time");
        map.put(exerciseType, exerciseDuration);
        chart.addSeries(exerciseType, exerciseDuration);
      }
    } catch (SQLException sqe) {
      System.out.println("Failed to update chart");
    }
    return map;
  }


  /**
   * Builds the actual chart and sets several visual aspects of it.
   *
   * @return The created PieChart with its visuals
   */
  private PieChart buildChart() {
    PieChart chart = new PieChartBuilder().width(250).height(250).build();
    chart.getStyler().setBaseFont(getFont());
    chart.getStyler().setPlotBackgroundColor(getBackground());
    chart.getStyler().setChartBackgroundColor(getBackground());
    chart.getStyler().setChartFontColor(Color.WHITE);
    chart.getStyler().setPlotBorderColor(getBackground());
    chart.getStyler().setLegendBackgroundColor(getBackground());
    chart.getStyler().setChartTitleBoxBackgroundColor(getBackground());
    chart.getStyler().setChartTitleBoxBorderColor(getBackground());
    return chart;
  }
}
