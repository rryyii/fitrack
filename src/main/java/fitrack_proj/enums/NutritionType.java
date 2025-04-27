package fitrack_proj.enums;

/**
 * NutritionType enum for the different nutritional types.
 * 
 */
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

  /**
   * NutritionType main constructor.
   * 
   * @param value Int value for associated  type
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
