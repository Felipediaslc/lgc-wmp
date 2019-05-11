package com.luisgomezcaballero.weeklymenuplanner.model.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * This class reperesents a dish.
 * 
 * @author Luis
 *
 */
@Data
public class MenuDishDto implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Dish identifier.
   */
  private Integer dishId;

  /**
   * Dish name.
   */
  private String name;

  /**
   * Dish ingredients.
   */
  private String ingredients;

  /**
   * Dish recipe.
   */
  private String recipe;

}
