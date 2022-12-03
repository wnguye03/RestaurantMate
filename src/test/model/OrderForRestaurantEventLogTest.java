package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderForRestaurantEventLogTest {
    private OrderForRestaurant jeff;
    private MenuItem friedChicken;
    private MenuItem burger;
    private ArrayList<MenuItem> foodOrdered;
    private ArrayList<String> allergies;

    @BeforeEach
    void runBeforeTests(){
        EventLog.getInstance().clear();
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
        burger = new MenuItem("Burger", 20, "Dairy", 15);
        foodOrdered = new ArrayList<MenuItem>();
        allergies = new ArrayList<String>();
        foodOrdered.add(friedChicken);
        foodOrdered.add(burger);
        allergies.add("Dairy");
        jeff = new OrderForRestaurant("Jeff", foodOrdered, allergies);
    }

    @Test
    void testAddItemToOrderEventLog() {
        jeff.addItemToOrder(burger);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("added food item Burger to order number 70", iterator.next().getDescription());
    }


    @Test
    void testRemovingItemToOrderEventLog() {
        jeff.removeItemToOrder("Burger");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("removed food item Burger from order number 69", iterator.next().getDescription());
    }

    @Test
    void testAddingAllergyToOrderEventLog() {
        jeff.addAllergy("gluten");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("added allergy gluten to order number 68", iterator.next().getDescription());
    }

    @Test
    void testRemovingAllergyToOrderEventLog() {
        jeff.removeAllergy("Dairy");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("removed allergy Dairy from order number 71", iterator.next().getDescription());
    }
}
