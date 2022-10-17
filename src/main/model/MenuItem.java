package model;

//repersents a food item having a item number id, name of dish, price, availability (boolean)
// associated allergy, time to make
public class MenuItem {
    private static int nextFoodItemID = 0; //used to track the id of the next food item
    private final int foodItemID;         // food item id
    private String nameOfDish;      // the name of the dish
    private int priceOfItem;        // price of food item in dollars
    private boolean availability;   // is the food currently available on the menu to be ordered
    private String allergy;         // The main allergy concern of the dish
    private int timeToMake;         // time to make the dish in minutes

    /*
     * REQUIRES: nameOfDish has a non-zero length, priceOfItem to be a non-negative number, and timeToMake
     *           to be a non-negative number
     * EFFECTS: foodItemID is a unique positive integer assigned only to this dish; name of food item is
     *          set to nameOfDish; the price of the dish is set to priceOfItem; the availability of the dish
     *          is set to a default of true; the largest allergy concern of the dish is set to allergy; the
     *          time it takes to make the dish is set to timeToMake
     */
    public MenuItem(String nameOfDish, int priceOfItem, String allergy, int timeToMake) {
        this.foodItemID = nextFoodItemID++;
        this.nameOfDish = nameOfDish;
        this.priceOfItem = priceOfItem;
        this.availability = true;
        this.allergy = allergy;
        this.timeToMake = timeToMake;
    }

    public int getFoodItemID() {
        return foodItemID;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public int getPriceOfItem() {
        return priceOfItem;
    }

    public void setPriceOfItem(int priceOfItem) {
        this.priceOfItem = priceOfItem;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public int getTimeToMake() {
        return timeToMake;
    }

    public void setTimeToMake(int timeToMake) {
        this.timeToMake = timeToMake;
    }

    //EFFECTS: Print food item;
    public String printFoodItem() {
        String result = "food item ID: " + getFoodItemID() + " name of dish: " + getNameOfDish() + " is available: "
                + isAvailability() + " allergy: " + getAllergy() + " price: " + getPriceOfItem() + " time to make: "
                + getTimeToMake();
        return result;
    }

    //MODIFIES: this
    //EFFECTS: converts the printed model form on the ui to string format
    @Override
    public String toString() {
        return getNameOfDish();
    }
}
