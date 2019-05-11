package com.luisgomezcaballero.weeklymenuplanner.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuLine;
import com.luisgomezcaballero.weeklymenuplanner.repository.MenuLineRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for the menu line service.
 * 
 * @author Luis
 *
 */
public class MenuLineServiceTest {

  /**
   * Menu line repository mock.
   */
  @Mock
  private transient MenuLineRepository menuLineRepository;

  /**
   * Menu line service.
   */
  @InjectMocks
  private transient MenuLineService menuLineService;

  /**
   * Constructor.
   */
  public MenuLineServiceTest() {
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
  public void testMenuLineService() {
    assertNotNull(new MenuLineService(null));
  }

  /**
   * Fin all test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindAll() throws Exception {
    when(menuLineRepository.findAll()).thenReturn(new ArrayList<>());
    menuLineService.findAll();
    verify(menuLineRepository).findAll();
  }

  /**
   * Find by identifier test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindById() throws Exception {
    MenuLine menuLine = new MenuLine();
    when(menuLineRepository.findById(1)).thenReturn(Optional.of(menuLine));
    menuLineService.findById(1);
    verify(menuLineRepository).findById(1);
  }

  /**
   * Delete test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testDelete() throws Exception {
    MenuLine menuLine = new MenuLine();
    menuLineService.delete(menuLine);
    verify(menuLineRepository).delete(menuLine);
  }

  /**
   * Save test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testSave() throws Exception {
    MenuLine menuLine = new MenuLine();
    menuLineService.save(menuLine);
    verify(menuLineRepository).save(menuLine);
  }

  /**
   * Find by menu test.
   * 
   * @throws Exception
   */
  @Test
  public void testFindByMenu() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    when(menuLineRepository.findByMenu(menuHeader)).thenReturn(new ArrayList<>());
    menuLineService.findByMenu(menuHeader);
    verify(menuLineRepository).findByMenu(menuHeader);

  }

  /**
   * Find by menu and dish order test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindByMenuAndDishOrder() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    Integer dishOrder = 1;
    MenuLine menuLine = new MenuLine();
    when(menuLineRepository.findByMenuAndDishOrder(menuHeader, dishOrder)).thenReturn(menuLine);
    menuLineService.findByMenuAndDishOrder(menuHeader, dishOrder);
    verify(menuLineRepository).findByMenuAndDishOrder(menuHeader, dishOrder);
  }

  /**
   * Get last dish order test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testGetLastDishOrder() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    when(menuLineRepository.findLastByOrderByDishOrder(menuHeader)).thenReturn(1);
    menuLineService.getLastDishOrder(menuHeader);
    verify(menuLineRepository).findLastByOrderByDishOrder(menuHeader);
  }
}