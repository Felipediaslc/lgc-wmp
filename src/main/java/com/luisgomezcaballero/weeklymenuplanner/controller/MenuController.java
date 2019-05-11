package com.luisgomezcaballero.weeklymenuplanner.controller;

import com.luisgomezcaballero.weeklymenuplanner.model.dto.MenuHeaderDto;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import com.luisgomezcaballero.weeklymenuplanner.service.MenuLineService;
import com.luisgomezcaballero.weeklymenuplanner.service.MenuService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Menu controller.
 * 
 * @author Luis
 *
 */
@Controller
public class MenuController {

  /**
   * Constant.
   */
  private static final String MENUS_UPDATE_MENU = "menus/updateMenu";
  /**
   * Constant.
   */
  private static final String MENUS_MENUS = "menus/menus";
  /**
   * Constant.
   */
  private static final String MENU_ID = "menuId";
  /**
   * Constant.
   */
  private static final String MENU_LINES = "menuLines";
  /**
   * Constant.
   */
  private static final String WEEKDAYS = "weekdays";
  /**
   * Constant.
   */
  private static final String MEAL_TYPES = "mealTypes";
  /**
   * Constant.
   */
  private static final String ALL_MENUS = "allMenus";
  /**
   * Constant.
   */
  private static final String MENUS_NEW_MENU = "menus/newMenu";
  /**
   * Constant.
   */
  private static final String MENU = "menu";

  /**
   * Menu service.
   */
  @Autowired
  private final MenuService menuService;

  /**
   * Menu line service.
   */
  @Autowired
  private final MenuLineService menuLineService;

  /**
   * Constructor.
   * 
   * @param menuService Menu service
   */
  public MenuController(final MenuService menuService, final MenuLineService menuLineService) {
    super();
    this.menuService = menuService;
    this.menuLineService = menuLineService;
  }

  /**
   * Home mapping.
   * 
   * @return
   */
  @GetMapping("/")
  public ModelAndView home() {
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_MENUS, menuService.findAll());
    modelAndView.setViewName(MENUS_MENUS);
    return modelAndView;
  }

  /**
   * New menu.
   * 
   * @return
   */
  @GetMapping("/menu/create")
  public ModelAndView newMenu() {
    final ModelAndView modelAndView = new ModelAndView();
    final MenuHeader menuHeader = new MenuHeader();
    menuHeader.setStartDate(new Date());
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.setViewName(MENUS_NEW_MENU);
    return modelAndView;
  }

  /**
   * Create menu.
   * 
   * @param menuHeader Menu header
   * @return
   */
  @PostMapping("/menu/create")
  public ModelAndView createMenu(final MenuHeaderDto menuHeaderDto) {
    MenuHeader menuHeader = menuService.convertToMenuHeader(menuHeaderDto);
    menuService.save(menuHeader);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  /**
   * Auto-generate.
   * 
   * @param menuHeader Menu header
   * @return
   */
  @GetMapping("/menu/autogenerate/{startDate}")
  public ModelAndView autogenerate(@PathVariable("startDate") final Date startDate,
      final MenuHeaderDto menuHeaderDto) {

    menuHeaderDto.setStartDate(startDate);

    MenuHeader menuHeader = menuService.convertToMenuHeader(menuHeaderDto);

    menuService.save(menuHeader);

    menuService.autogenerate(menuHeader);

    final List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  /**
   * Update menu.
   * 
   * @param menuId Menu id
   * @return
   */
  @GetMapping("/menu/{menuId}/update")
  public ModelAndView updateMenu(@PathVariable(MENU_ID) final Integer menuId) {
    final MenuHeader menuHeader = menuService.findById(menuId);
    final List<MenuLine> menuLines = menuLineService.findByMenu(menuHeader);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(MENU, menuHeader);
    modelAndView.addObject(MENU_LINES, menuLines);
    modelAndView.addObject(MEAL_TYPES, getMealTypes());
    modelAndView.addObject(WEEKDAYS, getWeekdays());
    modelAndView.setViewName(MENUS_UPDATE_MENU);
    return modelAndView;
  }

  /**
   * Update menu.
   * 
   * @param menuId     Menu id
   * @param menuHeader Menu header
   * @return
   */
  @PostMapping("/menu/{menuId}/update")
  public ModelAndView updateMenu(@PathVariable(MENU_ID) final Integer menuId,
      final MenuHeaderDto menuHeaderDto) {
    menuHeaderDto.setMenuId(menuId);

    MenuHeader menuHeader = menuService.convertToMenuHeader(menuHeaderDto);

    menuService.save(menuHeader);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_MENUS, menuService.findAll());
    modelAndView.setViewName(MENUS_MENUS);
    return modelAndView;
  }

  /**
   * Delete menu.
   * 
   * @param menuId Menu id
   * @return
   */
  @GetMapping("/menu/{menuId}/delete")
  public ModelAndView deleteMenu(@PathVariable(MENU_ID) final Integer menuId) {
    final MenuHeader menuHeader = menuService.findById(menuId);
    menuService.delete(menuHeader);
    final ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject(ALL_MENUS, menuService.findAll());
    modelAndView.setViewName(MENUS_MENUS);
    return modelAndView;
  }

  private List<String> getWeekdays() {
    return Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
  }

  private List<String> getMealTypes() {
    return Arrays.asList("Desayuno", "Comida", "Cena");
  }

}
