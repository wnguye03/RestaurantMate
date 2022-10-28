package model;

//The class represents an order having an id, name of customer, an array of menu items ordered, the price, and allergy

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderForRestaurant implements Writable {
    private static int nextOrderID = 0;             // used to track the id of the next account
    private final int customerID;                         // order ID used internally by restaurant to track order
    private String customerName;                    // name of customer ordering
    private int totalPrice;                         // the sum total price
    private final ArrayList<MenuItem> listOfFoodOrdered;  // list of food item ordered
    private final ArrayList<String> customerAllergies;     // list of customer allergy

    /*
     * REQUIRES: customerName has a non-zero length, listOfFoodOrdered's length is greater than 0,
     * EFFECTS: customerID is a unique positive integer assigned only to this customer's order; name of customer is
     *          set to customerName; the customer's allergy is set to customerAllergies
     */
    public OrderForRestaurant(String customerName, ArrayList<MenuItem> listOfFoodOrdered, ArrayList<String>
            customerAllergies) {
        this.customerID = nextOrderID++;
        this.customerName = customerName;
        this.listOfFoodOrdered = listOfFoodOrdered;
        this.customerAllergies = customerAllergies;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<MenuItem> getListOfFoodOrdered() {
        return listOfFoodOrdered;
    }

    public ArrayList<String> getCustomerAllergies() {
        return customerAllergies;
    }

    /*
     * EFFECTS: adds up the total cost of each food in the list of food ordered to get
     *          the final price
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (MenuItem item: this.listOfFoodOrdered) {
            totalPrice = totalPrice + item.getPriceOfItem();
        }
        return totalPrice;
    }

    /*
     * Requires: item to be added must be a valid item on the menu
     * MODIFIES: this
     * EFFECTS: adds an additional food item to the listOfFoodOrdered
     */
    public boolean addItemToOrder(MenuItem newItem) {
        this.listOfFoodOrdered.add(newItem);
        return true;
    }

    /*
     * Requires: item to be removed must be a valid food item ordered
     * MODIFIES: this
     * EFFECTS: if the food item does exists in the listOfFoodOrdered remove it
     */
    public boolean removeItemToOrder(String nameOfItem) {
        for (MenuItem item: listOfFoodOrdered) {
            if (item.getNameOfDish().equals(nameOfItem)) {
                listOfFoodOrdered.remove(item);
                return true;
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if allergy doesen't exists adds an allergy to the list of customer allergies
     *          otherwise do not add the allergy
     */
    public boolean addAllergy(String allergy) {
        if (customerAllergies.contains(allergy)) {
            return false;
        } else {
            customerAllergies.add(allergy);
            return true;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes allergy from list of customer allergies
     */
    public boolean removeAllergy(String allergy) {
        if (customerAllergies.contains(allergy)) {
            customerAllergies.remove(allergy);
            return true;
        } else {
            return false;
        }
    }

    /*
     * EFFECTS: adds up the total time to make each food in the list of food ordered to get
     *          the final time in minutes
     */
    public int getTotalTime() {
        int totalTime = 0;
        for (MenuItem item: this.listOfFoodOrdered) {
            totalTime = totalTime + item.getTimeToMake();
        }
        return totalTime;
    }

    @Override
    public JSONObject toJsonObj() {
        JSONObject restaurantOrders = new JSONObject();
        restaurantOrders.put("id", customerID);
        restaurantOrders.put("name", customerName);
        restaurantOrders.put("price", getTotalPrice());
        restaurantOrders.put("food ordered", listOfFoodOrdered);
        restaurantOrders.put("customer allergy", customerAllergies);
        return restaurantOrders;
    }

    @Override
    public JSONArray toJsonArray() {
        return null;
    }


}

























