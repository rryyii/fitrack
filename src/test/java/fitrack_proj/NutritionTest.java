package fitrack_proj;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import fitrack_proj.model.FitrackDatabase;

class NutritionTest {
  private FitrackDatabase connection = new FitrackDatabase();

  private void setUp() {
    String username = "test_name";
    String password = "test_password";
    String gender = "male";
    int height = 190;
    int weight = 250;
    String activity = "none";
    int age = 25;
    connection.registerUser(username, password, gender, height, weight, activity, age);
  }

  @Test
  void testInsertNutrition() {
    setUp();
    String food_name = "cherries";
    int serving_count = 20;
    assertEquals(1, connection.insertNutrition(1, food_name, serving_count));
  }

  @Test
  void testFailureInsertNutrition() {

  }

  @Test
  void testRetrieveNutrition() {
    int user_id = 1;
    assertNotNull(connection.retrieveNutrition(user_id));
  }

  @Test
  void testFailureRetrieveNutrition() {
    int user_id = -25;
    assertNull(connection.retrieveNutrition(user_id));
  }

}
