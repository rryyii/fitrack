package fitrack_proj.enums;

public enum NutritionType {
  ADDED_SUGARS(1),
  CALCIUM(2),
  CHOLESTEROL(3),
  DIETARY_FIBER(4),
  FAT(5),
  PROTEIN(6),
  SODIUM(7),
  CARBOHYDRATE(8);

  private final int value;

 NutritionType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
