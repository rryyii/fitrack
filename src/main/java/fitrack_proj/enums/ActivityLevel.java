package fitrack_proj.enums;

public enum ActivityLevel {
  LITTLE_TO_NO_EXERCISE(1),
  MODERATE_EXERCISE(2),
  ACTIVE(3),
  VERY_ACTIVE(4);

  private final int value;

  ActivityLevel(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
