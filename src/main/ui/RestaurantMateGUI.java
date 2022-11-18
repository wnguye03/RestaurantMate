package ui;


import model.MenuForRestaurant;
import model.MenuItem;
import model.OrderForRestaurant;
import model.Restaurent;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

///**
// * Represents the restaurant mate application's main window frame that houses all functionality and all sub-frames
// */
public class RestaurantMateGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 2000;
    private static final int HEIGHT = 2000;
    private JFrame frame;
    private JPanel panel;
    private JTextArea mainJta;

    private JButton foodBtn;
    private JButton addDish = new JButton();
    private JButton deleteDish = new JButton();

    private JButton menuBtn;
    private JButton clearMenuBtn = new JButton();

    private JButton orderBtn;
    private JButton nameBtn = new JButton();
    private JButton addItemBtn = new JButton();
    private JButton addAllergyBtn = new JButton();
    private JButton addOrderBtn = new JButton();


    private JButton restaurantBtn;
    private JButton setNameRestaurantBtn = new JButton();
    private JButton setCuisineRestaurantBtn = new JButton();
    private JButton finishOrderRestaurantBtn = new JButton();
    private JButton prioritizeOrderRestaurantBtn = new JButton();
    private JButton removeOrderRestaurantBtn = new JButton();
    private JButton modifyOrderRestaurantBtn = new JButton();

    private JButton save;
    private JButton load;
    private JButton quit;

    private JTextArea idFoodItemJta = new JTextArea();

    private Restaurent restaurent;
    private MenuForRestaurant menu = new MenuForRestaurant();

    /*
     * MODIFIES: this
     * EFFECTS: creates the starting home screen and initializes a dummy restaurant
     */
    public RestaurantMateGUI() {
        restaurent = new Restaurent("Loaf",  "Cafe", new ArrayList<OrderForRestaurant>(), menu);
        initGui();
        initHome();
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes a new GUI frame
     */
    public void initGui() {
        frame = new JFrame();
        frame.setTitle("Restaurant Mate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(true);

    }

    /*
     * MODIFIES: this
     * EFFECTS: init the components of the home screen
     */
    public void initHome() {
        addButtons();
        addTopBar();
        addCenterImage();
    }

    /*
     * MODIFIES: this
     * EFFECTS: init the top bar of the home screen: save button, load button, quit to home button
     */
    private void addTopBar() {
        JPanel mb = new JPanel();

        save = new JButton("Save Restaurant");
        save.addActionListener(this);

        load = new JButton("Load Restaurant");
        load.addActionListener(this);

        quit = new JButton("Quit to Home");
        quit.addActionListener(this);

        mb.add(save);
        mb.add(load);
        mb.add(quit);
        frame.getContentPane().add(BorderLayout.NORTH, mb);

    }

    /*
     * MODIFIES: this
     * EFFECTS: inits the bottom bar of the home screen: menu, menu items, order, and restaurant buttons
     */
    private void addButtons() {
        Panel panel = new Panel();

        foodBtn = new JButton("Menu Items");
        foodBtn.addActionListener(this);
        panel.add(foodBtn);

        menuBtn = new JButton("Menu");
        menuBtn.addActionListener(this);
        panel.add(menuBtn);

        orderBtn = new JButton("New Order");
        orderBtn.addActionListener(this);
        panel.add(orderBtn);

        restaurantBtn = new JButton("Restaurant");
        restaurantBtn.addActionListener(this);
        panel.add(restaurantBtn);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates the main load in image for the home page
     */
    private void addCenterImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("data/RestaurantMate-logos.jpeg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        frame.add(BorderLayout.CENTER, picLabel);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (foodBtn.equals(btn)) {
            openFoodPanel();
        } else if (menuBtn.equals(btn)) {
            openMenuPanel();
        } else if (orderBtn.equals(btn)) {
            openOrderPanel();
        } else if (restaurantBtn.equals(btn)) {
            openRestaurantPanel();
        } else if (save.equals(btn)) {
            restaurent.handleSaveRestaurant();
        } else if (load.equals(btn)) {
            restaurent = restaurent.handleReadRestaurant(restaurent);
        } else if (quit.equals(btn)) {
            quitPopUpPanel();
        } else if (addDish.equals(btn)) {
            addNewDish();
        } else if (deleteDish.equals(btn)) {
            deleteMenuDish();
        } else if (clearMenuBtn.equals(btn)) {
            clearMenu();
        } else if (setNameRestaurantBtn.equals(btn)) {
            setNamePopUp();
        } else if (setCuisineRestaurantBtn.equals(btn)) {
            setCuisinePopUp();
        } else if (finishOrderRestaurantBtn.equals(btn)) {
            finishOrderPopUp();
        } else if (prioritizeOrderRestaurantBtn.equals(btn)) {
            prioritizeOrderPopUp();
        } else if (removeOrderRestaurantBtn.equals(btn)) {
            removeOrderPopUp();
        } else if (modifyOrderRestaurantBtn.equals(btn)) {
            modifyOrderPopUp();
        } else if (addOrderBtn.equals(btn)) {
            addOrder();
        }
    }

    /*
     * MODIFIES: this, restaurant
     * EFFECTS: creates a pop-up to rename the restaurant
     */
    private void setNamePopUp() {
        String name = JOptionPane.showInputDialog(frame,
                "Set Restaurant Name (should be a word or phrase)", null);
        this.restaurent.setRestaurantName(name);
        restaurent.handleSaveRestaurant();
    }

    /*
     * MODIFIES: this, restaurant
     * EFFECTS: creates a pop-up to choose the restaurant's cuisine
     */
    private void setCuisinePopUp() {
        String cusine = JOptionPane.showInputDialog(frame,
                "Set Restaurant Cuisine (should be a word or phrase)", null);
        this.restaurent.setCuisine(cusine);
        restaurent.handleSaveRestaurant();
    }

    /*
     * EFFECTS: helper method to generate a pop-up to get the customer's Id
     */
    private String popUp() {
        return JOptionPane.showInputDialog(frame, "Enter Order Id (should be a number)", null);
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: order id has to exist in the order list
     * EFFECTS: creates a pop-up to choose order that is finished in the order list
     */
    private void finishOrderPopUp() {
        String id = popUp();
        OrderForRestaurant orderToFinish = this.restaurent.getSingleOrder(Integer.parseInt(id));
        this.restaurent.finishOrder(orderToFinish);
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: order id has to exist in the order list
     * EFFECTS: creates a pop-up to choose order that is to be prioritized in the order list
     */
    private void prioritizeOrderPopUp() {
        String id = popUp();
        OrderForRestaurant orderToPrioritize = this.restaurent.getSingleOrder(Integer.parseInt(id));
        this.restaurent.prioritizeOrder(orderToPrioritize);
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: order id has to exist in the order list
     * EFFECTS: creates a pop-up to choose order that is to be removed in the order list
     */
    private void removeOrderPopUp() {
        String id = popUp();
        this.restaurent.removeOrder(Integer.parseInt(id));
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: order id has to exist in the order list
     * EFFECTS: creates a pop-up to choose order that is to be modified with four options: add and remove food items
     *          and add and remove allergies
     */
    private void modifyOrderPopUp() {
        String id = popUp();
        String typeOfMod = setUpforModifyOrder();
        if (typeOfMod.equals("1")) {
            String item = JOptionPane.showInputDialog(frame, "Enter name of food item to add"
                            + "(should be a word or phrase)", null);
            MenuItem itemAdd = this.restaurent.getMenu().getFoodItem(item);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).addItemToOrder(itemAdd);
        } else if (typeOfMod.equals("2")) {
            String item = JOptionPane.showInputDialog(frame, "Enter name of food item to remove"
                            + "(should be a word or phrase)", null);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).removeItemToOrder(item);
        } else if (typeOfMod.equals("3")) {
            String allergy = JOptionPane.showInputDialog(frame, "Enter Allergy to Add"
                            + "(should be a word or phrase)", null);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).addAllergy(allergy);
        } else if (typeOfMod.equals("4")) {
            String allergy = JOptionPane.showInputDialog(frame, "Enter Allergy to Remove"
                    + "(should be a word or phrase)", null);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).removeAllergy(allergy);
        }
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    /*
     * MODIFIES: this
     * EFFECTS: helper method for modify order creating a pop-up to allow users to choose
     *          which functionality of modification to run
     */
    private String setUpforModifyOrder() {
        return JOptionPane.showInputDialog(frame,
                "Enter 1 to add food item, Enter 2 to remove food item, Enter 3 to add allergy, enter 4 to "
                        + "remove allergy",
                null);
    }


    /*
     * MODIFIES: this
     * EFFECTS: exits the current page and load in the homepage
     */
    private void quitPopUpPanel() {
        new RestaurantMateGUI();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the restaurant's gui with associated buttons and jtext area
     */
    private void openRestaurantPanel() {
        initGui();
        addTopBar();
        restaurent = restaurent.handleReadRestaurant(restaurent);

        mainJta = new JTextArea();
        mainJta.setPreferredSize(new Dimension(800, 600));
        mainJta.setEnabled(false);
        frame.getContentPane().add(mainJta);
        displayOrder();

        JPanel panel = new JPanel();

        restaurentSetUp();

        panel.add(setNameRestaurantBtn);
        panel.add(setCuisineRestaurantBtn);
        panel.add(finishOrderRestaurantBtn);
        panel.add(prioritizeOrderRestaurantBtn);
        panel.add(removeOrderRestaurantBtn);
        panel.add(modifyOrderRestaurantBtn);

        frame.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    /*
     * MODIFIES: this
     * EFFECTS: helper method for the restaurant's gui creating all the buttons att the bottom of the screen:
     *          setting the name and cuisine and, finishing, prioritizing, removing, and modifying order
     */
    private void restaurentSetUp() {
        setNameRestaurantBtn = new JButton("Set Restaurant Name");
        setNameRestaurantBtn.addActionListener(this);

        setCuisineRestaurantBtn = new JButton("Set Restaurant Cuisine");
        setCuisineRestaurantBtn.addActionListener(this);

        finishOrderRestaurantBtn = new JButton("Finish Order");
        finishOrderRestaurantBtn.addActionListener(this);

        prioritizeOrderRestaurantBtn = new JButton("Prioritize Order");
        prioritizeOrderRestaurantBtn.addActionListener(this);

        removeOrderRestaurantBtn = new JButton("Remove Order");
        removeOrderRestaurantBtn.addActionListener(this);

        modifyOrderRestaurantBtn = new JButton("Modify Order");
        modifyOrderRestaurantBtn.addActionListener(this);

    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the orders' gui with associated buttons and jtext area
     */
    private void openOrderPanel() {
        initGui();
        addTopBar();
        restaurent = restaurent.handleReadRestaurant(restaurent);

        mainJta = new JTextArea();
        mainJta.setPreferredSize(new Dimension(800, 600));
        mainJta.setEnabled(false);
        frame.getContentPane().add(mainJta);
        displayOrder();

        JPanel panel = new JPanel();

        addOrderBtn = new JButton("Add Order");
        addOrderBtn.addActionListener(this);

        panel.add(addOrderBtn);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: menu items that are added must be valid menu items, meaning instantiated items that have been added to
     *           the menu object
     * EFFECTS: creates a pop-up to create a new order for a customer
     */
    private void addOrder() {
        String name = (String)JOptionPane.showInputDialog(frame,
                "Enter customer name (should be a word or phrase)", null);
        ArrayList<MenuItem> items = addMenuItem();
        ArrayList<String> allergies = addAllergies();
        this.restaurent.addOrder(new OrderForRestaurant(name, items, allergies));
        displayOrder();
        restaurent.handleSaveRestaurant();
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: menu items inputed has to be valid menu items
     * EFFECTS: creates a pop-up to add a new order to the restaurant's list of orders
     */
    private ArrayList<MenuItem> addMenuItem() {
        ArrayList<MenuItem> items = new ArrayList<>();
        Boolean check = true;
        while (check) {
            String item = JOptionPane.showInputDialog(frame,
                    "Name of menu item to be added and type done when finished adding "
                            + "(should be a word or phrase)", null);
            if (item.equals("done")) {
                check = false;
            } else {
                MenuItem itemToAdd = this.restaurent.getMenu().getFoodItem(item);
                items.add(itemToAdd);
            }
        }
        return items;
    }

    /*
     * MODIFIES: this, orders
     * EFFECTS: helper method to add allergies to a given order
     */
    private ArrayList<String> addAllergies() {
        ArrayList<String> allergies = new ArrayList<>();
        Boolean check = true;
        while (check) {
            String item = JOptionPane.showInputDialog(frame,
                    "Name of allergy to be added and type done when finished adding"
                            + "(should be a word or phrase)", null);
            if (item.equals("done")) {
                check = false;
            } else {
                allergies.add(item);
            }
        }
        return allergies;
    }

    /*
     * MODIFIES: this
     * EFFECTS: display the current list of restaurant's orders
     */
    private void displayOrder() {
        String order = String.join("\n", this.restaurent.getCurrentOrders());
        mainJta.setText(order);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes the menu gui with all its individual components: top nav bar, text area for menu items, and
     *          clear menu button
     */
    private void openMenuPanel() {
        initGui();
        addTopBar();

        mainJta = new JTextArea();
        mainJta.setPreferredSize(new Dimension(800, 600));
        mainJta.setEnabled(false);
        frame.getContentPane().add(mainJta);
        restaurent = restaurent.handleReadRestaurant(restaurent);
        displayMenu();

        clearMenuBtn = new JButton("Clear Menu - WARNING this action is irreversible");
        clearMenuBtn.addActionListener(this);
        frame.getContentPane().add(clearMenuBtn, BorderLayout.SOUTH);

    }

    /*
     * MODIFIES: this, restaurant
     * EFFECTS: clears the existing restaurant's menu
     */
    private void clearMenu() {
        this.restaurent.getMenu().clearMenu();
        restaurent.handleSaveRestaurant();
        displayMenu();
    }

    /*
     * MODIFIES: this
     * EFFECTS: inits the menu item GUI
     */
    private void openFoodPanel() {
        initGui();
        addTopBar();
        addFoodItemPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: add the add food item panel
     */
    private void addFoodItemPanel() {
        JPanel panel2 = new JPanel();

        addDish = new JButton("Add Food Item");
        addDish.addActionListener(this);
        panel2.add(addDish);

        deleteDish = new JButton("Delete Food Item");
        deleteDish.addActionListener(this);
        panel2.add(deleteDish);

        mainJta = new JTextArea();
        mainJta.setPreferredSize(new Dimension(800, 600));
        mainJta.setEnabled(false);
        mainJta.setLineWrap(true);
        mainJta.setColumns(5);
        frame.getContentPane().add(mainJta);
        restaurent = restaurent.handleReadRestaurant(restaurent);
        displayMenu();

        frame.getContentPane().add(BorderLayout.SOUTH, panel2);
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: name and allergy has to be of non-zero length
     * EFFECTS: creates a pop-up to add a new menu item to the menu
     */
    private void addNewDish() {
        String name = (String)JOptionPane.showInputDialog(frame, "Name of Dish (should be a word or phrase)",
                null);
        String stringPrice = JOptionPane.showInputDialog(frame, "Price of Dish (should be a number)",
                null);
        int price = Integer.parseInt(stringPrice);
        String allergy = (String)JOptionPane.showInputDialog(frame, "Allergy of Dish (should be a word or"
                + "phrase)", null);
        String stringTime = (String)JOptionPane.showInputDialog(frame, "Time to Make Dish (should be a number)",
                null);
        int time = Integer.parseInt(stringTime);
        this.restaurent.getMenu().addFoodItem(new MenuItem(name, price, allergy, time));
        restaurent.handleSaveRestaurant();
        displayMenu();;
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays the current list of menu items for the restaurant
     */
    private void displayMenu() {
        String menu = this.restaurent.getMenu().getAllMenuItems();
        mainJta.setText(menu);
    }

    /*
     * MODIFIES: this, restaurant
     * REQUIRES: input has to be a valid menu item
     * EFFECTS: deletes a particular menu item from the menu
     */
    private void deleteMenuDish() {
        String name = JOptionPane.showInputDialog(frame, "Enter name of item to be deleted"
                        + "(should be a word or phrase)",
                null);
        this.restaurent.getMenu().deleteFoodItemFromMenu(name);
        restaurent.handleSaveRestaurant();
        displayMenu();
    }
}
