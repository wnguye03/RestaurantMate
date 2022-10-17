package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuItemTest {
    private MenuItem friedChicken;

    @BeforeEach
    void runBeforeTests(){
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
    }

    @Test
    void testConstructor() {
        assertEquals(1, friedChicken.getFoodItemID());
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
}
