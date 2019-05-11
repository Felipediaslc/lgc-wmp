package com.luisgomezcaballero.weeklymenuplanner.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import com.luisgomezcaballero.weeklymenuplanner.repository.DishRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for the dish service.
 * 
 * @author Luis
 *
 */
public class DishServiceTest {

  /**
   * Dish repository mock.
   */
  @Mock
  private transient DishRepository dishRepository;

  /**
   * Dish service.
   */
  @InjectMocks
  private transient DishService dishService;

  /**
   * Constructor.
   */
  public DishServiceTest() {
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
  public void testDishService() {
    assertNotNull(new DishService());
  }

  /**
   * Find all test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindAll() throws Exception {
    when(dishRepository.findAll()).thenReturn(new ArrayList<>());
    dishService.findAll();
    verify(dishRepository).findAll();
  }

  /**
   * Fin by identifier test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testFindById() throws Exception {
    MenuDish menuDish = new MenuDish();
    when(dishRepository.findById(1)).thenReturn(Optional.of(menuDish));
    dishService.findById(1);
    verify(dishRepository).findById(1);
  }

  /**
   * Delete test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testDelete() throws Exception {
    MenuDish menuDish = new MenuDish();
    dishService.delete(menuDish);
    verify(dishRepository).delete(menuDish);
  }

  /**
   * Save test.
   * 
   * @throws Exception Exception
   */
  @Test
  public void testSave() throws Exception {
    MenuDish menuDish = new MenuDish();
    dishService.save(menuDish);
    verify(dishRepository).save(menuDish);
  }
}