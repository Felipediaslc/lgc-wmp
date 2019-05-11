package com.luisgomezcaballero.weeklymenuplanner.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * This class reperesents a dish.
 * 
 * @author Luis
 *
 */
@Data
@Entity
@Table(name = "wmp_dish")
public class MenuDish implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Dish identifier.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer dishId;

  /**
   * Dish name.
   */
  private String name;

  /**
   * Dish ingredients.
   */
  @Column(length = 1024)
  private String ingredients;

  /**
   * Dish recipe.
   */
  @Column(length = 1024)
  private String recipe;

}
