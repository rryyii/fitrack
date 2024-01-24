package fitrack_proj.app;

import java.awt.CardLayout;

import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;

public class NutritionTrack extends JPanel {
	
	public void displayChart() {
		PieChart chart = new PieChart(5, 5);
		chart.addSeries("", 50);
		XChartPanel<PieChart> chartPanel = new SwingWrapper<>(chart).getXChartPanel();
		this.add(chartPanel);
	}
}