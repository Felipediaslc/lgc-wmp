package com.luisgomezcaballero.weeklymenuplanner.repository;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Menu line repository.
 * 
 * @author Luis
 *
 */
@Repository
public interface MenuLineRepository extends JpaRepository<MenuLine, Integer> {

  /**
   * Find menu line by its menu.
   * @param menu Menu.
   * @return
   */
  List<MenuLine> findByMenu(MenuHeader menu);

  /**
   * Find last order for all the menu lines of a menu. 
   * @param menuHeader Menu.
   * @return
   */
  @Query("SELECT max(dishOrder) FROM MenuLine ml WHERE ml.menu = :menuHeader")
  Integer findLastByOrderByDishOrder(MenuHeader menuHeader);

  /**
   * Find a menu line of a menu by its order (the order of the menu line).
   * @param menuHeader Menu.
   * @param dishOrder Order of the dish.
   * @return
   */
  MenuLine findByMenuAndDishOrder(MenuHeader menuHeader, Integer dishOrder);

}