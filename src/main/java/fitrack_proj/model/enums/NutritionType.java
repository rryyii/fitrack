package fitrack_proj.model.enums;

/**
 * NutritionType enum for the different nutritional types.
 * 
 */
public enum NutritionType {
  ADDED_SUGARS(50), CALCIUM(1300), CHOLESTEROL(3), DIETARY_FIBER(4), FAT(78), PROTEIN(50), SODIUM(
      7), CARBOHYDRATE(275);

  private final int value;

  /**
   * NutritionType main constructor.
   * 
   * @param value Int value for associated type
   */
  NutritionType(int value) {
    this.value = value;
  }

  /**
   * Returns the currently set value.
   *
   * @return Int of the current value
   */
  public int getValue() {
    return value;
  }
}
