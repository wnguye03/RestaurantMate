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
// * Represents application's main window frame.
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
     * EFFECTS: RestaurantMateUI empty constructor
     */
    public RestaurantMateGUI() {
        restaurent = new Restaurent("Loaf",  "Cafe", new ArrayList<OrderForRestaurant>(), menu);
//        this.restaurent = restaurent;
//        this.frame = frame;
        initGui();
        initHome();
    }

    /*
     * EFFECTS: creates the gui
     */
    public void initGui() {
        frame = new JFrame();
        frame.setTitle("Restaurant Mate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(true);

    }

    public void initHome() {
        addButtons();
        addTopBar();
        addCenterImage();
    }

    /*
     * EFFECTS: add the menu bar at the top of the screen
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
     * EFFECTS: add the buttons at the bottom of the screen
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
     * EFFECTS: add load in image
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

    private void setNamePopUp() {
        String name = JOptionPane.showInputDialog(frame,
                "Set Restaurant Name", null);
        this.restaurent.setRestaurantName(name);
        restaurent.handleSaveRestaurant();
    }

    private void setCuisinePopUp() {
        String cusine = JOptionPane.showInputDialog(frame,
                "Set Restaurant Cuisine", null);
        this.restaurent.setCuisine(cusine);
        restaurent.handleSaveRestaurant();
    }

    private String popUp() {
        return JOptionPane.showInputDialog(frame, "Enter Order Id", null);
    }

    private void finishOrderPopUp() {
        String id = popUp();
        OrderForRestaurant orderToFinish = this.restaurent.getSingleOrder(Integer.parseInt(id));
        this.restaurent.finishOrder(orderToFinish);
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    private void prioritizeOrderPopUp() {
        String id = popUp();
        OrderForRestaurant orderToPrioritize = this.restaurent.getSingleOrder(Integer.parseInt(id));
        this.restaurent.prioritizeOrder(orderToPrioritize);
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    private void removeOrderPopUp() {
        String id = popUp();
        this.restaurent.removeOrder(Integer.parseInt(id));
        restaurent.handleSaveRestaurant();
        displayOrder();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void modifyOrderPopUp() {
        String id = popUp();
        String typeOfMod = JOptionPane.showInputDialog(frame,
                "Enter 1 to add food item, Enter 2 to remove food item, Enter 3 to add allergy, enter 4 to "
                        + "remove allergy",
                null);
        if (typeOfMod.equals("1")) {
            String item = JOptionPane.showInputDialog(frame, "Enter name of food item to add",
                    null);
            MenuItem itemAdd = this.restaurent.getMenu().getFoodItem(item);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).addItemToOrder(itemAdd);
        } else if (typeOfMod.equals("2")) {
            String item = JOptionPane.showInputDialog(frame, "Enter name of food item to remove",
                    null);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).removeItemToOrder(item);
        } else if (typeOfMod.equals("3")) {
            String allergy = JOptionPane.showInputDialog(frame, "Enter Allergy to Add",
                    null);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).addAllergy(allergy);
        } else if (typeOfMod.equals("4")) {
            String allergy = JOptionPane.showInputDialog(frame, "Enter Allergy to Remove",
                    null);
            this.restaurent.getSingleOrder(Integer.parseInt(id)).removeAllergy(allergy);
        } else {
            modifyOrderPopUp();
        }
        restaurent.handleSaveRestaurant();
        displayOrder();
    }



    private void quitPopUpPanel() {
        new RestaurantMateGUI();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

        panel.add(setNameRestaurantBtn);
        panel.add(setCuisineRestaurantBtn);
        panel.add(finishOrderRestaurantBtn);
        panel.add(prioritizeOrderRestaurantBtn);
        panel.add(removeOrderRestaurantBtn);
        panel.add(modifyOrderRestaurantBtn);



        frame.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addOrder() {
        String name = (String)JOptionPane.showInputDialog(frame, "Enter customer name", null);
        ArrayList<MenuItem> items = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        Boolean check = true;
        while (check) {
            String item = JOptionPane.showInputDialog(frame,
                    "Name of menu item to be added and type done when finished adding", null);
            if (item.equals("done")) {
                check = false;
            } else {
                MenuItem itemToAdd = this.restaurent.getMenu().getFoodItem(item);
                items.add(itemToAdd);
            }
        }

        Boolean check2 = true;
        while (check2) {
            String item = JOptionPane.showInputDialog(frame,
                    "Name of allergy to be added and type done when finished adding", null);
            if (item.equals("done")) {
                check2 = false;
            } else {
                allergies.add(item);
            }

            this.restaurent.addOrder(new OrderForRestaurant(name, items, allergies));
        }
        displayOrder();
        restaurent.handleSaveRestaurant();
    }

    private void displayOrder() {
        String order = String.join("\n", this.restaurent.getCurrentOrders());
        mainJta.setText(order);
    }


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

    private void clearMenu() {
        this.restaurent.getMenu().clearMenu();
        restaurent.handleSaveRestaurant();
        displayMenu();
    }


    private void openFoodPanel() {
        initGui();
        addTopBar();
        addFoodItemPanel();
    }

    /*
     * REQUIRES:
     * EFFECTS: add the add food item panel
     */
    private void addFoodItemPanel() {
//        JSplitPane splitPane = new JSplitPane();
//        JSplitPane splitPane1 = new JSplitPane();
//        JPanel panel3 = new JPanel();
        JPanel panel2 = new JPanel();

        addDish = new JButton("Add Food Item");
        addDish.addActionListener(this);
        panel2.add(addDish);

//
//        JTextField name = new JTextField("enter name of dish here");
//        JTextField price = new JTextField("enter price of dish here");
//        JTextField allergy = new JTextField("enter allergy of dish here");
//        JTextField timeToMake = new JTextField("enter time to make dish here");
//        panel3.add(name, BorderLayout.CENTER);
//        panel3.add(price, BorderLayout.CENTER);
//        panel3.add(allergy, BorderLayout.CENTER);
//        panel3.add(timeToMake, BorderLayout.CENTER);
//        splitPane.setLeftComponent(panel3);
//
        deleteDish = new JButton("Delete Food Item");
        deleteDish.addActionListener(this);
        panel2.add(deleteDish);
//
//        JTextField id = new JTextField("enter ID of dish here");
//        splitPane1.setLeftComponent(id);
//        panel2.add(splitPane, BorderLayout.CENTER);
//        panel2.add(splitPane1, BorderLayout.SOUTH);

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

    private void addNewDish() {
        String name = (String)JOptionPane.showInputDialog(frame, "Name of Dish", null);
        String stringPrice = JOptionPane.showInputDialog(frame, "Price of Dish", null);
        int price = Integer.parseInt(stringPrice);
        String allergy = (String)JOptionPane.showInputDialog(frame, "Allergy of Dish", null);
        String stringTime = (String)JOptionPane.showInputDialog(frame, "Time to Make Dish",
                null);
        int time = Integer.parseInt(stringTime);
        this.restaurent.getMenu().addFoodItem(new MenuItem(name, price, allergy, time));
        restaurent.handleSaveRestaurant();
        displayMenu();;

    }

    private void displayMenu() {
        String menu = this.restaurent.getMenu().getAllMenuItems();
        mainJta.setText(menu);
    }

    private void deleteMenuDish() {
        String name = JOptionPane.showInputDialog(frame, "Enter name of item to be deleted",
                null);
        this.restaurent.getMenu().deleteFoodItemFromMenu(name);
        restaurent.handleSaveRestaurant();
        displayMenu();
    }


}
