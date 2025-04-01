package fitrack_proj.enums;

public enum ExerciseType {
  SPORTS(1),
  BIKING(2),
  RESISTANCE(3),
  CARDIO(4);

  private final int value;

  ExerciseType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
