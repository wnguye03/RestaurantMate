package persistence;

import model.MenuForRestaurant;
import model.MenuItem;
import model.OrderForRestaurant;
import model.Restaurent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

   protected void checkMenu(String name, int id, boolean avaliability, String allergy, int price, int time,
                            MenuItem item) {
       assertEquals(name, item.getNameOfDish());
       assertEquals(id, item.getFoodItemID());
       assertEquals(avaliability, item.isAvailability());
       assertEquals(allergy, item.getAllergy());
       assertEquals(time, item.getTimeToMake());
       assertEquals(price, item.getPriceOfItem());
   }

   protected void checkOrder(int id, String name, int price, ArrayList<MenuItem> ordered, ArrayList<String> allergy,
                             OrderForRestaurant orderForRestaurant) {
       assertEquals(id, orderForRestaurant.getCustomerID());
       assertEquals(name, orderForRestaurant.getCustomerName());
       assertEquals(price, orderForRestaurant.getTotalPrice());

       assertEquals(ordered.size(), orderForRestaurant.getListOfFoodOrdered().size());
       for( int i = 0; i < orderForRestaurant.getListOfFoodOrdered().size(); i++) {
           MenuItem menuItem = orderForRestaurant.getListOfFoodOrdered().get(i);
           MenuItem actualMenuItem = ordered.get(i);
           checkMenu( actualMenuItem.getNameOfDish(), actualMenuItem.getFoodItemID(), actualMenuItem.isAvailability(),
           actualMenuItem.getAllergy(), actualMenuItem.getPriceOfItem(), actualMenuItem.getTimeToMake(), menuItem);
       }
       assertEquals(allergy.size(), orderForRestaurant.getCustomerAllergies().size());
       for( int i = 0; i < orderForRestaurant.getCustomerAllergies().size(); i++) {
           assertEquals(allergy.get(i), orderForRestaurant.getCustomerAllergies().get(i));
       }
   }

    @Test
    void testReaderOnNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Restaurent restaurent = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurant() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurant.json");
        try {
            Restaurent restaurent = reader.read();
            assertEquals("UBC Club", restaurent.getRestaurantName());
            assertEquals("contemporary", restaurent.getCuisine());
            assertEquals(0, restaurent.getMenu().menuSize());
            assertEquals(0, restaurent.getCurrentOrders().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurant.json");
        try {
            Restaurent restaurent = reader.read();
            assertEquals("UBC Club", restaurent.getRestaurantName());
            assertEquals("contemporary", restaurent.getCuisine());
            assertEquals(3, restaurent.getMenu().menuSize());
            assertEquals(2, restaurent.getCurrentOrders().size());
            MenuForRestaurant menu = restaurent.getMenu();
            assertEquals(3, restaurent.getMenu().menuSize());
            checkMenu("Fried Chicken", 0, true, "Peanut", 40, 10,
                    restaurent.getMenu().getMenu().get(0));
            checkMenu("Burger", 1, true, "Dairy", 20, 15,
                    restaurent.getMenu().getMenu().get(1));
            checkMenu("Rice", 2, true, "nothing", 10, 15,
                    restaurent.getMenu().getMenu().get(2));
            checkOrder(0, "Mike", 30, restaurent.getSingleOrder(0).getListOfFoodOrdered(),
                    restaurent.getSingleOrder(0).getCustomerAllergies(), restaurent.getSingleOrder(0));
            assertEquals(2, restaurent.getSingleOrder(0).getListOfFoodOrdered().size());
            assertEquals(1, restaurent.getSingleOrder(0).getCustomerAllergies().size());
            checkOrder(1, "Bob", 50, restaurent.getSingleOrder(1).getListOfFoodOrdered(),
                    restaurent.getSingleOrder(1).getCustomerAllergies(), restaurent.getSingleOrder(1));
            assertEquals(2, restaurent.getSingleOrder(1).getListOfFoodOrdered().size());
            assertEquals(0, restaurent.getSingleOrder(1).getCustomerAllergies().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
