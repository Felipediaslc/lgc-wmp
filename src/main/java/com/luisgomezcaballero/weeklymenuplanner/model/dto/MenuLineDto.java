package com.luisgomezcaballero.weeklymenuplanner.model.dto;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import java.io.Serializable;
import lombok.Data;

/**
 * This class represents a line of a menu.
 * 
 * @author Luis
 *
 */
@Data
public class MenuLineDto implements Serializable, Comparable<MenuLineDto> {

  private static final long serialVersionUID = 1L;

  /**
   * Line identifier.
   */
  private Integer menuLineId;

  /**
   * Type of meal: breakfast, lunch, dinner, others.
   */
  private Integer mealType;

  /**
   * Dish order in that type of meal.
   */
  private Integer dishOrder;

  /**
   * Menu.
   */
  private MenuHeader menu;

  /**
   * Day of the week.
   */
  private Integer weekday;

  /**
   * List of dishes of this line.
   */

  private MenuDish dish;

  @Override
  public int compareTo(final MenuLineDto other) {
    return this.getDishOrder() - other.getDishOrder();
  }

  /**
   * Constructor.
   * 
   * @param mealType  Type of meal.
   * @param dishOrder Order of the dish.
   * @param menu      Menu.
   * @param weekday   Weekday.
   * @param dish      Dish.
   */
  public MenuLineDto(final Integer mealType, final Integer dishOrder, final MenuHeader menu,
      final Integer weekday, final MenuDish dish) {
    super();
    this.mealType = mealType;
    this.dishOrder = dishOrder;
    this.menu = menu;
    this.weekday = weekday;
    this.dish = dish;
  }

  /**
   * Constructor.
   */
  public MenuLineDto() {
    super();
  }

}
