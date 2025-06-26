package fitrack_proj.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatClientProperties;
import fitrack_proj.model.User;
import fitrack_proj.model.enums.NutritionType;
import net.miginfocom.swing.MigLayout;

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
  public NutritionPanel(User user, JPanel cards) {
    super(new MigLayout("", "center", "top"));
    this.cards = cards;
    this.userInfo = user;
    add(createNutritionPanel());
    add(createTable());
    SwingUtilities.invokeLater(() -> {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
      if (frame != null) {
        frame.pack();
      }
    });

  }

  /**
   * Creates a new panel for inputting nutrition information for the user.
   * 
   * @return JPanel panel with the added nutritional Swing components
   */
  private JPanel createNutritionPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    panel.setBackground(new Color(25, 5, 22));
    panel.putClientProperty(FlatClientProperties.STYLE, "arc:8");

    nutritionLabel = new JLabel("Nutrition Tracker");
    nutritionLabel.putClientProperty("FlatLaf.styleClass", "h1");
    panel.add(nutritionLabel, "wrap");

    foodNameLabel = new JLabel("Food Name: ");
    panel.add(foodNameLabel);
    foodNameField = new JTextField(10);
    foodNameField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(foodNameField, "wrap");

    servingLabel = new JLabel("Servings: ");
    panel.add(servingLabel);
    servingField = new JTextField(10);
    servingField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(servingField, "wrap");

    caloriesLabel = new JLabel("Calories: ");
    panel.add(caloriesLabel);
    caloriesField = new JTextField(10);
    caloriesField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(caloriesField, "wrap");

    proteinLabel = new JLabel("Protein: ");
    panel.add(proteinLabel);
    proteinField = new JTextField(10);
    proteinField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(proteinField, "wrap");

    carbLabel = new JLabel("Carbohydrates: ");
    panel.add(carbLabel);
    carbField = new JTextField(10);
    carbField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(carbField, "wrap");

    fatLabel = new JLabel("Fats: ");
    panel.add(fatLabel);
    fatField = new JTextField(10);
    fatField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(fatField, "wrap");

    typeLabel = new JLabel("Meal Type: ");
    panel.add(typeLabel);
    typeField = new JComboBox<String>();
    typeField.addItem("Breakfast");
    typeField.addItem("Lunch");
    typeField.addItem("Dinner");
    typeField.addItem("Snack");
    typeField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
    panel.add(typeField, "wrap, align center");
    foodButton = new JButton("Submit");
    panel.add(foodButton, "wrap");
    return panel;
  }

  private JPanel createTable() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    panel.setBackground(new Color(25, 5, 22));
    panel.putClientProperty(FlatClientProperties.STYLE, "arc:8");
    foodHistoryLabel = new JLabel("History");
    foodHistoryLabel.putClientProperty("FlatLaf.styleClass", "h1");
    panel.add(foodHistoryLabel, "wrap");
    String foodColumn[] =
        {"Food", "Servings", "Calories", "Protein", "Carbs", "Fats", "Meal Type", "Date"};
    DefaultTableModel nutritionTable = new DefaultTableModel(foodColumn, 0);
    foodTable = new JTable(nutritionTable);
    foodTable.setFocusable(false);
    foodTable.setVisible(true);

    JScrollPane scroll = new JScrollPane(foodTable);
    scroll.setPreferredSize(new Dimension(350, 210));
    panel.add(scroll, "wrap");


    UIManager.put("ProgressBar.horizontalSize", new Dimension(250, 15));
    UIManager.put("ProgressBar.arc", 15);


    caloriesBar = new JProgressBar(SwingConstants.HORIZONTAL);
    caloriesBar.setMaximum(userInfo.calculateCalories());
    caloriesBar.setStringPainted(true);
    panel.add(caloriesBar, "wrap");
    proteinBar = new JProgressBar(SwingConstants.HORIZONTAL);
    proteinBar.setName("gsdfsdfd");
    proteinBar.setStringPainted(true);
    proteinBar.setMaximum(NutritionType.PROTEIN.getValue());
    panel.add(proteinBar, "wrap");
    carbBar = new JProgressBar(SwingConstants.HORIZONTAL);
    carbBar.setStringPainted(true);
    carbBar.setMaximum(NutritionType.CARBOHYDRATE.getValue());
    panel.add(carbBar, "wrap");
    fatBar = new JProgressBar(SwingConstants.HORIZONTAL);
    fatBar.setStringPainted(true);
    fatBar.setMaximum(NutritionType.FAT.getValue());
    panel.add(fatBar);
    return panel;
  }

  public JTextField getCaloriesField() {
    return caloriesField;
  }

  public JTextField getProteinField() {
    return proteinField;
  }

  public JTextField getCarbField() {
    return carbField;
  }

  public JTextField getFatField() {
    return fatField;
  }

  public JProgressBar getCaloriesBar() {
    return caloriesBar;
  }

  public JComboBox<String> getTypeField() {
    return typeField;
  }

  public JButton getFoodButton() {
    return foodButton;
  }

  public JTextField getServingField() {
    return servingField;
  }

  public JTextField getFoodNameField() {
    return foodNameField;
  }

  public JTable getFoodTable() {
    return foodTable;
  }

  public User getUserInfo() {
    return userInfo;
  }

  public JProgressBar getProteinBar() {
    return proteinBar;
  }

  public JProgressBar getCarbBar() {
    return carbBar;
  }

  public JProgressBar getFatBar() {
    return fatBar;
  }

  private User userInfo;
  private JButton foodButton;
  private JTextField servingField;
  private JTextField foodNameField;
  private JTextField caloriesField;
  private JTextField proteinField;
  private JTextField carbField;
  private JTextField fatField;
  private JProgressBar caloriesBar;
  private JProgressBar proteinBar;
  private JProgressBar carbBar;
  private JProgressBar fatBar;
  private JComboBox<String> typeField;

  private JLabel caloriesLabel;
  private JLabel proteinLabel;
  private JLabel carbLabel;
  private JLabel fatLabel;
  private JLabel typeLabel;
  private JLabel nutritionLabel;
  private JLabel foodNameLabel;
  private JLabel foodHistoryLabel;
  private JLabel servingLabel;
  private JTable foodTable;
  private JPanel cards;
  private static final long serialVersionUID = 2223906239612989135L;
}
