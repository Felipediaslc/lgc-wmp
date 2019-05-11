package com.luisgomezcaballero.weeklymenuplanner.controller;

import com.luisgomezcaballero.weeklymenuplanner.model.dto.MenuDishDto;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import com.luisgomezcaballero.weeklymenuplanner.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for dishes.
 * 
 * @author Luis
 *
 */
@Controller
public class DishController {

  /**
   * Constant.
   */
  private static final String DISHES_UPDATE_DISH = "dishes/updateDish";

  /**
   * Constant.
   */
  private static final String DISH_ID = "dishId";

  /**
   * Constant.
   */
  private static final String EMPTY = "";

  /**
   * Constant.
   */
  private static final String REGEXP_CRLF = "(\\r)";

  /**
   * Constant.
   */
  private static final String DISHES_NEW_DISH = "dishes/newDish";

  /**
   * Constant.
   */
  private static final String DISH = "dish";

  /**
   * URL for all dishes.
   */
  private static final String DISHES_DISHES = "dishes/dishes";

  /**
   * All dishes object name.
   */
  private static final String ALL_DISHES = "allDishes";
  /**
   * Service for dishes.
   */
  @Autowired
  private final DishService dishService;

  /**
   * Constructor for the controller of dishes.
   * 
   * @param dishService Dish service.
   */
  public DishController(final DishService dishService) {
    super();
    this.dishService = dishService;
  }

  /**
   * Returns all dishes.
   * 
   * @return
   */
  @GetMapping("/dishes")
  public ModelAndView dishes() {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_DISHES, dishService.findAll());
    modelAndView.setViewName(DISHES_DISHES);
    return modelAndView;
  }

  /**
   * Shows the create dish form.
   * 
   * @return
   */
  @GetMapping("/dishes/create")
  public ModelAndView newDish() {
    final ModelAndView modelAndView = new ModelAndView();
    final MenuDish menuDish = new MenuDish();
    modelAndView.addObject(DISH, menuDish);
    modelAndView.setViewName(DISHES_NEW_DISH);
    return modelAndView;
  }

  /**
   * Creates a dish.
   * 
   * @param menuDishDto Related menu.
   * @return
   */
  @PostMapping("/dishes/create")
  public ModelAndView createDish(final MenuDishDto menuDishDto) {
    menuDishDto.setRecipe(menuDishDto.getRecipe().replaceAll(REGEXP_CRLF, EMPTY));
    MenuDish menuDish = dishService.convertToMenuDish(menuDishDto);
    dishService.save(menuDish);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_DISHES, dishService.findAll());
    modelAndView.setViewName(DISHES_DISHES);
    return modelAndView;
  }

  /**
   * Prepares the update a dish form.
   * 
   * @param dishId Dish identifier.
   * @return
   */
  @GetMapping("/dishes/update/{dishId}")
  public ModelAndView updateDish(@PathVariable(DISH_ID) final Integer dishId) {
    final MenuDish menuDish = dishService.findById(dishId);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(DISH, menuDish);
    modelAndView.addObject(ALL_DISHES, dishService.findAll());
    modelAndView.setViewName(DISHES_UPDATE_DISH);
    return modelAndView;
  }

  /**
   * Updates a dish.
   * 
   * @param dishId   Dish identifier.
   * @param menuDish Related menu.
   * @return
   */
  @PostMapping("/dishes/update/{dishId}")
  public ModelAndView updateDish(@PathVariable(DISH_ID) final Integer dishId,
      final MenuDishDto menuDishDto) {
    menuDishDto.setDishId(dishId);
    menuDishDto.setRecipe(menuDishDto.getRecipe().replaceAll(REGEXP_CRLF, EMPTY));
    MenuDish menuDish = dishService.convertToMenuDish(menuDishDto);
    dishService.save(menuDish);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_DISHES, dishService.findAll());
    modelAndView.setViewName(DISHES_DISHES);
    return modelAndView;
  }

  /**
   * Deletes a dish.
   * 
   * @param dishId Dish identifier.
   * @return
   */
  @GetMapping("/dishes/delete/{dishId}")
  public ModelAndView deleteDish(@PathVariable(DISH_ID) final Integer dishId) {
    final MenuDish menuDish = dishService.findById(dishId);
    dishService.delete(menuDish);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_DISHES, dishService.findAll());
    modelAndView.setViewName(DISHES_DISHES);
    return modelAndView;
  }
}
