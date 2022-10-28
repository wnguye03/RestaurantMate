package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.MenuForRestaurant;
import model.MenuItem;
import model.OrderForRestaurant;
import model.Restaurent;
import org.json.*;

//Modeled after JsonSerializationDemo from phase 2 module

// Represents a reader that reads target from JSON data stored in file
public class JsonReader {
    protected String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads restaurant object from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Restaurent read() throws IOException {
        Restaurent restaurent = new Restaurent("dummy", "dummy",
                new ArrayList<OrderForRestaurant>(), new MenuForRestaurant());
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseRestaurent(restaurent, jsonObject);
        return restaurent;
    }

    // EFFECTS: reads source file as string and returns it
    protected String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Restaurent from JSON object and returns it
    private Restaurent parseRestaurent(Restaurent restaurent, JSONObject jsonObject) {
//        System.out.println("reached");
        restaurent.setRestaurantName(jsonObject.getString("restaurantName"));
        restaurent.setCuisine(jsonObject.getString("cuisine"));
        parseMenu(restaurent, (JSONArray) jsonObject.get("menu"));
        parseOrders(restaurent, (JSONArray) jsonObject.get("orders"));
        return restaurent;
    }

    private void parseOrders(Restaurent restaurent, JSONArray orders) {
        for (Object json: orders) {
            JSONObject orderJO = (JSONObject) json;
            String name = orderJO.getString("name");
            ArrayList<String> allergy = new ArrayList<>();
            JSONArray jsonArrayAllergy = ((JSONObject) json).getJSONArray("customer allergy");
            for (Object json2: jsonArrayAllergy) {
                String currAllergy = json2.toString();
                allergy.add(currAllergy);
            }
            ArrayList<MenuItem> foodOrdered = new ArrayList<>();
            JSONArray jsonArrayFoodItem = ((JSONObject) json).getJSONArray("food ordered");
            for (Object json3: jsonArrayFoodItem) {
                JSONObject menuItemJo = (JSONObject) json3;
                String nameOfDish = menuItemJo.getString("nameOfDish");
                String allergyOfDish = menuItemJo.getString("allergy");
                int priceOfItem = menuItemJo.getInt("priceOfItem");
                int timeToMake = menuItemJo.getInt("timeToMake");
                MenuItem mi = new MenuItem(nameOfDish, priceOfItem, allergyOfDish, timeToMake);
                foodOrdered.add(mi);
            }
            OrderForRestaurant order = new OrderForRestaurant(name, foodOrdered, allergy);
            restaurent.addOrder(order);
        }
    }



//    public Object readMenu() throws IOException {
//        String jsonData = readFile(source);
//        JSONArray jsonArray = new JSONArray(jsonData);
//        MenuForRestaurant menu = new MenuForRestaurant();
//        parseMenu(menu, jsonArray);
//        return menu;
//    }


    public void parseMenu(Restaurent restaurent, JSONArray jsonArray) {
        for (Object json: jsonArray) {
            JSONObject menuItemJo = (JSONObject) json;
            int id = menuItemJo.getInt("id");
            String name = menuItemJo.getString("name");
            boolean avaliability = menuItemJo.getBoolean("avaliability");
            String allergy = menuItemJo.getString("allergy");
            int priceOfItem = menuItemJo.getInt("priceOfItem");
            int timeToMake = menuItemJo.getInt("time to make");
            MenuItem mi = new MenuItem(name, priceOfItem, allergy, timeToMake);
            restaurent.getMenu().addFoodItem(mi);
        }
    }

}

