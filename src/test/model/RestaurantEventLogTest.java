package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantEventLogTest {
    private Restaurent bobFoodStand;
    private String restaurantName;
    private String cuisineType;
    private ArrayList<OrderForRestaurant> ordersList;
    private OrderForRestaurant jeff;
    private OrderForRestaurant mike;
    private OrderForRestaurant sam;
    private MenuItem friedChicken;
    private MenuItem burger;
    private ArrayList<MenuItem> foodOrdered;
    private ArrayList<String> allergies;
    private MenuForRestaurant menu;

    @BeforeEach
    void runBeforeTests(){
        restaurantName = "Bob's Food Stand";
        cuisineType = "American";
        ordersList = new ArrayList<OrderForRestaurant>();
        menu = new MenuForRestaurant();
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
        burger = new MenuItem("Burger", 20, "Dairy", 15);
        menu.addFoodItem(friedChicken);
        menu.addFoodItem(burger);
        bobFoodStand = new Restaurent(restaurantName, cuisineType, ordersList, menu);
        foodOrdered = new ArrayList<MenuItem>();
        allergies = new ArrayList<String>();
        foodOrdered.add(friedChicken);
        foodOrdered.add(burger);
        allergies.add("Dairy");
        jeff = new OrderForRestaurant("Jeff", foodOrdered, allergies);
        mike = new OrderForRestaurant("Mike", foodOrdered, allergies);
        sam = new OrderForRestaurant("Sam", foodOrdered, allergies);
        EventLog.getInstance().clear();
    }

    @Test
    void testSetNameEventLog() {
        bobFoodStand.setRestaurantName("burger");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("The Restaurant is now renamed to burger",
                iterator.next().getDescription());
    }

    @Test
    void testSetCuisineEventLog() {
        bobFoodStand.setCuisine("burger");
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("The restaurant's cuisine is now set to burger",
                iterator.next().getDescription());
    }

    @Test
    void testAddOrderEventLog() {
        bobFoodStand.addOrder(jeff);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("added order number 54 to restaurant's queue of orders",
                iterator.next().getDescription());
    }

    @Test
    void testRemoveOrderEventLog() {
        bobFoodStand.addOrder(mike);
        bobFoodStand.removeOrder(49);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("added order number 49 to restaurant's queue of orders", iterator.next().getDescription());
        assertEquals("removed order number 49 from restaurant's queue of orders",
                iterator.next().getDescription());
    }

    @Test
    void testFinishOrderEventLog() {
        bobFoodStand.addOrder(mike);
        bobFoodStand.finishOrder(mike);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("added order number 52 to restaurant's queue of orders", iterator.next().getDescription());
        assertEquals("finished making order number 52 in the restaurant's queue of orders",
                iterator.next().getDescription());
    }

    @Test
    void testPrioritizeOrderEventLog() {
        bobFoodStand.addOrder(mike);
        bobFoodStand.prioritizeOrder(mike);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("added order number 43 to restaurant's queue of orders", iterator.next().getDescription());
        assertEquals("prioritized order number 43 in the restaurant's queue of orders",
                iterator.next().getDescription());
    }

    @Test
    void testHandleSaveEventLog() {
        bobFoodStand.handleSaveRestaurant();
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("saved the restaurant to memory", iterator.next().getDescription());
    }

    @Test
    void testHandleLoadEventLog() {
        bobFoodStand.handleReadRestaurant(bobFoodStand);
        EventLog el = EventLog.getInstance();
        Iterator<Event> iterator = el.iterator();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertEquals("Restaurant from memory loaded into the application", iterator.next().getDescription());
    }
}
