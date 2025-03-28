package fitrack_proj.panel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;

public class ChartTest {
  public static void main(String[] args) {
    PieChart chart = new PieChartBuilder().width(200).height(200).build();
    chart.addSeries("test", 25);
    chart.addSeries("test2", 25);
    chart.addSeries("tes3", 25);
    chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
    chart.getStyler().setLegendVisible(false);
    new SwingWrapper<PieChart>(chart).displayChart();
  }
}
