package fitrack_proj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.model.dao.RegisterDAO;

class UserAuthenticationTest extends BaseTest {
  private RegisterDAO registerDAO = new RegisterDAO(connection.getConnection());
  private LoginDAO loginDAO = new LoginDAO(connection.getConnection());
  private String username;
  private String password;

  @Test
  void testRegisterSuccess() {
    username = "test_name";
    password = "test_password";
    String gender = "male";
    int height = 190;
    int weight = 250;
    String activity = "none";
    int age = 25;
    assertEquals(1, registerDAO.registerUser(activity, activity, gender, height, weight, activity, age));
  }

  @Test
  void testRegisterFailure() {
    username = "test_name";
    password = "231ninfds";
    String gender = "5";
    int height = 900;
    int weight = 2000;
    String activity = "555";
    int age = 2000;
    assertEquals(-1, registerDAO.registerUser(username, password, gender, height, weight, activity, age));
  }

  @Test
  void testLoginFailure() {
    username = "not_real";
    password = "no_password";
    assertEquals(-1, loginDAO.verifyLogin(username, password));
  }
  
  @Test
  void testLoginSuccess() {
    username = "test_name";
    password = "test_password";
    assertEquals(-1, loginDAO.verifyLogin(username, password));
  }

}
