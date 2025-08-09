package fitrack_proj.util;

import java.util.HashMap;

import fitrack_proj.model.enums.ExerciseType;
import fitrack_proj.model.enums.NutritionType;

/**
 * Routine Class.
 *
 * @author yirw
 */
public class FitnessHistory {
	public static final HashMap<NutritionType, NutritionMap> dailyNutrientValues = new HashMap<>();

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
	public static double calculateBurntCal(ExerciseType exerciseType, int timeElapsed, double weight) {
		double burntCalories = 0.0;
		double metValue = exerciseType.getValue();
		burntCalories = metValue * 3.5 * weight / 200;
		// met value * 3.5 * weight/200 = calories burnt per minute
		return burntCalories * timeElapsed;
	}

	/**
	 * Calculates user's recommended calories to stay at the same body weight based
	 * on user's information.
	 *
	 * @param weight   Weight of user
	 * @param height   Height of user
	 * @param gender   Gender of user
	 * @param activity Activity level of user
	 * @param age      Age of user
	 * @return Calories Value calculated in type int
	 */
	public static int calculateCal(double weightKg, double heightCm, String gender, String activity, int age) {
		int amr = 0;
		double bmr = 0.0;
		if (gender.equalsIgnoreCase("female")) {
			bmr = 655.1 + (9.563 * weightKg) + (1.850 * heightCm) - (4.676 * age);
		} else {
			bmr = 66.47 + (13.75 * weightKg) + (5.003 * heightCm) - (6.755 * age);
		}
		switch (activity.toLowerCase()) {
		case "little to no exercise":
			amr = (int) Math.round(bmr * 1.2);
			break;
		case "moderate exercise":
			amr = (int) Math.round(bmr * 1.55);
			break;
		case "active":
			amr = (int) Math.round(bmr * 1.725);
			break;
		case "very active":
			amr = (int) Math.round(bmr * 1.9);
			break;
		}
		return amr;
	}

}

class NutritionMap {

	public NutritionMap(double amount, String unit) {
		this.amount = amount;
		this.unit = unit;
	}

	public double amount;
	public String unit;

}
