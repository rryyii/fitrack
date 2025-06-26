package fitrack_proj.controller;

import java.awt.CardLayout;

import javax.swing.JPanel;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.User;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.view.DashPanel;
import fitrack_proj.view.ExercisePanel;
import fitrack_proj.view.GoalPanel;
import fitrack_proj.view.NavigationPanel;
import fitrack_proj.view.NutritionPanel;
import fitrack_proj.view.ProfilePanel;
import fitrack_proj.view.RegisterPanel;
import fitrack_proj.view.RootPanel;

public class PanelController {

	public PanelController(JPanel cards, CardLayout layout, FitrackDatabase connection) {
		this.cards = cards;
		this.layout = layout;
		this.connection = connection;
	}

	public void createRootPanel() {
		this.rootPanel = new RootPanel();
		this.rootPanel.setMainPanel(this.cards);
	}

	public void setRootPanel() {
		this.rootPanel.setNavPanel(navigationPanel);
	}

	public RootPanel getRootPanel() {
		return this.rootPanel;
	}

	public NavigationPanel getNavigationPanel() {
		return this.navigationPanel;
	}

	public void createNavigationPanel() {
		this.navigationPanel = new NavigationPanel(this.cards, this, user);
	}

	public void showRegisterPanel() {
		if (registerPanel == null) {
			createRegisterPanel();
		} else {
			layout.show(cards, "REGISTERPANEL");
		}
	}

	public RegisterPanel createRegisterPanel() {
		this.registerPanel = new RegisterPanel(cards);
		this.cards.add(registerPanel, "REGISTERPANEL");
		return this.registerPanel;
	}

	public void showDashPanel() {
		if (dashPanel == null) {
			System.out.println("No currently initialized DashPanel.");
		} else {
			layout.show(cards, "DASHPANEL");
		}
	}

	public void createDashPanel(int user_id, FitrackDatabase connection, LoginDAO loginModel,
			DashController dashController) {
		this.dashPanel = new DashPanel(cards, connection, loginModel, user_id, this, dashController);
		this.loginDAO = loginModel;
		this.dashController = dashController;
		this.connection = connection;
		this.cards.add(dashPanel, "DASHPANEL");
	}

	public void showGoalPanel() {
		if (goalPanel == null) {
			createGoalPanel(this.user);
			layout.show(cards, "GOALPANEL");
		} else {
			layout.show(cards, "GOALPANEL");
		}
	}

	public void createGoalPanel(User user) {
		this.goalPanel = new GoalPanel(user, cards, this);
		this.cards.add(goalPanel, "GOALPANEL");
	}

	public void showProfilePanel() {
		if (profilePanel == null) {
			createProfilePanel(this.user);
			layout.show(cards, "PROFILEPANEL");
		} else {
			layout.show(cards, "PROFILEPANEL");
		}
	}

	public void createProfilePanel(User user) {
		this.profilePanel = new ProfilePanel(user, cards, this);
		this.cards.add(profilePanel, "PROFILEPANEL");
	}

	public void createNutritionPanel() {
		if (this.nutritionPanel == null) {
			this.nutritionPanel = new NutritionPanel(user, cards);
			this.cards.add(nutritionPanel, "NUTRITIONPANEL");
		}
	}

	public void createExercisePanel() {
		if (this.exercisePanel == null) {
			this.exercisePanel = new ExercisePanel(cards);
			this.cards.add(exercisePanel, "EXERCISEPANEL");
		}
	}

	public void showExercisePanel() {
		layout.show(cards, "EXERCISEPANEL");
	}

	public void showNutritionPanel() {
		layout.show(cards, "NUTRITIONPANEL");
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginDAO getLoginDAO() {
		return this.loginDAO;
	}

	public DashController getDashController() {
		return this.dashController;
	}

	public void setGoalController(GoalController controller) {
		this.goalController = controller;
	}

	public void createGoalController() {
		this.goalController = new GoalController();
	}

	public GoalController getGoalController() {
		return this.goalController;
	}

	public User getUser() {
		return this.user;
	}

	public FitrackDatabase getConnection() {
		return this.connection;
	}

	public void createProfileController() {
		this.profileController = new ProfileController();
		this.profileController.setDashPanel(dashPanel);
	}

	public ProfileController getProfileController() {
		return this.profileController;
	}

	public void createExerciseController() {
		new ExerciseController(exercisePanel, connection, user);
	}

	public void createNutritionController(User user) {
		new NutritionController(nutritionPanel, connection, user);
	}

	private NutritionPanel nutritionPanel;
	private ExercisePanel exercisePanel;
	private RootPanel rootPanel;
	private FitrackDatabase connection;
	private LoginDAO loginDAO;
	private DashController dashController;
	private GoalController goalController;
	private ProfileController profileController;
	private User user;
	private JPanel cards;
	private CardLayout layout;
	private ProfilePanel profilePanel;
	private RegisterPanel registerPanel;
	private NavigationPanel navigationPanel;
	private DashPanel dashPanel;
	private GoalPanel goalPanel;

}