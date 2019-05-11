package com.luisgomezcaballero.weeklymenuplanner.model.dto;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * This class represents the header of a menu.
 * 
 * @author Luis
 *
 */
@Data
public class MenuHeaderDto implements Serializable {
  
  private static final long serialVersionUID = 1L;
  /**
   * Menu identifier.
   */
  private Integer menuId;

  /**
   * Menu start date.
   */
  private Date startDate;

  /**
   * Link with menu lines.
   */
  private List<MenuLine> menuLines;

}
