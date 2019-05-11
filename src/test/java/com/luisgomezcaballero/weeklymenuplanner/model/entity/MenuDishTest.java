package com.luisgomezcaballero.weeklymenuplanner.model.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MenuDishTest {

  @Mock
  MenuDish menuDish;

  @Before
  public void setUp() {
    menuDish = new MenuDish();
  }

  @Test
  public void testMenuDish() {
    assertNotNull(new MenuDish());
  }

  @Test
  public void testGetDishId() throws Exception {
    assertNull(menuDish.getDishId());

  }

  @Test
  public void testGetName() throws Exception {
    assertNull(menuDish.getName());
  }

  @Test
  public void testGetIngredients() throws Exception {
    assertNull(menuDish.getIngredients());
  }

  @Test
  public void testGetRecipe() throws Exception {
    assertNull(menuDish.getRecipe());
  }

  @Test
  public void testSetDishId() throws Exception {
    menuDish.setDishId(null);
    assertNull(menuDish.getDishId());

  }

  @Test
  public void testSetName() throws Exception {
    menuDish.setName(null);
    assertNull(menuDish.getName());
  }

  @Test
  public void testSetIngredients() throws Exception {
    menuDish.setIngredients(null);
    assertNull(menuDish.getIngredients());

  }

  @Test
  public void testSetRecipe() throws Exception {
    menuDish.setRecipe(null);
    assertNull(menuDish.getRecipe());

  }

  @Test
  public void testEquals() throws Exception {
    MenuDish menu = new MenuDish();
    MenuDish otherMenu = new MenuDish();
    assertTrue(menu.equals(otherMenu));

  }

  @Test
  public void testCanEqual() throws Exception {
    MenuDish menu = new MenuDish();
    MenuDish otherMenu = new MenuDish();
    assertTrue(menu.canEqual(otherMenu));
  }

  @Test
  public void testHashCode() throws Exception {
    MenuDish menu = new MenuDish();
    assertNotNull(menu.hashCode());
  }

  @Test
  public void testToString() throws Exception {
    MenuDish menu = new MenuDish();
    assertNotNull(menu.toString());
  }
}