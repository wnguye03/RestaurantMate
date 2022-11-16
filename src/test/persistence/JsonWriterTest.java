package persistence;

import model.MenuForRestaurant;
import model.MenuItem;
import model.OrderForRestaurant;
import model.Restaurent;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
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
    void testWriterInvalidFile() {
        try {
            Restaurent restaurent = new Restaurent("UBC Club", "american",
                    new ArrayList<OrderForRestaurant>(), new MenuForRestaurant());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurant() {
        try {
            Restaurent restaurent = new Restaurent("UBC Club", "american",
                    new ArrayList<OrderForRestaurant>(), new MenuForRestaurant());            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(restaurent);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            restaurent = reader.read();
            assertEquals("UBC Club", restaurent.getRestaurantName());
            assertEquals("american", restaurent.getCuisine());
            assertEquals(0, restaurent.getMenu().menuSize());
            assertEquals(0, restaurent.getCurrentOrders().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRestaurant() {
        try {
            ArrayList<OrderForRestaurant> orderList = new ArrayList<>();
            ArrayList<MenuItem> customerOrder = new ArrayList<>();
            ArrayList<String> allergy = new ArrayList<>();
            MenuItem testItem = new MenuItem("sandwich", 10, "gluten", 5);
            MenuForRestaurant menu = new MenuForRestaurant();
            menu.addFoodItem(testItem);
            allergy.add("Dairy");
            customerOrder.add(testItem);
            OrderForRestaurant testOrder = new OrderForRestaurant("Joe", customerOrder, allergy);
            orderList.add(testOrder);
            Restaurent restaurent = new Restaurent("Loaf", "cafe", orderList, menu);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRestaurant.json");
            writer.open();
            writer.write(restaurent);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRestaurant.json");
            restaurent = reader.read();
            assertEquals("Loaf", restaurent.getRestaurantName());
            assertEquals("cafe", restaurent.getCuisine());
            assertEquals(1, restaurent.getMenu().menuSize());
            checkMenu("sandwich", 1, true, "gluten", 10, 5,
                    restaurent.getMenu().getMenu().get(0));
            assertEquals(1, restaurent.getCurrentOrders().size());
            checkOrder(1, "Joe", 10, restaurent.getSingleOrder(1).getListOfFoodOrdered(),
                    restaurent.getSingleOrder(1).getCustomerAllergies(), restaurent.getSingleOrder(1));
            assertEquals(1, restaurent.getSingleOrder(1).getListOfFoodOrdered().size());
            assertEquals(1, restaurent.getSingleOrder(1).getCustomerAllergies().size());

        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
