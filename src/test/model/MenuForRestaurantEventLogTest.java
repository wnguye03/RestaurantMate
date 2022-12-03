package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class MenuForRestaurantEventLogTest {
    private MenuForRestaurant menu;
    private MenuItem friedChicken;
    private MenuItem burger;

    @BeforeEach
    void runBeforeTests(){
        EventLog.getInstance().clear();
        menu = new MenuForRestaurant();
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
        burger = new MenuItem("Burger", 20, "Dairy", 15);
    }


    @Test
    void testEventForAddingItem() {
        menu.addFoodItem(burger);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("New menu item Burger has been added", iterator.next().getDescription());
    }

    @Test
    void testEventForAddingMultipleItem() {
        menu.addFoodItem(burger);
        menu.addFoodItem(friedChicken);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("New menu item Burger has been added", iterator.next().getDescription());
        assertEquals("New menu item Fried Chicken has been added", iterator.next().getDescription());
    }

    @Test
    void testEventForDeletingItem() {
        menu.addFoodItem(burger);
        menu.deleteFoodItemFromMenu("Burger");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("New menu item Burger has been added", iterator.next().getDescription());
        assertEquals("Menu Item Burger has been removed", iterator.next().getDescription());
    }

    @Test
    void testEventForDeletingMultipleItem() {
        menu.addFoodItem(burger);
        menu.addFoodItem(friedChicken);
        menu.deleteFoodItemFromMenu("Burger");
        menu.deleteFoodItemFromMenu("Fried Chicken");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("New menu item Burger has been added", iterator.next().getDescription());
        assertEquals("New menu item Fried Chicken has been added", iterator.next().getDescription());
        assertEquals("Menu Item Burger has been removed", iterator.next().getDescription());
        assertEquals("Menu Item Fried Chicken has been removed", iterator.next().getDescription());
    }

    @Test
    void testEventClearMenu() {
        menu.addFoodItem(burger);
        menu.clearMenu();
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("New menu item Burger has been added", iterator.next().getDescription());
        assertEquals("Menu has been cleared", iterator.next().getDescription());
    }
}
