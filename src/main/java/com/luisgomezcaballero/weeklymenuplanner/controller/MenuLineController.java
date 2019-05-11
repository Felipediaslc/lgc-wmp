package com.luisgomezcaballero.weeklymenuplanner.controller;

import com.luisgomezcaballero.weeklymenuplanner.model.dto.MenuLineDto;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import com.luisgomezcaballero.weeklymenuplanner.service.DishService;
import com.luisgomezcaballero.weeklymenuplanner.service.MenuLineService;
import com.luisgomezcaballero.weeklymenuplanner.service.MenuService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for menu lines.
 * 
 * @author Luis
 *
 */
@Controller
public class MenuLineController {

  /**
   * Constant.
   */
  private static final String DISH_ORDER = "dishOrder";

  /**
   * Constant.
   */
  private static final String MENU_LINES_UPDATE_MENU_LINE = "menuLines/updateMenuLine";

  /**
   * Constant.
   */
  private static final String MENU_LINE_ID = "menuLineId";

  /**
   * Constant.
   */
  private static final String MENU_LINES_NEW_MENU_LINE = "menuLines/newMenuLine";

  /**
   * Constant.
   */
  private static final String DISHES = "dishes";

  /**
   * Constant.
   */
  private static final String MENU_LINE = "menuLine";

  /**
   * Constant.
   */
  private static final String MENU_LINES_MENU_LINES = "menuLines/menuLines";

  /**
   * Constant.
   */
  private static final String ALL_MENU_LINES_BY_MENU_ID = "allMenuLinesByMenuId";

  /**
   * Menu lines string.
   */
  private static final String MENU_LINES = "menuLines";

  /**
   * URL for menu updates.
   */
  private static final String MENUS_UPDATE_MENU = "menus/updateMenu";

  /**
   * Weekdays.
   */
  private static final String WEEKDAYS = "weekdays";

  /**
   * Types of meals.
   */
  private static final String MEAL_TYPES = "mealTypes";

  /**
   * Menu string.
   */
  private static final String MENU = "menu";

  /**
   * Menu identifier.
   */
  private static final String MENU_ID = "menuId";

  /**
   * Service for lines of menu.
   */
  @Autowired
  private final MenuLineService menuLineService;

  /**
   * Service for menus.
   */
  @Autowired
  private final MenuService menuService;

  /**
   * Service for dishes.
   */
  @Autowired
  private final DishService dishService;

  /**
   * Constructor.
   */
  public MenuLineController(final MenuLineService menuLineService, final MenuService menuService,
      final DishService dishService) {
    super();
    this.menuLineService = menuLineService;
    this.menuService = menuService;
    this.dishService = dishService;
  }

  /**
   * Menu lines for a specific menu.
   * 
   * @param menuId Menu identifier.
   * @return
   */
  @GetMapping("/menu/{menuId}/menuLines")
  public ModelAndView menuLines(@PathVariable(MENU_ID) final Integer menuId) {
    final ModelAndView modelAndView = new ModelAndView();
    final MenuHeader menuHeader = menuService.findById(menuId);
    modelAndView.addObject(ALL_MENU_LINES_BY_MENU_ID, menuLineService.findByMenu(menuHeader));
    modelAndView.setViewName(MENU_LINES_MENU_LINES);
    return modelAndView;
  }

  /**
   * Form to create a menu.
   * 
   * @param menuId Identifier of the menu.
   * @return
   */
  @GetMapping("/menu/{menuId}/menuLine/create")
  public ModelAndView newMenuLine(@PathVariable(MENU_ID) final Integer menuId) {
    final ModelAndView modelAndView = new ModelAndView();
    final MenuLine menuLine = new MenuLine();
    final MenuHeader menuHeader = menuService.findById(menuId);
    menuLine.setMenu(menuHeader);
    modelAndView.addObject(MENU_LINE, menuLine);
    modelAndView.addObject(DISHES, dishService.findAll());
    modelAndView.setViewName(MENU_LINES_NEW_MENU_LINE);
    return modelAndView;
  }

  /**
   * Creation of a menu.
   * 
   * @param menuId   Identifier of the menu.
   * @param menuLine Menu line.
   * @return
   */
  @PostMapping("/menu/{menuId}/menuLine/create")
  public ModelAndView createMenuLine(@PathVariable(MENU_ID) final Integer menuId,
      final MenuLineDto menuLineDto) {

    final MenuHeader menuHeader = menuService.findById(menuId);
    menuLineDto.setMenu(menuHeader);
    menuLineDto.setDishOrder(menuService.getLastDishOrder(menuHeader));
    MenuLine menuLine = menuLineService.convertToMenuLine(menuLineDto);
    menuLineService.save(menuLine);
    final ModelAndView modelAndView = new ModelAndView();
    final List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    Collections.sort(menuLines);
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  /**
   * Form to update a menu.
   * 
   * @param menuId     Identifier of the menu.
   * @param menuLineId Identifier of the menu line.
   * @return
   */
  @GetMapping("/menu/{menuId}/menuLine/{menuLineId}/update")
  public ModelAndView updateMenuLine(@PathVariable(MENU_ID) final Integer menuId,
      @PathVariable(MENU_LINE_ID) final Integer menuLineId) {
    final MenuLine menuLine = menuLineService.findById(menuLineId);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU_LINE, menuLine);
    modelAndView.addObject(DISHES, dishService.findAll());
    modelAndView.setViewName(MENU_LINES_UPDATE_MENU_LINE);
    return modelAndView;
  }

  /**
   * Update of a menu.
   * 
   * @param menuId     Identifier of the menu.
   * @param menuLineId Identifier of the menu line.
   * @param menuLine   Menu line.
   * @return
   */
  @PostMapping("/menu/{menuId}/menuLine/{menuLineId}/update")
  public ModelAndView updateMenuLine(@PathVariable(MENU_ID) final Integer menuId,
      @PathVariable(MENU_ID) final Integer menuLineId, final MenuLineDto menuLineDto) {
    final MenuHeader menuHeader = menuService.findById(menuId);
    final MenuLine auxMenuLine = menuLineService.findById(menuLineDto.getMenuLineId());
    menuLineDto.setMenu(menuHeader);
    menuLineDto.setDishOrder(auxMenuLine.getDishOrder());

    MenuLine menuLine = menuLineService.convertToMenuLine(menuLineDto);
    
    menuLineService.save(menuLine);
    final ModelAndView modelAndView = new ModelAndView();
    final List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    Collections.sort(menuLines);
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  /**
   * Deletion of a menu.
   * 
   * @param menuLineId Identifier of the menu line.
   * @return
   */
  @GetMapping("/menu/{menuId}/menuLine/{menuLineId}/delete")
  public ModelAndView deleteMenuLine(@PathVariable("menuId") final Integer menuId,
      @PathVariable(MENU_LINE_ID) final Integer menuLineId) {
    final MenuHeader menuHeader = menuService.findById(menuId);
    List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    final MenuLine menuLine = menuLineService.findById(menuLineId);

    Collections.sort(menuLines);
    if (menuLine.getDishOrder() != menuLines.size()) {
      // Reorganize dishOrder in the other menu lines.
      for (int position = menuLine.getDishOrder(); position < menuLines.size(); position++) {
        menuLines.get(position).setDishOrder(position);
      }
    }

    menuLineService.delete(menuLine);
    menuLines = menuLineService.findByMenu(menuHeader);

    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  private List<String> getWeekdays() {
    return Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
  }

  private List<String> getMealTypes() {
    return Arrays.asList("Desayuno", "Comida", "Cena");
  }

  /**
   * Move the menu line one position up.
   * 
   * @param menuId    Menu identifier.
   * @param dishOrder Order of the dish.
   * @return
   */
  @GetMapping("/menu/{menuId}/menuLine/{dishOrder}/moveUp")
  public ModelAndView moveUpMenuLine(@PathVariable(MENU_ID) final Integer menuId,
      @PathVariable(DISH_ORDER) final Integer dishOrder) {
    final MenuHeader menuHeader = menuService.findById(menuId);
    final List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    Collections.sort(menuLines);

    final MenuLine menuLine = menuLineService.findByMenuAndDishOrder(menuHeader, dishOrder);
    final MenuLine auxMenuLine = menuLineService.findByMenuAndDishOrder(menuHeader, dishOrder - 1);
    menuLine.setDishOrder(0);
    menuLineService.save(menuLine);
    auxMenuLine.setDishOrder(dishOrder);
    menuLineService.save(auxMenuLine);
    menuLine.setDishOrder(dishOrder - 1);
    menuLineService.save(menuLine);
    Collections.sort(menuLines);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  /**
   * Move the menu line one position down.
   * 
   * @param menuId    Menu identifier.
   * @param dishOrder Order of the dish.
   * @return
   */
  @GetMapping("/menu/{menuId}/menuLine/{dishOrder}/moveDown")
  public ModelAndView moveDownMenuLine(@PathVariable(MENU_ID) final Integer menuId,
      @PathVariable(DISH_ORDER) final Integer dishOrder) {
    final MenuHeader menuHeader = menuService.findById(menuId);
    final List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    Collections.sort(menuLines);

    final MenuLine menuLine = menuLineService.findByMenuAndDishOrder(menuHeader, dishOrder);
    final MenuLine auxMenuLine = menuLineService.findByMenuAndDishOrder(menuHeader, dishOrder + 1);
    menuLine.setDishOrder(0);
    menuLineService.save(menuLine);
    auxMenuLine.setDishOrder(dishOrder);
    menuLineService.save(auxMenuLine);
    menuLine.setDishOrder(dishOrder + 1);
    menuLineService.save(menuLine);
    Collections.sort(menuLines);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }
}
