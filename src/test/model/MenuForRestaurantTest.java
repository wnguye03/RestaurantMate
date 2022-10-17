package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MenuForRestaurantTest {
    private MenuForRestaurant menu;
    private MenuItem friedChicken;
    private MenuItem burger;

    @BeforeEach
    void runBeforeTests(){
        menu = new MenuForRestaurant();
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
        burger = new MenuItem("Burger", 20, "Dairy", 15);
    }

    @Test
    void constructorTest() {
        assertEquals(0, menu.menuSize());
    }

    @Test
    void addFoodItemNotInMenuTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));
    }

    @Test
    void addFoodItemInMenuAlreadyTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));


        assertFalse(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));
    }

    @Test
    void getFoodItemWithItemInMenuTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));
    }

    @Test
    void getFoodItemWithItemNotInMenuTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertNull(menu.getFoodItem("Burger"));
    }

    @Test
    void deleteItemOnTheMenuThFromMenuTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));

        assertTrue(menu.deleteFoodItemFromMenu("Fried Chicken"));
        assertEquals(0, menu.menuSize());
    }

    @Test
    void deleteItemNotOnTheMenuThFromMenuTest() {
        assertEquals(0, menu.menuSize());
        assertFalse(menu.deleteFoodItemFromMenu("Fried Chicken"));
    }

    @Test
    void getAllMenuItemsWithItemsOnMenuTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));

        assertEquals("Fried Chicken 40 ", menu.getAllMenuItems());
    }

    @Test
    void getAllMenuItemsWithItemsNotOnMenuTest() {
        assertEquals("", menu.getAllMenuItems());
    }

    @Test
    void clearMenuTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());
        assertEquals(friedChicken, menu.getFoodItem("Fried Chicken"));

        assertTrue(menu.addFoodItem(burger));
        assertEquals(2, menu.menuSize());
        assertEquals(burger, menu.getFoodItem("Burger"));

        menu.clearMenu();

        assertEquals(0, menu.menuSize());

    }

    @Test
    void menuSizeTest() {
        assertEquals(0, menu.menuSize());
        assertTrue(menu.addFoodItem(friedChicken));
        assertEquals(1, menu.menuSize());

        assertTrue(menu.addFoodItem(burger));
        assertEquals(2, menu.menuSize());
    }

}

