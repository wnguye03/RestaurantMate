package model;

//The class represents an restaurant having a name, cuisine type, and a list of current orders

import java.util.ArrayList;

public class Restaurent {
    private String restaurantName;
    private String cuisine;
    private final ArrayList<OrderForRestaurant> currentOrders;
    private final MenuForRestaurant menu;

    /*
     * REQUIRES: restaurantName and cuisine has a non-zero length, OrderForRestaurant's length is greater than 0,
     * EFFECTS: the restaurant's name is set to restaurantName; the restaurant's cuisine is set to cuisine
     *          the restaurant's current order is set to OrderForRestaurant, the restaurant's menu is set to menu
     */
    public Restaurent(String restaurantName, String cuisine, ArrayList<OrderForRestaurant> currentOrders,
                      MenuForRestaurant menu) {
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.currentOrders = currentOrders;
        this.menu = menu;
    }

    public MenuForRestaurant getMenu() {
        return menu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /*
     * EFFECTS: gets the current queue of orders for the restaurant
     */
    public ArrayList<String> getCurrentOrders() {
        ArrayList<String> allOrders = new ArrayList<>();
        for (OrderForRestaurant order: currentOrders) {
            allOrders.add("order ID: " + order.getCustomerID() + " name of customer: " + order.getCustomerName()
                    + " allergy " + order.getListOfFoodOrdered() + " allergy " + order.getCustomerAllergies()
                    + " time to make " + order.getTotalTime() + " price: " + order.getTotalPrice());
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
        currentOrders.remove(order);
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: brings a specified order to the front of the queue of orders to do
     */
    public boolean prioritizeOrder(OrderForRestaurant order) {
        if (!currentOrders.contains(order)) {
            currentOrders.add(0, order);
            return true;
        } else {
            currentOrders.remove(order);
            currentOrders.add(0, order);
            return true;
        }
    }
}
