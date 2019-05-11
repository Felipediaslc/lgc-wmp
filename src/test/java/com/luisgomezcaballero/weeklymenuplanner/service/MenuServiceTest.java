package com.luisgomezcaballero.weeklymenuplanner.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import com.luisgomezcaballero.weeklymenuplanner.repository.MenuLineRepository;
import com.luisgomezcaballero.weeklymenuplanner.repository.MenuRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Test class for the menu service.
 * 
 * @author Luis
 *
 */
public class MenuServiceTest {

  /**
   * Menu service.
   */
  @InjectMocks
  private transient MenuService menuService;

  /**
   * Menu line service.
   */
  @Mock
  private transient MenuLineService menuLineService;

  /**
   * Dish service.
   */
  @Mock
  private transient DishService dishService;

  /**
   * Menu repository mock.
   */
  @Mock
  private transient MenuRepository menuRepository;

  /**
   * Menu line repository mock.
   */
  @Mock
  private transient MenuLineRepository menuLineRepository;

  /**
   * Constructor.
   */
  public MenuServiceTest() {
    super();
  }

  /**
   * Set up for the mocks.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Constructor test.
   */
  @Test
  public void testMenuService() {
    assertNotNull(new MenuService(null, null, null));
  }

  /**
   * Find all test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindAll() throws Exception {
    when(menuRepository.findAll()).thenReturn(new ArrayList<>());
    menuService.findAll();
    verify(menuRepository).findAll();
  }

  /**
   * Find by identifier test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindById() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    when(menuRepository.findById(1)).thenReturn(Optional.of(menuHeader));
    menuService.findById(1);
    verify(menuRepository).findById(1);
  }

  /**
   * Delete test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testDelete() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    menuService.delete(menuHeader);
    verify(menuRepository).delete(menuHeader);
  }

  /**
   * Save test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testSave() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    menuService.save(menuHeader);
    verify(menuRepository).save(menuHeader);
  }

  /**
   * Get last dish order with order = 1 test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testGetLastDishOrderWith1() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    when(menuLineService.getLastDishOrder(menuHeader)).thenReturn(Integer.valueOf(1));
    menuService.getLastDishOrder(menuHeader);
    verify(menuLineService).getLastDishOrder(menuHeader);
  }

  /**
   * Get last dish order with order = null test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testGetLastDishOrderWithNull() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    when(menuLineService.getLastDishOrder(menuHeader)).thenReturn(null);
    menuService.getLastDishOrder(menuHeader);
    verify(menuLineService).getLastDishOrder(menuHeader);
  }

  /**
   * Auto-generate test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testAutogenerate() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    when(dishService.findAll()).thenReturn(Arrays.asList(new MenuDish(), new MenuDish()));
    menuService.autogenerate(menuHeader);
    verify(dishService, atLeastOnce()).findAll();
    verify(dishService, atLeastOnce()).findById(Mockito.anyInt());
    verify(menuLineService, atLeastOnce()).save(Mockito.any(MenuLine.class));
  }
}