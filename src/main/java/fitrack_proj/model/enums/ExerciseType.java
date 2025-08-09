package fitrack_proj.model.enums;

/**
 * ExerciseType enum for the exercise input.
 *
 */
public enum ExerciseType {
  SPORTS(1), BIKING(2), RESISTANCE(3), CARDIO(4);

  private final int value;

  /**
   * ExerciseType main constructor.
   * 
   * @param value to retrieve associated enum
   */
  ExerciseType(int value) {
    this.value = value;
  }

  /**
   * Returns the value associated with the exercise type.
   * 
   * @return Int value of the exercise type.
   */
  public int getValue() {
    return value;
  }
}
