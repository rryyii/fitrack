package fitrack_proj.app;

import java.util.HashMap;

/**
 * Routine Class.
 *
 * @author yirw
 */
public class FitnessHistory {
  private static final HashMap<Integer, String> exerciseKeys = new HashMap<>();
  private static final HashMap<Integer, Double> metValues = new HashMap<>();

  public FitnessHistory() {
    initializeMaps();
  }

  /**
   * Initializes the two HashMaps containing exercises and their met values.
   */
  private void initializeMaps() {
    exerciseKeys.put(1, "sports");
    exerciseKeys.put(2, "biking");
    exerciseKeys.put(3, "resistance");
    exerciseKeys.put(4, "cardio");

    metValues.put(1, 7.8);
    metValues.put(2, 7.5);
    metValues.put(3, 3.5);
    metValues.put(4, 8.0);
  }

  private static double convertMetric(double measurement, String system) {
    double result = measurement;
    if (system.compareTo("lbs") == 0) {
      result /= 2.205;
    } else if (system.compareTo("inches") == 0) {
      result *= 2.54;
    }
    return result;
  }

  /**
   * Calculates a possible value of burnt calories according to the exercise.
   *
   * @return Double value of the burnt calories
   */
  public static double calculateBurntCal(int exerciseType, int timeElapsed, double weight) {
    double burntCalories = 0.0;
    double metValue = metValues.get(exerciseType);
    burntCalories = metValue * 3.5 * weight / 200;
    // met value * 3.5 * weight/200 = calories burnt per minute
    return burntCalories * timeElapsed;
  }

  /**
   * Calculates user's recommended calories to stay at the same body weight based on user's information.
   *
   * @param weight Weight of user
   * @param height Height of user
   * @param gender Gender of user
   * @param activity Activity level of user
   * @param age Age of user
   * @return Calories Value calculated in type int
   */
  public static int calculateCal(int weight, int height, String gender, String activity, int age) {
    int amr = 0;
    double bmr = 0.0;
    if (gender.equalsIgnoreCase("female")) {
      bmr = 655.1 + (9.563 * convertMetric(weight, "lbs"))
          + (1.850 * convertMetric(height, "inches")) - (4.676 * age);
    } else {
      bmr = 66.47 + (13.75 * convertMetric(weight, "lbs"))
          + (5.003 * convertMetric(height, "inches")) - (6.755 * age);
    }
    switch (activity.toLowerCase()) {
      case ("little to no exercise"):
        amr = (int) Math.round(bmr * 1.2);
        break;
      case ("moderate exercise"):
        amr = (int) Math.round(bmr * 1.55);
        break;
      case ("active"):
        amr = (int) Math.round(bmr * 1.725);
        break;
      case ("very active"):
        amr = (int) Math.round(bmr * 1.9);
        break;
    }
    return amr;
  }
  
  

}
