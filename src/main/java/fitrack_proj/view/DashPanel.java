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
 * DashPanel class creates the dashboard panel for the user to view their exercise and nutrition
 * history.
 */
public class DashPanel extends JPanel {

  /**
   * DashPanel main constructor creates the dashboard panel
   * 
   * @param cards JPanel of the current cards in the CardLayout
   * @param connection Established SQL database connection
   * @param user_id Currently logged in user's id
   */
  public DashPanel(JPanel cards, FitrackDatabase connection, LoginDAO loginModel, int user_id,
      PanelController panelController, DashController dashController) {
    super(new MigLayout("align center"));
    userInfo = new User(user_id, loginModel);
    this.controller = panelController;
    panelController.setUser(userInfo);
    dashController.setController(this, connection);
    add(createInfoPanel(), "wrap");
    add(createNutritionChart());
    add(createExerciseChart());

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

  private JPanel createNutritionChart() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap", "align center"));
    panel.setBackground(new Color(84, 89, 92));
    panel.putClientProperty(FlatClientProperties.STYLE, "arc: 8;");
    PieChart chart = new PieChartBuilder().width(200).height(200).build();
    chart.addSeries("test", 25);
    chart.addSeries("test2", 25);
    chart.addSeries("tes3", 25);
    chart.getStyler().setDonutThickness(0.15);
    chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setChartBackgroundColor(new Color(84, 89, 92));
    chart.getStyler().setPlotBackgroundColor(new Color(84, 89, 92));
    chart.getStyler().setPlotBorderVisible(false);
    XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
    panel.add(chartPanel);
    JLabel label = new JLabel("Macros");
    panel.add(label);
    return panel;
  }

  private JPanel createExerciseChart() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap", "align center"));
    panel.setBackground(new Color(84, 89, 92));
    panel.putClientProperty(FlatClientProperties.STYLE, "arc: 8;");
    PieChart nutritionChart = new PieChartBuilder().width(200).height(200).build();
    nutritionChart.addSeries("test", 25);
    nutritionChart.addSeries("test2", 25);
    nutritionChart.addSeries("tes3", 25);
    nutritionChart.getStyler().setDonutThickness(0.15);
    nutritionChart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
    nutritionChart.getStyler().setLegendVisible(false);
    nutritionChart.getStyler().setChartBackgroundColor(new Color(84, 89, 92));
    nutritionChart.getStyler().setPlotBackgroundColor(new Color(84, 89, 92));
    nutritionChart.getStyler().setPlotBorderVisible(false);
    XChartPanel<PieChart> nutritionChartPanel = new XChartPanel<>(nutritionChart);
    panel.add(nutritionChartPanel);
    JLabel label = new JLabel("Exercise Distribution");
    panel.add(label);
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

  private User userInfo;
  private JTextField username;
  private PanelController controller;
  private JTextField height;
  private JTextField weight;

}
