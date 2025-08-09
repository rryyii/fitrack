package fitrack_proj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fitrack_proj.model.dao.ExerciseDAO;

class ExerciseTest extends BaseTest {
  private ExerciseDAO model = new ExerciseDAO(connection.getConnection());

  @Test
  void testInsertExercise() {
    setUp();
    int user_id = 1;
    String exercise = "cardio";
    int time = 25;
    assertEquals(1, model.insertExercise(exercise, time, user_id));
  }

  @Test
  void testFailureInsertExercise() {

  }

  @Test
  void testRetrieveExercise() {
    int user_id = 1;
    assertNotNull(model.retrieveHistory(user_id));
  }

  @Test
  void testFailureRetrieveExercise() {
    int user_id = -25;
    assertNull(model.retrieveHistory(user_id));
  }

}
