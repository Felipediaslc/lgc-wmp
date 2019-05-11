package com.luisgomezcaballero.weeklymenuplanner.service;

import com.luisgomezcaballero.weeklymenuplanner.model.dto.MenuDishDto;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import com.luisgomezcaballero.weeklymenuplanner.repository.DishRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for dishes.
 * 
 * @author Luis
 *
 */
@Service
public class DishService {

  /**
   * Repository for dishes.
   */
  @Autowired
  private DishRepository dishRepository;

  /**
   * Simple constructor.
   */
  public DishService() {
    super();
  }

  /**
   * Constructor.
   * 
   * @param dishRepository Repository for dishes-
   */
  public DishService(final DishRepository dishRepository) {
    super();
    this.dishRepository = dishRepository;
  }

  /**
   * Returns all dishes.
   * 
   * @return
   */
  public List<MenuDish> findAll() {
    return this.dishRepository.findAll();
  }

  /**
   * Finds a dish by its identifier.
   * 
   * @param identifier Dish identifier.
   * @return
   */
  public MenuDish findById(final Integer identifier) {
    MenuDish menuDish = null;
    Optional<MenuDish> optionalValue = this.dishRepository.findById(identifier);

    if (optionalValue.isPresent()) {
      menuDish = optionalValue.get();
    }

    return menuDish;
  }

  /**
   * Deletes a dish.
   * 
   * @param menuDish Menu of the dish.
   */
  public void delete(final MenuDish menuDish) {
    dishRepository.delete(menuDish);
  }

  /**
   * Saves a dish.
   * 
   * @param menuDish Menu of the dish.
   */
  public void save(final MenuDish menuDish) {
    dishRepository.save(menuDish);
  }

  /**
   * Converter.
   * 
   * @param menuDishDto Menu dish DTO
   * @return
   */
  public MenuDish convertToMenuDish(MenuDishDto menuDishDto) {
    MenuDish menuDish = new MenuDish();

    menuDish.setDishId(menuDishDto.getDishId());
    menuDish.setIngredients(menuDishDto.getIngredients());
    menuDish.setName(menuDishDto.getName());
    menuDish.setRecipe(menuDishDto.getRecipe());

    return menuDish;
  }
}
