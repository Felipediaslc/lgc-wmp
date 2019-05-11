package com.luisgomezcaballero.weeklymenuplanner.model.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * This class represents a line of a menu.
 * 
 * @author Luis
 *
 */
@Data
@Entity
@Table(name = "wmp_menu_line")
public class MenuLine implements Serializable, Comparable<MenuLine> {

  private static final long serialVersionUID = 1L;

  /**
   * Line identifier.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "menuId")
  private MenuHeader menu;

  /**
   * Day of the week.
   */
  private Integer weekday;

  /**
   * List of dishes of this line.
   */

  @ManyToOne
  @JoinColumn(name = "dishId", nullable = false)
  private MenuDish dish;

  @Override
  public int compareTo(final MenuLine other) {
    return this.getDishOrder() - other.getDishOrder();
  }

  /**
   * Constructor.
   * 
   * @param mealType Type of meal.
   * @param dishOrder Order of the dish.
   * @param menu Menu.
   * @param weekday Weekday.
   * @param dish Dish.
   */
  public MenuLine(final Integer mealType, final Integer dishOrder, final MenuHeader menu,
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
  public MenuLine() {
    super();
  }

}
