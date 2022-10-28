package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.ArrayList;

// represents the menu for a given restaurant
public class MenuForRestaurant implements Writable {

    private final ArrayList<MenuItem> menu;   //menu for a restaurant

    /*
     * EFFECTS: menu is set to an empty list of menu food items
     */
    public MenuForRestaurant() {
        this.menu = new ArrayList<MenuItem>();
    }

    /*
     * EFFECTS: gets the array of menu items
     */
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    /*
     * MODIFIES: This
     * EFFECTS: if food item does not exist on the menu add the item otherwise nothing is added
     */
    public boolean addFoodItem(MenuItem menuItem) {
        if (menu.contains(menuItem)) {
            return false;
        } else {
            this.menu.add(menuItem);
            return true;
        }
    }

    /*
     * EFFECTS: get the foodItem from the menu by name
     */
    public MenuItem getFoodItem(String foodName) {
        for (MenuItem item: menu) {
            if (item.getNameOfDish().equals(foodName)) {
                return item;
            }
        }
        return null;
    }

    /*
     * MODIFIES: This
     * EFFECTS: finds the FoodItem with the same name as the input string and removes
     *          it from the menu list
     */
    public boolean deleteFoodItemFromMenu(String foodName) {
        MenuItem itemToDelete = getFoodItem(foodName);
        if (itemToDelete == null) {
            return false;
        } else {
            menu.remove(itemToDelete);
            return true;
        }
    }


    /*
     * EFFECTS: get all FoodItem from the menu
     */
    public String getAllMenuItems() {
        String menuStringForm = "";
        for (MenuItem item: menu) {
            menuStringForm = menuStringForm + item.getNameOfDish() + " " + item.getPriceOfItem() + " ";
        }
        return menuStringForm;
    }

    /*
     * MODIFIES: this
     * EFFECTS: clear all items from the menu
     */
    public void clearMenu() {
        menu.clear();
    }

    /*
     * EFFECTS: gets the number of items in the menu
     */
    public int menuSize() {
        return menu.size();
    }

    @Override
    public JSONObject toJsonObj() {
        return null;
    }

    @Override
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (MenuItem menuItem: menu) {
            jsonArray.put(menuItem.toJsonObj());
        }
        return jsonArray;
    }
}
