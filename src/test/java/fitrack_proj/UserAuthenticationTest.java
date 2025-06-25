package fitrack_proj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import fitrack_proj.model.FitrackDatabase;
import fitrack_proj.util.PasswordEncryptor;

class UserAuthenticationTest {
  private FitrackDatabase connection = new FitrackDatabase();

  @Test
  void testRegisterLoginUser() {
    String username = "test_name";
    String password = "test_password";
    String gender = "male";
    int height = 190;
    int weight = 250;
    String activity = "none";
    int age = 25;
    assertEquals(1,
        connection.registerUser(username, password, gender, height, weight, activity, age));
    assertEquals(1, connection.verifyLogin(username, password));
  }

  @Test
  void testRegisterFailure() {
    String username = "test_name";
    String password = "231ninfds";
    String gender = "5";
    int height = 900;
    int weight = 2000;
    String activity = "555";
    int age = 2000;
    assertEquals(-1,
        connection.registerUser(username, password, gender, height, weight, activity, age));
  }

  @Test
  void testLoginFailure() {
    String username = "not_real";
    String password = "no_password";
    assertEquals(-1, connection.verifyLogin(username, password));
  }

}
