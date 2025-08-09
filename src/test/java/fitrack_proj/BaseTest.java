package fitrack_proj;

import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.dao.RegisterDAO;

public abstract class BaseTest {
  
  public FitrackDatabase connection = new FitrackDatabase();
  
  public void setUp() {
    String username = "test_name";
    String password = "test_password";
    String gender = "male";
    int height = 190;
    int weight = 250;
    String activity = "none";
    int age = 25;
    new RegisterDAO(connection.getConnection()).registerUser(username, password, gender, height, weight, activity, age);
  }
}
