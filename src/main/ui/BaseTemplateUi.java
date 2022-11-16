//package ui;
//
//import model.MenuForRestaurant;
//import model.OrderForRestaurant;
//import model.Restaurent;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class BaseTemplateUi extends JFrame implements ActionListener {
//    private static final int WIDTH = 2000;
//    private static final int HEIGHT = 2000;
//    private JFrame frame;
//    private JButton save;
//    private JButton load;
//    private JButton quit;
//    private JButton foodBtn;
//    private JButton menuBtn;
//    private JButton orderBtn;
//    private JButton restaurantBtn;
//    private RestaurantMateGUI home;
//    private MenuItemUi menuItem;
//
//    private Restaurent restaurent;
////    private MenuForRestaurant menu = new MenuForRestaurant();
//
//    public BaseTemplateUi() {
//        restaurent = new Restaurent("Loaf",  "Cafe", new ArrayList<OrderForRestaurant>(),
//                new MenuForRestaurant());
//        initGui();
//        addTopBar();
//        addButtons();
//        home = new RestaurantMateGUI(this.restaurent, this.frame);
//    }
//
//    /*
//     * EFFECTS: creates the gui
//     */
//    public void initGui() {
//        frame = new JFrame();
//        frame.setTitle("Restaurant Mate");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(WIDTH, HEIGHT);
//        frame.setVisible(true);
//        frame.setResizable(true);
//
//    }
//
//    /*
//     * EFFECTS: add the menu bar at the top of the screen
//     */
//    public void addTopBar() {
//        JPanel mb = new JPanel();
//
//        save = new JButton("Save Restaurant");
//        save.addActionListener(this);
//
//        load = new JButton("Load Restaurant");
//        load.addActionListener(this);
//
//        quit = new JButton("Homepage");
//        quit.addActionListener(this);
//
//        mb.add(save);
//        mb.add(load);
//        mb.add(quit);
//        frame.getContentPane().add(BorderLayout.NORTH, mb);
//
//    }
//
//    private void addButtons() {
//        Panel panel = new Panel();
//
//        foodBtn = new JButton("Menu Items");
//        foodBtn.addActionListener(this);
//        panel.add(foodBtn);
//
//        menuBtn = new JButton("Menu");
//        menuBtn.addActionListener(this);
//        panel.add(menuBtn);
//
//        orderBtn = new JButton("New Order");
//        orderBtn.addActionListener(this);
//        panel.add(orderBtn);
//
//        restaurantBtn = new JButton("Restaurant");
//        restaurantBtn.addActionListener(this);
//        panel.add(restaurantBtn);
//
//        frame.getContentPane().add(BorderLayout.SOUTH, panel);
//    }
//
//
////    public void quitPopUpPanel() {
////        switchWindow("Homepage");
////    }
//
//    //To-DO
//    public void loadPopUpPanel() {
//    }
//
//    //TO-DO
//    public void savePopUpPanel() {
//    }
//
//    public void switchWindow(String destinationWindow) {
//        if (destinationWindow == "Homepage") {
//            home.setVisible(true);
//            menuItem.setVisible(false);
//        } else if (destinationWindow == "menuItem") {
//            home.setVisible(false);
//            menuItem.setVisible(true);
//        }
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JButton btn = (JButton) e.getSource();
//        if (save.equals(btn)) {
//            savePopUpPanel();
//        } else if (load.equals(btn)) {
//            loadPopUpPanel();
//        } else if (quit.equals(btn)) {
//            switchWindow("Homepage");
//        } else if (foodBtn.equals(btn)) {
//            menuItem = new MenuItemUi(this.restaurent, this.frame);
//            switchWindow("menuItem");
//        }
//    }
//}
