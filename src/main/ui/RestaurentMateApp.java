package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.MenuItem;
import model.OrderForRestaurant;
import model.MenuForRestaurant;
import model.Restaurent;

//Restaurant management application
public class RestaurentMateApp {
    private static MenuItem friedChicken;
    private static MenuItem burger;
    private Restaurent restaurent;
    private ArrayList<OrderForRestaurant> orderQueue;
    private MenuForRestaurant menu1;
    private OrderForRestaurant order;
    private Scanner input;

    //EFFECTS: runs the restaurant application homepage
    public RestaurentMateApp() {
        runRestaurantApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input for homepage
    private void runRestaurantApp() {
        boolean continueRunning = true;
        String commands = null;

        init();

        while (continueRunning) {
            displayPointOfSaleHomePage();
            commands = input.next();
            commands = commands.toLowerCase();

            if (commands.equals("q")) {
                continueRunning = false;
            } else {
                processCommandsForHomePage(commands);
            }
        }
        System.out.println("Goodbye and have a good day!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command for homepage
    private void processCommandsForHomePage(String commands) {
        if (commands.equals("f")) {
            menuItemInteraction();
        } else if (commands.equals("m")) {
            menuInteraction();
        } else if (commands.equals("o")) {
            orderInteraction();
        } else if (commands.equals("r")) {
            restaurantInteraction();
        } else {
            System.out.println("Invalid Section, select one of the above options");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes restaurant, menu of restaurant, and food items for menu
    private void init() {
        orderQueue = new ArrayList<>();
        menu1 = new MenuForRestaurant();
        friedChicken = new MenuItem("Fried Chicken", 40, "Peanut", 10);
        burger = new MenuItem("Burger", 20, "Dairy", 15);
        menu1.addFoodItem(friedChicken);
        menu1.addFoodItem(burger);
        restaurent = new Restaurent("UBC Club", "contemporary", orderQueue, menu1);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays homepage options for users
    private void displayPointOfSaleHomePage() {
        System.out.println("\nChoose from:");
        System.out.println("\tf -> menu items interaction");
        System.out.println("\tm -> menu interaction");
        System.out.println("\to -> order interaction");
        System.out.println("\tr -> restaurant interaction");
    }


    //EFFECTS: runs the menu items for the restaurant application
    private void menuItemInteraction() {
        runMenuItemInteractionHomePage();
    }

    //MODIFIES: this
    //EFFECTS: processes user input for menu items application
    private void runMenuItemInteractionHomePage() {
        boolean continueRunning = true;
        String commands = null;

        while (continueRunning) {
            displayMenuItemInteractionHomePage();
            commands = input.next();
            commands = commands.toLowerCase();

            if (commands.equals("e")) {
                continueRunning = false;
            } else {
                processCommandsForMenuItem(commands);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command for menu item appliccation
    private void processCommandsForMenuItem(String commands) {
        if (commands.equals("a")) {
            addFoodItem();
        } else if (commands.equals("r")) {
            removeFoodItem();
        } else if (commands.equals("e")) {
            displayPointOfSaleHomePage();
        } else {
            System.out.println("Invalid Section, select one of the above options");
        }
    }

    //EFFECTS: displays menu items application options for users
    private void displayMenuItemInteractionHomePage() {
        System.out.println("\nChoose from:");
        System.out.println("\ta -> add food item to menu");
        System.out.println("\tr -> remove food item");
        System.out.println("\te -> esc");
    }

    // REQUIRES: inputed menu item must be a valid item in the menu and item to be removed must be typed exactly as
    //           the item's name appear in the menu
    // MODIFIES: this
    // EFFECTS: remove a food item from the restaurant's menu
    private void removeFoodItem() {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runMenuItemInteractionHomePage();
        } else {
            System.out.println("Fill out form to delete an item from the menu");
            System.out.println("To delete an item type the item's name from the menu items below");
            System.out.println(restaurent.getMenu().getAllMenuItems());
            System.out.println();
            input = in.nextLine();
            if (!restaurent.getMenu().deleteFoodItemFromMenu(input)) {
                System.out.println("Food Item " + input + " is not in the menu");
                runMenuInteractionHomePage();
            } else {
                System.out.println("Menu is now:");
                System.out.println(restaurent.getMenu().getAllMenuItems());
            }
        }
        runMenuItemInteractionHomePage();
    }

    // REQUIRES: dollar amount and time to make to be a positive integer
    // MODIFIES: this
    // EFFECTS: adds a food item from the restaurant's menu
    private void addFoodItem() {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        System.out.println("Fill out the form to add a new item to the menu");
        System.out.println("enter name of dish:");
        String dishName = in.nextLine();
        System.out.println("enter price of dish:");
        String cost = in.nextLine();
        int intCost = Integer.parseInt(cost);
        System.out.println("enter main allergic concern with dish:");
        String allergy = in.nextLine();
        System.out.println("enter time to make the dish (minutes):");
        String time = in.nextLine();
        int intTime = Integer.parseInt(time);
        restaurent.getMenu().addFoodItem(new MenuItem(dishName, intCost, allergy, intTime));
        System.out.println(dishName + " added to menu");
        System.out.println("menu is now");
        System.out.println(restaurent.getMenu().getAllMenuItems());

        runMenuItemInteractionHomePage();
    }


    //EFFECTS: runs the menu for the restaurant
    private void menuInteraction() {
        runMenuInteractionHomePage();
    }

    //MODIFIES: this
    //EFFECTS: processes user input for menu application
    private void runMenuInteractionHomePage() {
        boolean continueRunning = true;
        String commands = null;

        while (continueRunning) {
            displayMenuInteractionHomePage();
            commands = input.next();
            commands = commands.toLowerCase();

            if (commands.equals("e")) {
                continueRunning = false;
            } else {
                processCommandsForMenu(commands);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command for menu application
    private void processCommandsForMenu(String commands) {
        if (commands.equals("c")) {
            clearMenu();
        } else if (commands.equals("p")) {
            printMenu();
        } else if (commands.equals("e")) {
            displayPointOfSaleHomePage();
        } else {
            System.out.println("Invalid Section, select one of the above options");
        }
    }

    //EFFECTS: displays menu application options for users
    private void displayMenuInteractionHomePage() {
        System.out.println("\nChoose from:");
        System.out.println("\tc -> clear menu");
        System.out.println("\tp -> print menu");
        System.out.println("\te -> esc");
    }

    // MODIFIES: this
    // EFFECTS: deletes all menu items in the restaurant's menu
    private void clearMenu() {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runMenuInteractionHomePage();
        } else {
            System.out.println("WARNING: this actions is not reversible");
            System.out.println("type out \"yes\" to clear the menu or type anything else to clear the action");
            String deleteCommand = in.nextLine();
            if (deleteCommand.equals("yes")) {
                restaurent.getMenu().clearMenu();
                System.out.println("menu has been cleared");
            } else {
                runMenuInteractionHomePage();
            }
        }
    }

    // EFFECTS: prints the menu of the restaurant onto the screen
    private void printMenu() {
        System.out.println(restaurent.getMenu().getAllMenuItems());
    }

    //EFFECTS: runs the ordering application for the restaurant homepage
    private void orderInteraction() {
        runOrderInteraction();
    }

    //MODIFIES: this
    //EFFECTS: processes user input for ordering application
    private void runOrderInteraction() {
        boolean continueRunning = true;
        String commands = null;

        while (continueRunning) {
            displayOrderInteractionHomePage();
            commands = input.next();
            commands = commands.toLowerCase();

            if (commands.equals("e")) {
                continueRunning = false;
            } else {
                processCommandsForOrder(commands);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: displays ordering application options for users
    private void displayOrderInteractionHomePage() {
        System.out.println("\nChoose from:");
        System.out.println("\ta -> add new order");
        System.out.println("\te -> esc");
    }

    //MODIFIES: this
    //EFFECTS: processes user command for ordering application
    private void processCommandsForOrder(String commands) {
        if (commands.equals("a")) {
            addNewOrder();
        } else if (commands.equals("e")) {
            displayPointOfSaleHomePage();
        } else {
            System.out.println("Invalid Section, select one of the above options");
        }
    }

    // REQUIRES: name must be of length at least 1
    // MODIFIES: this
    // EFFECTS: adds a new order to the restaurant's queue of orders
    private void addNewOrder() {
        ArrayList<MenuItem> currentOrder;
        ArrayList<String> allergies;
        OrderForRestaurant newOrder;
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runMenuItemInteractionHomePage();
        } else {
            System.out.println("Fill out the form to add a new order");
            System.out.println("enter name of customer:");
            String customerName = in.nextLine();
            currentOrder = creatingCustomerOrderList();
            allergies = creatingAllergyList();
            newOrder = new OrderForRestaurant(customerName, currentOrder, allergies);
            restaurent.addOrder(newOrder);
            System.out.println(" added to restaurant queue" + customerName + " " + "food items ordered: "
                    + currentOrder + " allergies: " + allergies
                    + " Price: " + newOrder.getTotalPrice() + " time to make: " + newOrder.getTotalTime());
        }
    }

    // REQUIRES: order list must be at least 1 in length and valid (items already existing in the restaurant's menu)
    //           menu items must be chosen and item's name must be typed exatly as they appear in the menu
    // MODIFIES: addNewOrder
    // EFFECTS: creates the list of food items bought for each order
    private ArrayList<MenuItem> creatingCustomerOrderList() {
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<MenuItem> result = new ArrayList<>();
        boolean continueRunning = true;

        System.out.println("input customer order by typing out name of dish below");
        while (continueRunning) {
            System.out.println("type next dish when done type d");
            System.out.println(restaurent.getMenu().getAllMenuItems());
            input = in.nextLine();
            if (input.equals("d")) {
                continueRunning = false;
                break;
            }
            MenuItem dish = restaurent.getMenu().getFoodItem(input);
            result.add(dish);
            System.out.println(result);
        }
        result.stream().filter(item -> item != null);
        return result;
    }

    // MODIFIES: addNewOrder
    // EFFECTS: creates the list of allergies for each order
    private ArrayList<String> creatingAllergyList() {
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<String> result = new ArrayList<>();
        boolean continueRunning = true;

        System.out.println("input customer allergy by typing out customer allergy below");
        while (continueRunning) {
            System.out.println("type next allergy when done type d");
            input = in.nextLine();
            if (input.equals("d")) {
                continueRunning = false;
                break;
            }
            result.add(input);
            System.out.println(result);
        }
        return result;
    }

    //EFFECTS: runs the restaurant application
    private void restaurantInteraction() {
        runRestaurantInteraction();
    }

    //MODIFIES: this
    //EFFECTS: processes user input for restaurant application
    private void runRestaurantInteraction() {
        boolean continueRunning = true;
        String commands = null;

        while (continueRunning) {
            displayRestaurantHomePage();
            commands = input.next();
            commands = commands.toLowerCase();

            if (commands.equals("e")) {
                continueRunning = false;
            } else {
                processCommandsForRestaurant(commands);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command for restaurant application
    private void processCommandsForRestaurant(String commands) {
        if (commands.equals("sn")) {
            setName();
        } else if (commands.equals("sc")) {
            setCuisine();
        } else if (commands.equals("fo")) {
            finishOrder();
        } else if (commands.equals("po")) {
            prioritizeOrder();
        } else if (commands.equals("pq")) {
            printQueue();
        } else if (commands.equals("ro")) {
            removeOrder();
        } else if (commands.equals("pri")) {
            printOrder();
        } else if (commands.equals("mo")) {
            modifyOrder();
        } else if (commands.equals("e")) {
            displayPointOfSaleHomePage();
        } else {
            System.out.println("Invalid Section, select one of the above options");
        }
    }

    //EFFECTS: displays restaurant application homepage options for users
    private void displayRestaurantHomePage() {
        System.out.println("\nChoose from:");
        System.out.println("\tsn ->  set restaurant name");
        System.out.println("\tsc ->  set cuisine");
        System.out.println("\tfo ->  finish order");
        System.out.println("\tpo ->  prioritize specific order");
        System.out.println("\tpq ->  print queue");
        System.out.println("\tro ->  remove order");
        System.out.println("\tpri -> print specific order");
        System.out.println("\tmo ->  modify existing order");
        System.out.println("\te -> esc");
    }

    // REQUIRES: name must be at least 1 in length
    // MODIFIES: this
    // EFFECTS: sets the restaurant's name
    private void setName() {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runRestaurantInteraction();
        } else {
            System.out.println("type out name you would like the restaurant to be set to");
            String newName = in.nextLine();
            restaurent.setRestaurantName(newName);
            System.out.println("new restaurant name is " + restaurent.getRestaurantName());
        }
    }

    // REQUIRES: cuisine must be at least 1 in length
    // MODIFIES: this
    // EFFECTS: sets the restaurant's cuisine
    private void setCuisine() {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runRestaurantInteraction();
        } else {
            System.out.println("type out cuisine you would like the restaurant to be set to");
            String newCuisine = in.nextLine();
            restaurent.setCuisine(newCuisine);
            System.out.println("new cuisine is " + restaurent.getCuisine());
        }
    }

    // REQUIRES: order chosen must be a valid order ID in the restaurant's queue
    // MODIFIES: this
    // EFFECTS: notifies the restaurant staff of a finished order and removes the items from the
    //          restaurant's queue
    private void finishOrder() {
        OrderForRestaurant finishedOrder;
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runRestaurantInteraction();
        } else {
            System.out.println("input order ID of finished order");
            System.out.println(restaurent.getCurrentOrders());
            String orderId = in.nextLine();
            int orderIDNumber = Integer.parseInt(orderId);
            finishedOrder = restaurent.getSingleOrder(orderIDNumber);
            System.out.println(restaurent.getSingleOrder(orderIDNumber).getCustomerName() + " order ID: "
                    + restaurent.getSingleOrder(orderIDNumber).getCustomerID() + " finished and ready for delivery");
            restaurent.finishOrder(finishedOrder);
        }
    }

    // REQUIRES: order chosen must be a valid order ID in the restaurant's queue
    // MODIFIES: this
    // EFFECTS:  places an existing order at the top of the queue
    private void prioritizeOrder() {
        OrderForRestaurant orderToPrioritize;
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runRestaurantInteraction();
        } else {
            System.out.println("input order ID of order to be prioritized");
            System.out.println(restaurent.getCurrentOrders());
            String orderId = in.nextLine();
            int orderIDNumber = Integer.parseInt(orderId);
            orderToPrioritize = restaurent.getSingleOrder(orderIDNumber);
            if (orderToPrioritize == null) {
                System.out.println("invalid order try again");
            } else {
                System.out.println(restaurent.getSingleOrder(orderIDNumber).getCustomerName() + " order ID: "
                        + restaurent.getSingleOrder(orderIDNumber).getCustomerID() + " has been prioritized");
                restaurent.prioritizeOrder(orderToPrioritize);

            }
        }
    }

    //EFFECTS: prints the restaurant's current queue of orders
    private void printQueue() {
        System.out.println(restaurent.getCurrentOrders());
    }

    // REQUIRES: order chosen must be a valid order ID in the restaurant's queue
    // MODIFIES: this
    // EFFECTS: removes an order from the restaurant's queue
    private void removeOrder() {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runRestaurantInteraction();
        } else {
            System.out.println("input order ID of order to be removed from queue");
            System.out.println(restaurent.getCurrentOrders());
            String orderId = in.nextLine();
            int orderIDNumber = Integer.parseInt(orderId);
            if (restaurent.removeOrder(orderIDNumber)) {
                System.out.println("order ID " + orderIDNumber + " " + "removed");
            } else {
                System.out.println("order ID " + orderIDNumber + " " + "was not ordered");
            }
        }
    }

    // REQUIRES: order chosen must be a valid order ID in the restaurant's queue
    // EFFECTS:  prints a single order in the restaurant's queue
    private void printOrder() {
        OrderForRestaurant orderToPrint;
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        input = in.nextLine();
        if (input.equals("0")) {
            runRestaurantInteraction();
        } else {
            System.out.println("input order ID of order to be printed");
            System.out.println(restaurent.getCurrentOrders());
            String orderId = in.nextLine();
            int orderIDNumber = Integer.parseInt(orderId);
            orderToPrint = restaurent.getSingleOrder(orderIDNumber);
            if (orderToPrint == null) {
                System.out.println("invalid order");
            } else {
                System.out.println(orderToPrint.getCustomerName() + " " + "food items ordered: "
                        + orderToPrint.getListOfFoodOrdered() + " allergies " + orderToPrint.getCustomerAllergies()
                        + " Price " + orderToPrint.getTotalPrice() + " time to make: " + orderToPrint.getTotalTime());
            }
        }
    }

    //EFFECTS: runs the modify order application
    private void modifyOrder() {
        runModifyOrder();
    }

    //MODIFIES: this
    //EFFECTS: processes user input for modify order
    private void runModifyOrder() {
        boolean continueRunning = true;
        String commands = null;

        while (continueRunning) {
            displayModifyOrder();
            commands = input.next();
            commands = commands.toLowerCase();

            if (commands.equals("e")) {
                continueRunning = false;
            } else {
                processCommandsForModifyOrder(commands);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command for modify order
    private void processCommandsForModifyOrder(String commands) {
        if (commands.equals("ai")) {
            addItemToOrder();
        } else if (commands.equals("ri")) {
            removeItemFromOrder();
        } else if (commands.equals("aa")) {
            addAllergyFromOrder();
        } else if (commands.equals("ra")) {
            removeAllergyFromOrder();
        } else if (commands.equals("e")) {
            runRestaurantInteraction();
        } else {
            System.out.println("Invalid Section, select one of the above options");
        }
    }

    //EFFECTS: displays modify order options for users
    private void displayModifyOrder() {
        System.out.println("\nChoose from:");
        System.out.println("\tai -> add item to existing order");
        System.out.println("\tri -> remove item to existing order");
        System.out.println("\taa -> add allergy to existing order");
        System.out.println("\tra -> remove allergy to existing order");
        System.out.println("\te -> esc");
    }

    // REQUIRES: order chosen must be a valid order ID in the restaurant's queue and valid (items already existing in
    //           the restaurant's menu) menu items must be chosen and item's name must be typed exactly as they appear
    //           in the menu list
    //MODIFIES: this
    //EFFECTS: adds an additional food item to an existing order in the restaurant's queue of orders
    private void addItemToOrder() {
        Scanner in = new Scanner(System.in);
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        System.out.println("input order ID of order to be modified");
        System.out.println(restaurent.getCurrentOrders());
        String orderId = in.nextLine();
        int orderIDNumber = Integer.parseInt(orderId);
        OrderForRestaurant orderToModify = restaurent.getSingleOrder(orderIDNumber);
        if (orderToModify == null) {
            System.out.println("invalid order try again");
            addItemToOrder();
        } else {
            System.out.println("input food item to be added from menu: " + restaurent.getMenu().getAllMenuItems());
            String itemToAdd = in.nextLine();
            MenuItem dish = restaurent.getMenu().getFoodItem(itemToAdd);
            restaurent.getSingleOrder(orderIDNumber).addItemToOrder(dish);
            System.out.println(orderToModify.getCustomerName() + " " + "food items ordered: "
                    + orderToModify.getListOfFoodOrdered());
        }
    }

    // REQUIRES: order chosen must be a valid order ID in the restaurant's queue and valid (items already existing in
    //           the customer's order) order items must be chosen and item's name must be typed exactly as they appear
    //           in the order list and the length of the list of menu items for the chosen order must be at least 2
    //MODIFIES: this
    //EFFECTS: removes an food item from an existing order in the restaurant's queue of orders
    private void removeItemFromOrder() {
        Scanner in = new Scanner(System.in);
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        String input = in.nextLine();
        System.out.println("input order ID of order to be modified");
        System.out.println(restaurent.getCurrentOrders());
        String orderId = in.nextLine();
        int orderIDNumber = Integer.parseInt(orderId);
        OrderForRestaurant orderToModify = restaurent.getSingleOrder(orderIDNumber);
        if (orderToModify == null) {
            System.out.println("invalid order try again");
            removeItemFromOrder();
        } else {
            System.out.println("input food item to be removed from order: " + orderToModify.getListOfFoodOrdered());
            input = in.nextLine();
            restaurent.getSingleOrder(orderIDNumber).removeItemToOrder(input);
            System.out.println(orderToModify.getCustomerName() + " " + "food items ordered: "
                    + orderToModify.getListOfFoodOrdered());
        }
    }

    //MODIFIES: this
    //EFFECTS: adds an additional allergy to an existing order in the restaurant's queue of orders
    private void addAllergyFromOrder() {
        OrderForRestaurant orderToModify;
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        System.out.println("input order ID of order to be modified");
        System.out.println(restaurent.getCurrentOrders());
        String orderId = in.nextLine();
        int orderIDNumber = Integer.parseInt(orderId);
        orderToModify = restaurent.getSingleOrder(orderIDNumber);
        if (orderToModify == null) {
            System.out.println("invalid order try again");
            addAllergyFromOrder();
        } else {
            System.out.println("input additional allergy");
            String additionalAllergy = in.nextLine();
            orderToModify.addAllergy(additionalAllergy);
            System.out.println("allergies of order is now " + orderToModify.getCustomerAllergies());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes an allergy from an existing order in the restaurant's queue of order and the length of the list
    //         of allergies for the chosen order must be at least 2
    private void removeAllergyFromOrder() {
        OrderForRestaurant orderToModify;
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Quit the program by inputting 0 or press anything else to continue");
        System.out.println();
        System.out.println("input order ID of order to be modified");
        System.out.println(restaurent.getCurrentOrders());
        String orderId = in.nextLine();
        int orderIDNumber = Integer.parseInt(orderId);
        orderToModify = restaurent.getSingleOrder(orderIDNumber);
        if (orderToModify == null) {
            System.out.println("invalid order try again");
            removeAllergyFromOrder();
        } else {
            System.out.println("input allergy to be removed");
            String removeAllergy = in.nextLine();
            orderToModify.removeAllergy(removeAllergy);
            System.out.println("allergies of order is now " + orderToModify.getCustomerAllergies());
        }
    }

}
