package com.luisgomezcaballero.weeklymenuplanner.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import com.luisgomezcaballero.weeklymenuplanner.service.MenuLineService;
import com.luisgomezcaballero.weeklymenuplanner.service.MenuService;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

public class MenuLineControllerTest {

  @Mock
  MenuLineService menuLineService;

  @Mock
  MenuService menuService;

  @InjectMocks
  MenuLineController menuLineController;

  StandaloneMockMvcBuilder standaloneMockMvcBuilder;

  MockMvc mockMcv;

  @Mock
  Map<String, Object> model;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(menuLineController);
    mockMcv = standaloneMockMvcBuilder.build();
  }

  @Test
  public void testMenuLines() throws Exception {
    when(menuService.findById(1)).thenReturn(new MenuHeader());
    this.mockMcv.perform(get("/menu/{menuId}/menuLines", 1L)).andExpect(status().isOk())
        .andExpect(forwardedUrl("menuLines/menuLines"));
  }

//  @Test
//  public void testCreateSignupFormErrors() throws Exception {
//
//    this.mockMvc
//        .perform(post("/create").param("email", "<error>").param("firstName", "<error>")
//            .param("lastName", "<error>"))
//        .andExpect(status().isOk()).andExpect(forwardedUrl(MenuLineController.PAGE_INDEX))
//        .andExpect(model().attributeHasFieldErrors("signupForm", "email"));
//  }
//  @Autowired
//  private WebApplicationContext webApplicationContext;
//
//  private MockMvc mockMvc;
//
//  @Before
//  public void setup() {
//    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//  }

//  private MenuLineController createTestSubject() {
//    return new MenuLineController(new MenuLineService(new MenuLineRepository()),
//        new MenuService(new MenuRepository(), new DishService(),
//            new MenuLineService(new MenuLineRepository())),
//        new DishService());
//  }

//  @Test
//  public void testMenuLines() throws Exception {
//    this.mockMvc.perform(get("/menu/1/menuLines")).andExpect(status().isOk())
//        .andExpect(view().name("/menu/1/menuLines"));
//  }

//  @MethodRef(name = "newMenuLine", signature = "(QInteger;)QModelAndView;")
//  @Test
//  public void testNewMenuLine() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.newMenuLine(menuId);
//  }
//
//  @MethodRef(name = "createMenuLine", signature = "(QInteger;QMenuLine;)QModelAndView;")
//  @Test
//  public void testCreateMenuLine() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    MenuLine menuLine = null;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.createMenuLine(menuId, menuLine);
//  }
//
//  @MethodRef(name = "updateMenuLine", signature = "(QInteger;QInteger;)QModelAndView;")
//  @Test
//  public void testUpdateMenuLine() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    Integer menuLineId = 0;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.updateMenuLine(menuId, menuLineId);
//  }
//
//  @MethodRef(name = "updateMenuLine", signature = "(QInteger;QInteger;QMenuLine;)QModelAndView;")
//  @Test
//  public void testUpdateMenuLine_1() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    Integer menuLineId = 0;
//    MenuLine menuLine = null;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.updateMenuLine(menuId, menuLineId, menuLine);
//  }
//
//  @MethodRef(name = "deleteMenuLine", signature = "(QInteger;QInteger;)QModelAndView;")
//  @Test
//  public void testDeleteMenuLine() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    Integer menuLineId = 0;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.deleteMenuLine(menuId, menuLineId);
//  }
//
//  @MethodRef(name = "getWeekdays", signature = "()QList<QString;>;")
//  @Test
//  public void testGetWeekdays() throws Exception {
//    MenuLineController testSubject;
//    List<String> result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = Whitebox.invokeMethod(testSubject, "getWeekdays");
//  }
//
//  @MethodRef(name = "getMealTypes", signature = "()QList<QString;>;")
//  @Test
//  public void testGetMealTypes() throws Exception {
//    MenuLineController testSubject;
//    List<String> result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = Whitebox.invokeMethod(testSubject, "getMealTypes");
//  }
//
//  @MethodRef(name = "moveUpMenuLine", signature = "(QInteger;QInteger;)QModelAndView;")
//  @Test
//  public void testMoveUpMenuLine() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    Integer dishOrder = 0;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.moveUpMenuLine(menuId, dishOrder);
//  }
//
//  @MethodRef(name = "moveDownMenuLine", signature = "(QInteger;QInteger;)QModelAndView;")
//  @Test
//  public void testMoveDownMenuLine() throws Exception {
//    MenuLineController testSubject;
//    Integer menuId = 0;
//    Integer dishOrder = 0;
//    ModelAndView result;
//
//    // default test
//    testSubject = createTestSubject();
//    result = testSubject.moveDownMenuLine(menuId, dishOrder);
//  }
}