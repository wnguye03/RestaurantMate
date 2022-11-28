package model;

//The class represents an restaurant having a name, cuisine type, and a list of current orders

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurent implements Writable {
    private String restaurantName;
    private String cuisine;
    private ArrayList<OrderForRestaurant> currentOrders;
    private MenuForRestaurant menu;
    private JsonWriter restaurantWriter;
    private JsonReader restaurantReader;
    private static final String RESTAURANT_STORE = "./data/restaurant.json";

//    private JsonWriter orderWriter;

    /*
     * REQUIRES: restaurantName and cuisine has a non-zero length, OrderForRestaurant's length is greater than 0,
     * EFFECTS: the restaurant's name is set to restaurantName; the restaurant's cuisine is set to cuisine
     *          the restaurant's current order is set to OrderForRestaurant, the restaurant's menu is set to menu
     *          the restaurant Json writer location is set to restaurantWriter the restaurant's reader location is set
     *          to restaurantReader
     */
    public Restaurent(String restaurantName, String cuisine, ArrayList<OrderForRestaurant> currentOrders,
                      MenuForRestaurant menu) {
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.currentOrders = currentOrders;
        this.menu = menu;
        restaurantWriter = new JsonWriter("./data/restaurant.json");
        restaurantReader = new JsonReader(RESTAURANT_STORE);
    }

    public MenuForRestaurant getMenu() {
        return menu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        EventLog.getInstance().logEvent(new Event("The Restaurant is now renamed to " + restaurantName));
        this.restaurantName = restaurantName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        EventLog.getInstance().logEvent(new Event("The restaurant's cuisine is now set to " + cuisine));
        this.cuisine = cuisine;
    }

    /*
     * EFFECTS: gets the current queue of orders for the restaurant
     */
    public ArrayList<String> getCurrentOrders() {
        ArrayList<String> allOrders = new ArrayList<>();
        for (OrderForRestaurant order: currentOrders) {
            allOrders.add("order ID: " + order.getCustomerID() + " name of customer: " + order.getCustomerName()
                    + " Food Ordered " + order.getListOfFoodOrdered() + " allergy " + order.getCustomerAllergies()
                    + " time to make " + order.getTotalTime() + " price: " + order.getTotalPrice() + "\n");
        }
        return allOrders;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds an additional order to the restaurant's current list of orders if the order is not currently
     *          on the list of orders to do
     */
    public Boolean addOrder(OrderForRestaurant order) {
        if (currentOrders.contains(order)) {
            return false;
        } else {
            EventLog.getInstance().logEvent(new Event("added order number " + order.getCustomerID()
                    + " to restaurant's queue of orders"));
            currentOrders.add(order);
            return true;
        }
    }

    /*
     * EFFECTS: gets the given customer order from the list of current orders
     */
    public OrderForRestaurant getSingleOrder(int orderId) {
        for (OrderForRestaurant order: currentOrders) {
            if (order.getCustomerID() == orderId) {
                return order;
            }
        }
        return null;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an order from the restaurant's current list of orders if the order is on the currrent list
     *          of orders to do
     */
    public Boolean removeOrder(int orderID) {
        OrderForRestaurant order = getSingleOrder(orderID);
        if (order != null) {
            EventLog.getInstance().logEvent(new Event("removed order number " + orderID
                    + " from restaurant's queue of orders"));
            currentOrders.remove(order);
            return true;
        } else {
            return false;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an order from the restaurant's current list of orders after finish preparation
     */
    public boolean finishOrder(OrderForRestaurant order) {
        EventLog.getInstance().logEvent(new Event("finished making order number " + order.getCustomerID()
                + " in the restaurant's queue of orders"));
        currentOrders.remove(order);
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: brings a specified order to the front of the queue of orders to do
     */
    public boolean prioritizeOrder(OrderForRestaurant order) {
        if (!currentOrders.contains(order)) {
            EventLog.getInstance().logEvent(new Event("prioritized order number " + order.getCustomerID()
                    + " in the restaurant's queue of orders"));
            currentOrders.add(0, order);
            return true;
        } else {
            EventLog.getInstance().logEvent(new Event("prioritized order number " + order.getCustomerID()
                    + " in the restaurant's queue of orders"));
            currentOrders.add(0, order);
            currentOrders.remove(order);
            currentOrders.add(0, order);
            return true;
        }
    }

    // EFFECTS: saves the current restaurant information to a JSON file
    public void handleSaveRestaurant() {
        try {
            EventLog.getInstance().logEvent(new Event("saved the restaurant to memory"));
            restaurantWriter.open();
            restaurantWriter.write(this);
            restaurantWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the current restaurant with the saved restaurant's specification and memory
    public Restaurent handleReadRestaurant(Restaurent restaurent) {
        try {
            EventLog.getInstance().logEvent(new Event("Restaurant from memory loaded into the application"));
            return (Restaurent) restaurantReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject toJsonObj() {
        JSONObject jo = new JSONObject();
        jo.put("restaurantName", restaurantName);
        jo.put("cuisine", cuisine);
        jo.put("orders", convertOrdersToJsonObj());
        jo.put("menu", menu.toJsonArray());

        return jo;
    }

    @Override
    public JSONArray toJsonArray() {
        return null;
    }

    // EFFECTS: converts the current list of restaurant orders to an Json array
    public JSONArray convertOrdersToJsonObj() {
        JSONArray jsonArray = new JSONArray();
        for (OrderForRestaurant order: currentOrders) {
            jsonArray.put(order.toJsonObj());
        }
        return jsonArray;
    }
}
