package fitrack_proj.enums;

/**
 * ActivityLevel enum for the user's current activity level.
 * 
 */
public enum ActivityLevel {
  LITTLE_TO_NO_EXERCISE(1),
  MODERATE_EXERCISE(2),
  ACTIVE(3),
  VERY_ACTIVE(4);

  private final int value;
 
  /**
   * ActivityLevel main constructor.
   *
   * @param value to retrieve associated enum
   */
  ActivityLevel(int value) {
    this.value = value;
  }

  /**
   * Returns the value associated with the activity level.
   * 
   * @return Int value of the activity level
   */
  public int getValue() {
    return value;
  }
}
