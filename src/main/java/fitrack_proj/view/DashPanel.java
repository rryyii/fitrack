package fitrack_proj.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.XChartPanel;

import com.formdev.flatlaf.FlatClientProperties;

import fitrack_proj.controller.DashController;
import fitrack_proj.controller.PanelController;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.LoginDAO;
import net.miginfocom.swing.MigLayout;

/**
 * DashPanel class creates the dashboard panel for the user to view their
 * exercise and nutrition history.
 */
public class DashPanel extends JPanel {

	/**
	 * DashPanel main constructor creates the dashboard panel
	 * 
	 * @param cards      JPanel of the current cards in the CardLayout
	 * @param connection Established SQL database connection
	 * @param user_id    Currently logged in user's id
	 */
	public DashPanel(JPanel cards, FitrackDatabase connection, LoginDAO loginModel, int user_id,
			PanelController panelController, DashController dashController) {
		super(new MigLayout("align center"));
		userInfo = new User(user_id, loginModel);
		panelController.setUser(userInfo);
		dashController.setController(this, connection);
		String[] macros = { "Protein", "Carbs", "Fats" };
		String[] exercise = { "Sports", "Biking", "Resistance", "Cardio" };
		add(createInfoPanel(), "wrap");
		add(panelHelper("Macros", macros));
		add(panelHelper("Exercise Distribution", exercise));

		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});
	}

	private JPanel createInfoPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(84, 89, 92));
		panel.putClientProperty(FlatClientProperties.STYLE, "arc: 8;");
		panel.setLayout(new MigLayout("insets 20", "grow"));
		JTextField username = new JTextField("Welcome back " + userInfo.getUsername());
		username.setEditable(false);
		username.putClientProperty("FlatLaf.styleClass", "h2");
		username.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
		username.setFocusable(false);
		panel.add(username, "wrap");
		JTextField weight = new JTextField("Weight: " + userInfo.getWeight());
		weight.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
		weight.putClientProperty("FlatLaf.styleClass", "h2");
		weight.setEditable(false);
		weight.setFocusable(false);
		panel.add(weight, "wrap");
		JTextField height = new JTextField("Height: " + userInfo.getHeight());
		height.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
		height.putClientProperty("FlatLaf.styleClass", "h2");
		height.setFocusable(false);
		height.setEditable(false);
		panel.add(height, "wrap");
		this.username = username;
		this.weight = weight;
		this.height = height;
		return panel;
	}

	private JPanel panelHelper(String category, String[] series) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap", "align center"));
		panel.setBackground(new Color(84, 89, 92));
		panel.putClientProperty(FlatClientProperties.STYLE, "arc: 8;");
		PieChart chart = new PieChartBuilder().width(200).height(200).build();
		for (String serie : series) {
			chart.addSeries(serie, 0);
		}
		chart.getStyler().setToolTipsEnabled(true);
		chart.getStyler().setToolTipBorderColor(null);
		chart.getStyler().setLabelsVisible(false);
		chart.getStyler().setDonutThickness(0.20);
		chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
		chart.getStyler().setLegendVisible(false);
		chart.getStyler().setChartBackgroundColor(new Color(84, 89, 92));
		chart.getStyler().setPlotBackgroundColor(new Color(84, 89, 92));
		chart.getStyler().setPlotBorderVisible(false);
		XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
		panel.add(chartPanel);
		JLabel label = new JLabel(category);
		label.putClientProperty("FlatLaf.styleClass", "h2");
		panel.add(label);
		if (category.equals("Macros")) {
			this.nutritionChart = chart;
		} else if (category.equals("Exercise Distribution")) {
			this.exerciseChart = chart;
		}
		return panel;
	}

	public JTextField getUsername() {
		return username;
	}

	public JTextField getUserHeight() {
		return height;
	}

	public JTextField getWeight() {
		return weight;
	}

	public PieChart getNutritionChart() {
		return nutritionChart;
	}

	public PieChart getExerciseChart() {
		return exerciseChart;
	}

	private PieChart nutritionChart;
	private PieChart exerciseChart;
	private User userInfo;
	private JTextField username;
	private JTextField height;
	private JTextField weight;

}
