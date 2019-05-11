package com.luisgomezcaballero.weeklymenuplanner.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * This class represents the header of a menu.
 * 
 * @author Luis
 *
 */
@Data
@Entity
@Table(name = "wmp_menu")
public class MenuHeader implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * Menu identifier.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer menuId;

  /**
   * Menu start date.
   */
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date startDate;

  /**
   * Link with menu lines.
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
  private List<MenuLine> menuLines;

}
