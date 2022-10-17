package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class OrderForRestaurantTest {
    private OrderForRestaurant jeff;
    private MenuItem friedChicken;
    private MenuItem burger;
    private ArrayList<MenuItem> foodOrdered;
    private ArrayList<String> allergies;

    @BeforeEach
    void runBeforeTests(){
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
    void constructorTest() {
        assertEquals(33, jeff.getCustomerID());
        assertEquals("Jeff", jeff.getCustomerName());
        assertEquals(2, jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(0));
        assertEquals(1, jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));
    }

    @Test
    void setCustomerNameTest() {
        jeff.setCustomerName("mike");
        assertEquals("mike", jeff.getCustomerName());
    }

    @Test
    void getTotalPriceTest() {
        assertEquals(2, jeff.getListOfFoodOrdered().size());
        assertEquals(60,jeff.getTotalPrice());
    }

    @Test
    void addItemToOrderTest() {
        assertEquals(2, jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(0));
        assertEquals(burger, jeff.getListOfFoodOrdered().get(1));
        jeff.addItemToOrder(friedChicken);
        assertEquals(3, jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(2));
    }

    @Test
    void removeItemAlreadyInOrderFromOrderTest() {
        assertEquals(2, jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(0));
        assertEquals(burger, jeff.getListOfFoodOrdered().get(1));

        assertTrue(jeff.removeItemToOrder("Fried Chicken"));
        assertEquals(1,jeff.getListOfFoodOrdered().size());
        assertEquals(burger, jeff.getListOfFoodOrdered().get(0));
    }

    @Test
    void removeItemNotInOrderFromOrderTest() {
        assertEquals(2, jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(0));
        assertEquals(burger, jeff.getListOfFoodOrdered().get(1));

        assertFalse(jeff.removeItemToOrder("Hot Dog"));
        assertEquals(2,jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(0));
        assertEquals(burger, jeff.getListOfFoodOrdered().get(1));
    }

    @Test
    void addAllergyNotAlreadyInListTest() {
        assertEquals(1,jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));

        assertTrue(jeff.addAllergy("Peanut"));
        assertEquals(2, jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));
        assertEquals("Peanut", jeff.getCustomerAllergies().get(1));
    }

    @Test
    void addAllergyAlreadyInListTest() {
        assertEquals(1,jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));

        assertFalse(jeff.addAllergy("Dairy"));
        assertEquals(1, jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));
    }

    @Test
    void removeAllergyAlreadyInListTest() {
        assertEquals(1,jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));

        assertTrue(jeff.removeAllergy("Dairy"));
        assertEquals(0, jeff.getCustomerAllergies().size());
    }
    @Test
    void removeAllergyNotAlreadyInListTest() {
        assertEquals(1,jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));

        assertFalse(jeff.removeAllergy("Peanut"));
        assertEquals(1, jeff.getCustomerAllergies().size());
        assertEquals("Dairy", jeff.getCustomerAllergies().get(0));
    }

    @Test
    void getTotalTimeTest(){
        assertEquals(2, jeff.getListOfFoodOrdered().size());
        assertEquals(friedChicken, jeff.getListOfFoodOrdered().get(0));
        assertEquals(burger, jeff.getListOfFoodOrdered().get(1));

        assertEquals(25, jeff.getTotalTime());
    }

}
