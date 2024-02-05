package fitrack_proj.panel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class NutritionPanel extends JPanel {

	private JPanel cards;
	private ResultSet historySet;
	private static final long serialVersionUID = 2223906239612989135L;

	public NutritionPanel() {
	}

	public NutritionPanel(final JPanel cards) {
		this.cards = cards;
	}

	public JPanel createChart(ResultSet historySet) {
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
				}
			});
		});
		consts.gridx = 0;
		consts.gridy = 0;

		HashMap<String, Integer> historyMap = updateChart();
		PieChart chart = new PieChartBuilder().width(250).height(250).build();
		chart.getStyler().setPlotBackgroundColor(getBackground());
		chart.getStyler().setChartBackgroundColor(getBackground());

		for (Map.Entry<String, Integer> entry : historyMap.entrySet()) {
			chart.addSeries(entry.getKey(), entry.getValue());
		}

		chart.setTitle("Exercise Distribution:");
		XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
		consts.gridx = 10;
		consts.gridy = 10;
		chartPanel.add(returnButton, consts);
		return chartPanel;
	}

	public HashMap<String, Integer> updateChart() {
		HashMap<String, Integer> historyMap = new HashMap<>();
		try {
			while (this.historySet.next()) {
				String exerciseType = this.historySet.getString("exercise_type");
				int exerciseDuration = this.historySet.getInt("exercise_time");
				historyMap.put(exerciseType, exerciseDuration);
			}
		} catch (SQLException sqe) {
			System.out.println("Failed to update chart");
		}
		return historyMap;
	}
}