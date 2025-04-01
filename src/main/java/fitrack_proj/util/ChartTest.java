package fitrack_proj.util;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import fitrack_proj.enums.NutritionType;

public class ChartTest {
  public static void main(String[] args) {
    PieChart chart = new PieChartBuilder().width(200).height(200).build();
    chart.addSeries("test", 25);
    chart.addSeries("test2", 25);
    chart.addSeries("tes3", 25);
    chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
    chart.getStyler().setLegendVisible(false);
    new SwingWrapper<PieChart>(chart).displayChart();
    
    JFrame t = new JFrame();
    JPanel p = new JPanel();
    FitnessHistory h = new FitnessHistory();
//    for (Map.Entry<NutritionType, NutritionMap> entry.entrySet()) {
//      NutritionType key = entry.getKey();
//      NutritionMap value = entry.getValue();
//      JProgressBar pb = new JProgressBar(SwingConstants.HORIZONTAL);
//      pb.setMaximum((int) value.amount);
//      pb.setMinimum(0);
//      pb.setValue(25);
//      pb.setBorderPainted(true);      
//      pb.setStringPainted(true);
//      p.add(pb);
//  }
//    t.add(p);
//    t.setVisible(true);
    /**
     * Creates the PieChart to add to the Panel.
     *
     * @param historySet ResultSet of the user's history to use to fill chart
     * @return The JPanel containing the PieChart
     */
//    public JPanel createChart(ResultSet historySet) {
//      this.historySet = historySet;
//      this.setLayout(new GridBagLayout());
//      GridBagConstraints consts = new GridBagConstraints();
//      consts.insets = new Insets(5, 5, 5, 5);
//
//      JButton returnButton = new JButton("Return");
//      returnButton.addActionListener(e -> {
//        SwingUtilities.invokeLater(new Runnable() {
//          @Override
//          public void run() {
//            ((CardLayout) cards.getLayout()).show(cards, "dash");
//          }
//        });
//      });
//      consts.gridx = 0;
//      consts.gridy = 0;
//
//      this.chart = buildChart();
//      updateChart(this.historySet);
//
//      chart.setTitle("Exercise Distribution:");
//      XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
//      consts.gridx = 10;
//      consts.gridy = 10;
//      chartPanel.add(returnButton, consts);
//      return chartPanel;
//    }
//
//    /**
//     * Updates the chart to contain the information in the ResultSet.
//     *
//     * @return Returns a HashMap of type String, Integer that contains the information for the chart
//     *         series
//     */
//    public HashMap<String, Integer> updateChart(ResultSet historySet) {
//      HashMap<String, Integer> map = new HashMap<>();
//      map.clear();
//      try {
//        while (historySet.next()) {
//          String exerciseType = historySet.getString("exercise_type");
//          int exerciseDuration = historySet.getInt("exercise_time");
//          map.put(exerciseType, exerciseDuration);
//        }
//      } catch (SQLException sqe) {
//        System.out.println("Failed to update chart");
//      }
//      return map;
//    }
//
//
//    /**
//     * Builds the actual chart and sets several visual aspects of it.
//     *
//     * @return The created PieChart with its visuals
//     */
//    private PieChart buildChart() {
//      PieChart chart = new PieChartBuilder().width(250).height(250).build();
//      chart.getStyler().setBaseFont(getFont());
//      chart.getStyler().setPlotBackgroundColor(getBackground());
//      chart.getStyler().setChartBackgroundColor(getBackground());
//      chart.getStyler().setChartFontColor(Color.WHITE);
//      chart.getStyler().setPlotBorderColor(getBackground());
//      chart.getStyler().setLegendBackgroundColor(getBackground());
//      chart.getStyler().setChartTitleBoxBackgroundColor(getBackground());
//      chart.getStyler().setChartTitleBoxBorderColor(getBackground());
//      return chart;
//    }
  }
  

  
}
