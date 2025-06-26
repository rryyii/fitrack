package fitrack_proj.view;

import net.miginfocom.swing.MigLayout;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;

/**
 * NutritionPanel Class.
 *
 * @author ryanyi
 *
 */
public class ExercisePanel extends JPanel {

	/**
	 * NutritionPanel constructor.
	 *
	 * @param cards CardLayout to use for return button
	 */
	public ExercisePanel(final JPanel cards) {
		super(new MigLayout("wrap 3"));
		this.cards = cards;
		add(createExercisePanel());
		SwingUtilities.invokeLater(() -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.pack();
			}
		});
	}

	/**
	 * Creates the main panel for inputting exercise into the user's history.
	 * 
	 * @return JPanel panel with the added exercise Swing components
	 */
	private JPanel createExercisePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 1"));
		exerciseSelectLabel = new JLabel("Select performed exercise or activity type:");
		panel.add(exerciseSelectLabel);

		exerciseBox = new JComboBox<>();
		exerciseBox.addItem("");
		exerciseBox.addItem("Sports");
		exerciseBox.addItem("Biking");
		exerciseBox.addItem("Conditioning (resistance training)");
		exerciseBox.addItem("Cardio");
		exerciseBox.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
		panel.add(exerciseBox, "wrap");

		exerciseTimeLabel = new JLabel("Enter elapsed time (in minutes):");
		panel.add(exerciseTimeLabel);
		elapsedField = new JTextField(10);
		elapsedField.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
		panel.add(elapsedField, "wrap");

		exerciseHistoryLabel = new JLabel("Exercise History:");
		panel.add(exerciseHistoryLabel, "wrap");

		String column[] = { "Exercise Type", "Duration", "Date" };
		DefaultTableModel exerciseTable = new DefaultTableModel(column, 0);
		historyTable = new JTable(exerciseTable);
		historyTable.setFocusable(false);
		historyTable.setVisible(true);
		JScrollPane scroll = new JScrollPane(historyTable);
		scroll.setPreferredSize(new Dimension(350, 210));
		panel.add(scroll, "wrap");

		exerciseButton = new JButton("Submit");

		panel.add(exerciseButton, "wrap 30px");
		return panel;
	}
	

	public JComboBox<String> getExerciseBox() {
		return exerciseBox;
	}

	public JTable getHistoryTable() {
		return historyTable;
	}
	
	public JTextField getElapsedField() {
		return elapsedField;
	}

	public JButton getExerciseButton() {
		return exerciseButton;
	}


	private JLabel exerciseTimeLabel;
	private JTextField elapsedField;
	private JComboBox<String> exerciseBox;
	private JLabel exerciseHistoryLabel;
	private JLabel exerciseSelectLabel;
	private JButton exerciseButton;
	private JTable historyTable;
	private JPanel cards;
	private static final long serialVersionUID = 2223906239612989135L;
}
