package fitrack_proj;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import fitrack_proj.model.dao.NutritionDAO;

class NutritionTest extends BaseTest {
  private NutritionDAO model = new NutritionDAO(connection.getConnection());
  

  @Test
  void testInsertNutrition() {
    setUp();
    String food_name = "cherries";
    int serving_count = 20;
//    assertEquals(1, model.insertNutrition(1, food_name, serving_count));
  }

  @Test
  void testFailureInsertNutrition() {

  }

  @Test
  void testRetrieveNutrition() {
    int user_id = 1;
    assertNotNull(model.retrieveNutrition(user_id));
  }

  @Test
  void testFailureRetrieveNutrition() {
    int user_id = -25;
    assertNull(model.retrieveNutrition(user_id));
  }

}
