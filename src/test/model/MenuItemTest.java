package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {
    private MenuItem friedChicken;

    @BeforeEach
    void runBeforeTests(){
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
    }

    @Test
    void testConstructor() {
        assertEquals(7, friedChicken.getFoodItemID());
        assertEquals("Fried Chicken", friedChicken.getNameOfDish());
        assertEquals(40, friedChicken.getPriceOfItem());
        assertTrue(friedChicken.isAvailability());
        assertEquals("Peanut", friedChicken.getAllergy());
        assertEquals(10, friedChicken.getTimeToMake());
    }

    @Test
    void printFoodItemTest() {
        assertEquals("food item ID: 0 name of dish: Fried Chicken is available: true allergy: " +
                "Peanut price: 40 time to make: 10", friedChicken.printFoodItem());
    }

    @Test
    void setNameOfDishTest() {
        friedChicken.setNameOfDish("Fried Meat");
        assertEquals("Fried Meat", friedChicken.getNameOfDish());
    }

    @Test
    void setPriceOfDishTest() {
        friedChicken.setPriceOfItem(100);
        assertEquals(100, friedChicken.getPriceOfItem());
    }

    @Test
    void setAvailabilityTest() {
        friedChicken.setAvailability(false);
        assertFalse(friedChicken.isAvailability());
    }

    @Test
    void setAllergyTest() {
        friedChicken.setAllergy("tomato");
        assertEquals("tomato", friedChicken.getAllergy());
    }

    @Test
    void setTimeToMake() {
        friedChicken.setTimeToMake(100);
        assertEquals(100, friedChicken.getTimeToMake());
    }

    @Test
    void toStringTest () {
        assertEquals("Fried Chicken", friedChicken.toString());
    }

}
