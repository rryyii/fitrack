package fitrack_proj.controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.ExerciseDAO;
import fitrack_proj.model.dao.NutritionDAO;
import fitrack_proj.view.DashPanel;

public class DashController {

	public void setController(DashPanel panel, FitrackDatabase connection) {
		this.panel = panel;
		this.exerciseModel = new ExerciseDAO(connection.getConnection());
	}


	private User userInfo;
	private ExerciseDAO exerciseModel;
	private NutritionDAO nutritionModel;

	private DashPanel panel;
	private JComboBox<String> exerciseBox;
	private JTextField elapsedField;
	private JButton exerciseButton;
}
