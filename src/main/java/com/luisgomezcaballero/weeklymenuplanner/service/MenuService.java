package com.luisgomezcaballero.weeklymenuplanner.service;

import com.luisgomezcaballero.weeklymenuplanner.model.dto.MenuHeaderDto;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import com.luisgomezcaballero.weeklymenuplanner.repository.MenuRepository;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for menus.
 * 
 * @author Luis
 *
 */
@Service
public class MenuService {

  private static final String ERROR = "Error";
  private static Logger logger = LoggerFactory.getLogger(MenuService.class);
  /**
   * Repository for menus.
   */
  @Autowired
  private final MenuRepository menuRepository;
  /**
   * Service for dishes.
   */
  @Autowired
  private final DishService dishService;

  /**
   * Service for menu lines.
   */
  @Autowired
  private final MenuLineService menuLineService;

  /**
   * Random number generator.
   */
  private Random random;

  /**
   * Constructor.
   * 
   * @param menuRepository  Repository for menus.
   * @param dishService     Service for dishes.
   * @param menuLineService Service for menu lines.
   */
  public MenuService(final MenuRepository menuRepository, final DishService dishService,
      final MenuLineService menuLineService) {
    super();
    this.menuRepository = menuRepository;
    this.dishService = dishService;
    this.menuLineService = menuLineService;
    try {
      random = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      logger.debug(ERROR, e);
    }
  }

  /**
   * Finds all menus.
   * 
   * @return
   */
  public List<MenuHeader> findAll() {
    return this.menuRepository.findAll();
  }

  /**
   * Finds a menu by its identifier.
   * 
   * @param identifier Identifier of the menu.
   * @return
   */
  public MenuHeader findById(final Integer identifier) {
    MenuHeader menuHeader = null;
    Optional<MenuHeader> optionalValue = this.menuRepository.findById(identifier);

    if (optionalValue.isPresent()) {
      menuHeader = optionalValue.get();
    }

    return menuHeader;
  }

  /**
   * Deletes a menu.
   * 
   * @param menuHeader Menu.
   */
  public void delete(final MenuHeader menuHeader) {
    menuRepository.delete(menuHeader);
  }

  /**
   * Saves a menu.
   * 
   * @param menuHeader Menu.
   */
  public void save(final MenuHeader menuHeader) {
    menuRepository.save(menuHeader);
  }

  /**
   * Returns the last order of a menu line in a menu.
   * 
   * @param menuHeader Menu.
   * @return
   */
  public int getLastDishOrder(final MenuHeader menuHeader) {
    final Integer result = menuLineService.getLastDishOrder(menuHeader);
    final Integer returnValue;
    if (null == result) {
      returnValue = 1;
    } else {
      returnValue = result + 1;
    }
    return returnValue;
  }

  /**
   * Auto-generates a full week menu.
   * 
   * @param menuHeader Menu.
   */
  public void autogenerate(final MenuHeader menuHeader) {
    Integer dishOrder = 1;
    Integer randomDish = null;
    MenuLine menuLine;
    List<MenuDish> allDishes = dishService.findAll();
    Integer allDishesSize = 0;

    if (null != allDishes) {
      allDishesSize = allDishes.size();
    }

    if (allDishesSize > 1) {
      // Generate weekdays
      for (int weekday = 1; weekday <= 7; weekday++) {
        // Generate mealTypes (1 = breakfast, 2 = lunch, 3 = dinner)
        for (int mealType = 2; mealType <= 3; mealType++) {
          // Generate random dish
          randomDish = (random.nextInt(allDishesSize - 1)) + 1;
          menuLine = new MenuLine(mealType, dishOrder++, menuHeader, weekday,
              dishService.findById(randomDish));
          menuLineService.save(menuLine);
        }
      }
    }
  }

  /**
   * Converter.
   * 
   * @param menuHeaderDto Menu header DTO
   * @return
   */
  public MenuHeader convertToMenuHeader(MenuHeaderDto menuHeaderDto) {
    MenuHeader menuHeader = new MenuHeader();

    menuHeader.setMenuId(menuHeaderDto.getMenuId());
    menuHeader.setMenuLines(menuHeaderDto.getMenuLines());
    menuHeader.setStartDate(menuHeaderDto.getStartDate());

    return menuHeader;
  }

}
