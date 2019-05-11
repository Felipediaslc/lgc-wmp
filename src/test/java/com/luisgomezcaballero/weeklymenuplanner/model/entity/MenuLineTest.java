package com.luisgomezcaballero.weeklymenuplanner.model.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MenuLineTest {

  @Mock
  private MenuLine menuLine;

  @Before
  public void setUp() {
    menuLine = new MenuLine();
  }

  @Test
  public void testMenuLine() {
    assertNotNull(new MenuLine());
  }

  @Test
  public void testCompareTo() throws Exception {
    MenuLine menuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    MenuLine otherMenuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    assertNotNull(menuLine.compareTo(otherMenuLine));
  }

  @Test
  public void testGetMenuLineId() throws Exception {
    assertNull(menuLine.getMenuLineId());
  }

  @Test
  public void testGetMealType() throws Exception {
    assertNull(menuLine.getMealType());
  }

  @Test
  public void testGetDishOrder() throws Exception {
    assertNull(menuLine.getDishOrder());
  }

  @Test
  public void testGetMenu() throws Exception {
    assertNull(menuLine.getMenu());
  }

  @Test
  public void testGetWeekday() throws Exception {
    assertNull(menuLine.getWeekday());
  }

  @Test
  public void testGetDish() throws Exception {
    assertNull(menuLine.getDish());
  }

  @Test
  public void testSetMenuLineId() throws Exception {
    menuLine.setMenuLineId(null);
    assertNull(menuLine.getMenuLineId());

  }

  @Test
  public void testSetMealType() throws Exception {
    menuLine.setMealType(null);
    assertNull(menuLine.getMealType());
  }

  @Test
  public void testSetDishOrder() throws Exception {
    menuLine.setDishOrder(null);
    assertNull(menuLine.getDishOrder());

  }

  @Test
  public void testSetMenu() throws Exception {
    menuLine.setMenu(null);
    assertNull(menuLine.getMenu());

  }

  @Test
  public void testsetWeekday() throws Exception {
    menuLine.setWeekday(null);
    assertNull(menuLine.getWeekday());
  }

  @Test
  public void testsetDish() throws Exception {
    menuLine.setDish(null);
    assertNull(menuLine.getDish());
  }

  @Test
  public void testSetDish() throws Exception {
    menuLine.setDish(null);
    assertNull(menuLine.getDish());

  }

  @Test
  public void testEquals() throws Exception {
    MenuLine menuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    MenuLine otherMenuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    assertTrue(menuLine.equals(otherMenuLine));
  }

  @Test
  public void testCanEqual() throws Exception {
    MenuLine menuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    MenuLine otherMenuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    assertTrue(menuLine.canEqual(otherMenuLine));
  }

  @Test
  public void testHashCode() throws Exception {
    MenuLine menuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    assertNotNull(menuLine.hashCode());
  }

  @Test
  public void testToString() throws Exception {
    MenuLine menuLine = new MenuLine(null, 1, new MenuHeader(), null, new MenuDish());
    assertNotNull(menuLine.toString());
  }
}