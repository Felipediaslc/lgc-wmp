package com.luisgomezcaballero.weeklymenuplanner.model.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MenuHeaderTest {

  @Mock
  private MenuHeader menuHeader;

  @Before
  public void setUp() {
    menuHeader = new MenuHeader();
  }

  @Test
  public void testMenuHeader() {
    assertNotNull(new MenuHeader());
  }

  @Test
  public void testGetMenuId() throws Exception {
    assertNull(menuHeader.getMenuId());
  }

  @Test
  public void testGetStartDate() throws Exception {
    assertNull(menuHeader.getStartDate());
  }

  @Test
  public void testGetMenuLines() throws Exception {
    assertNull(menuHeader.getMenuLines());
  }

  @Test
  public void testSetMenuId() throws Exception {
    menuHeader.setMenuId(null);
    assertNull(menuHeader.getMenuId());

  }

  @Test
  public void testSetStartDate() throws Exception {
    menuHeader.setStartDate(null);
    assertNull(menuHeader.getStartDate());
  }

  @Test
  public void testSetMenuLines() throws Exception {
    menuHeader.setMenuLines(null);
    assertNull(menuHeader.getMenuLines());
  }

  @Test
  public void testEquals() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    MenuHeader otherMenuHeader = new MenuHeader();
    assertTrue(menuHeader.equals(otherMenuHeader));
  }

  @Test
  public void testCanEqual() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    MenuHeader otherMenuHeader = new MenuHeader();
    assertTrue(menuHeader.canEqual(otherMenuHeader));
  }

  @Test
  public void testHashCode() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    assertNotNull(menuHeader.hashCode());
  }

  @Test
  public void testToString() throws Exception {
    MenuHeader menuHeader = new MenuHeader();
    assertNotNull(menuHeader.toString());
  }
}