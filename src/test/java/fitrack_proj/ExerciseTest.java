package fitrack_proj;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import fitrack_proj.util.FitrackDatabase;

class ExerciseTest {
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
  void testInsertExercise() {
    setUp();
    int user_id = 1;
    String exercise = "cardio";
    int time = 25;
    assertEquals(1, connection.insertExercise(exercise, time, user_id));
  }
  
  @Test
  void testFailureInsertExercise() {
    
  }
  
  @Test
  void testRetrieveExercise() {
    int user_id = 1;
    assertNotNull(connection.retrieveHistory(user_id));
  }
  
  @Test
  void testFailureRetrieveExercise() {
    int user_id = -25;
    assertNull(connection.retrieveHistory(user_id));
  }
  
}
