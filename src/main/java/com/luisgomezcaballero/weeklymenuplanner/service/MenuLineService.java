package com.luisgomezcaballero.weeklymenuplanner.service;

import com.luisgomezcaballero.weeklymenuplanner.model.dto.MenuLineDto;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import com.luisgomezcaballero.weeklymenuplanner.repository.MenuLineRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for menu lines.
 * 
 * @author Luis
 *
 */
@Service
public class MenuLineService {

  /**
   * Repository for menu lines.
   */
  @Autowired
  private final MenuLineRepository menuLineRepo;

  /**
   * Constructor.
   * 
   * @param menuLineRepo Repository for menu lines.
   */
  public MenuLineService(final MenuLineRepository menuLineRepo) {
    super();
    this.menuLineRepo = menuLineRepo;
  }

  /**
   * Finds all menu lines.
   * 
   * @return
   */
  public List<MenuLine> findAll() {
    return this.menuLineRepo.findAll();
  }

  /**
   * Finds a menu line by its identifier.
   * 
   * @param identifier Menu line identifier.
   * @return
   */
  public MenuLine findById(final Integer identifier) {
    MenuLine menuLine = null;
    Optional<MenuLine> optionalValue = this.menuLineRepo.findById(identifier);

    if (optionalValue.isPresent()) {
      menuLine = optionalValue.get();
    }

    return menuLine;
  }

  /**
   * Deletes a menu line.
   * 
   * @param menuLine Menu line.
   */
  public void delete(final MenuLine menuLine) {
    menuLineRepo.delete(menuLine);
  }

  /**
   * Saves a menu line.
   * 
   * @param menuLine Menu line.
   */
  public void save(final MenuLine menuLine) {
    menuLineRepo.save(menuLine);
  }

  /**
   * Finds a menu line by its menu.
   * 
   * @param menuHeader Menu.
   * @return
   */
  public List<MenuLine> findByMenu(final MenuHeader menuHeader) {
    return menuLineRepo.findByMenu(menuHeader);
  }

  /**
   * Finds a menu line by its menu and order.
   * 
   * @param menuHeader Menu.
   * @param dishOrder  Order of the dish.
   * @return
   */
  public MenuLine findByMenuAndDishOrder(final MenuHeader menuHeader, final Integer dishOrder) {
    return menuLineRepo.findByMenuAndDishOrder(menuHeader, dishOrder);
  }

  /**
   * Returns the last order of the list of dishes of a menu.
   * 
   * @param menuHeader Menu.
   * @return
   */
  public Integer getLastDishOrder(final MenuHeader menuHeader) {
    return menuLineRepo.findLastByOrderByDishOrder(menuHeader);
  }

  /**
   * Converter.
   * 
   * @param menuLineDto Menu line DTO
   * @return
   */
  public MenuLine convertToMenuLine(MenuLineDto menuLineDto) {
    MenuLine menuLine = new MenuLine();

    menuLine.setDish(menuLineDto.getDish());
    menuLine.setDishOrder(menuLineDto.getDishOrder());
    menuLine.setMealType(menuLineDto.getMealType());
    menuLine.setMenu(menuLineDto.getMenu());
    menuLine.setMenuLineId(menuLineDto.getMenuLineId());
    menuLine.setWeekday(menuLineDto.getWeekday());

    return menuLine;
  }

}
