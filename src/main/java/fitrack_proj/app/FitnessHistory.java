package fitrack_proj.app;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import fitrack_proj.util.FitrackDatabase;

/**
 * Routine Class.
 *
 * @author yirw
 *
 */
public class FitnessHistory {
	private static HashMap<Integer, String> exerciseKeys = new HashMap<>();
	private static HashMap<Integer, Double> metValues = new HashMap<>();

	public FitnessHistory() {
		exerciseKeys.put(1, "sports");
		exerciseKeys.put(2, "biking");
		exerciseKeys.put(3, "resistance");
		exerciseKeys.put(4, "cardio");

		metValues.put(1, 7.8);
		metValues.put(2, 7.5);
		metValues.put(3, 3.5);
		metValues.put(4, 8.0);
	}

	private static double convertImperial(double measurement, String system) {
		double result = measurement;
		if (system.compareTo("lbs") < 0 || system.compareTo("inches") < 0) {
			return result;
		}
		if (system.compareTo("lbs") == 0) {
			result /= 2.2046;
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
		// met value * 3.5 * weight/200 = calories burnt per minute (so * 60 to get per
		// hour)
		return burntCalories * timeElapsed;
	}

	/**
	 * Calculates user's recommended calories based on user's information.
	 *
	 * @param weight   Weight of user
	 * @param height   Height of user
	 * @param gender   Gender of user
	 * @param activity Activity level of user
	 * @param age      Age of user
	 * @return Calorie value calculated
	 */
	public static int calculateCal(int weight, int height, String gender, String activity, int age, String stringWeight) {
		int amr = 0;
		String system = "";
		if (stringWeight.compareTo("pounds") == 0) {
			system = stringWeight;
		} else if (stringWeight.compareTo("inches") == 0) {
			system = stringWeight;
		}
		double bmr = 0.0;
		if (gender.toLowerCase().equals("female")) {
			bmr = 655.1 + (9.563 * convertImperial(weight, system)) + (1.850 * convertImperial(height, system)) - (4.676 * age);
		} else {
			bmr = 66.47 + (13.75 * convertImperial(weight, system)) + (5.003 * convertImperial(height, system)) - (6.755 * age);
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

	/**
	 * Retrieves the user's complete history
	 */
	public static ArrayList<String> retrieveHistory(FitrackDatabase connection, String name, ResultSet result,
			int userid) {
		ArrayList<String> userHistory = connection.retrieveHistory(name, result, userid);
		return userHistory;
	}

}
