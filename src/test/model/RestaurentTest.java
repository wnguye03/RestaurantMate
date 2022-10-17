package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurentTest {
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


    }

    @Test
    void constructorTest() {
        assertEquals("Bob's Food Stand", bobFoodStand.getRestaurantName());
        assertEquals("American", bobFoodStand.getCuisine());
        assertEquals(0, bobFoodStand.getCurrentOrders().size());
        assertEquals(2, bobFoodStand.getMenu().menuSize());
        assertEquals(friedChicken, bobFoodStand.getMenu().getFoodItem("Fried Chicken"));
        assertEquals(burger, bobFoodStand.getMenu().getFoodItem("Burger"));
    }

    @Test
    void setRestaurantNameTest() {
        bobFoodStand.setRestaurantName("mike's food stand");
        assertEquals("mike's food stand", bobFoodStand.getRestaurantName());
    }

    @Test
    void setCuisineTypeTest() {
        bobFoodStand.setCuisine("canadian");
        assertEquals("canadian", bobFoodStand.getCuisine());
    }

    @Test
    void addOrderNotAlreadyInCurrentOrdersTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 24 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));
    }

    @Test
    void addOrderAlreadyInCurrentOrdersTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 30 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

        assertEquals(false, bobFoodStand.addOrder(jeff));
    }

    @Test
    void getSingleOrderWithOrderInQueueTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 15 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));
    }

    @Test
    void getSingleOrderWithOrderNotInQueueTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertNull(bobFoodStand.getSingleOrder(2));
    }

    @Test
    void removeOrderAlreadyInCurrentOrdersTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 6 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

        assertEquals(true, bobFoodStand.removeOrder(6));
        assertEquals(0, bobFoodStand.getCurrentOrders().size());
    }

    @Test
    void removeOrderNotAlreadyInCurrentOrdersTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 27 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

        assertEquals(false, bobFoodStand.removeOrder(2));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 27 name of customer: Jeff allergy [Fried Chicken, Burger] allergy [Dairy] "
                + "time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));
    }

    @Test
    void finishOrderTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 3 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

        assertTrue(bobFoodStand.finishOrder(jeff));
        assertEquals(0, bobFoodStand.getCurrentOrders().size());
    }

    @Test
    void prioritizeOrderTest() {
        assertEquals(true, bobFoodStand.addOrder(jeff));
        assertEquals(1, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 12 name of customer: Jeff allergy [Fried Chicken, Burger] " +
                "allergy [Dairy] time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

        assertEquals(true, bobFoodStand.addOrder(mike));
        assertEquals(2, bobFoodStand.getCurrentOrders().size());
        assertEquals("order ID: 13 name of customer: Mike allergy [Fried Chicken, Burger] allergy [Dairy] "
                + "time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(1));

        assertTrue(bobFoodStand.prioritizeOrder(mike));

        assertEquals("order ID: 13 name of customer: Mike allergy [Fried Chicken, Burger] allergy [Dairy] "
                + "time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

        assertTrue(bobFoodStand.prioritizeOrder(sam));
        assertEquals("order ID: 14 name of customer: Sam allergy [Fried Chicken, Burger] allergy [Dairy] " +
                "time to make 25 price: 60", bobFoodStand.getCurrentOrders().get(0));

    }


}