//package ui;

import model.MenuForRestaurant;
import model.OrderForRestaurant;
import model.Restaurent;

//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//
//import javax.swing.*;
//
///**
// * Represents application's main window frame.
// */
//public class RestaurantMateUI extends JFrame implements ActionListener {
//    private static final int WIDTH = 2000;
//    private static final int HEIGHT = 2000;
//
//    private JFrame frame;
//    private JDesktopPane desktop;
//    private JTextArea mainAreaJta;
//
//    private JButton foodBtn;
//    private JButton addFoodBtn;
//    private JButton removeFoodBtn;
//
//    private JButton menuBtn;
//    private JButton clearMenuBtn;
//    private JButton printMenuBtn;
//
//
//    private JButton orderBtn;
//    private JButton addOrderBtn;
//
//    private JButton restaurantBtn;
//    private JButton setNameRestaurantBtn;
//    private JButton setCuisineRestaurantBtn;
//    private JButton finishOrderRestaurantBtn;
//    private JButton prioritizeOrderRestaurantBtn;
//    private JButton printQueueRestaurantBtn;
//    private JButton removeOrderRestaurantBtn;
//
//    private JButton modifyOrderRestaurant;
//    private JButton addItemOrderRestaurant;
//    private JButton removeItemOrderRestaurant;
//    private JButton addAllergyOrderRestaurant;
//    private JButton removeAllergyOrderRestaurant;
//
//
//    private JButton saveBtn;
//    private JButton loadBtn;
//
//    private JButton escBtn;
//    private JButton quitBtn;
//
//    private Restaurent restaurent;
//    private MenuForRestaurant menu = new MenuForRestaurant();
//
//    private static final String IMAGE_PATH = "./data/loadScreen.jpg";
//    private JDialog dialog;
//    private boolean confirmAction;
//
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    /*
//     * EFFECTS: RestaurantMateUI empty constructor
//     */
//    public RestaurantMateUI() {
//        restaurent = new Restaurent("Loaf",  "Cafe", new ArrayList<OrderForRestaurant>(),
//                menu);
//
//    }
//
//    /*
//     * EFFECTS: init the Gui for restaurant mate
//     */
//    public void initGUI() {
//        this.getContentPane().setSize(WIDTH, HEIGHT);
//        this.setResizable(true);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainAreaJta = new JTextArea();
//        mainAreaJta.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        mainAreaJta.setEnabled(false);
//        this.getContentPane().add(mainAreaJta);
//        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
////        addTextAreas();
//        addButtons();
////        addUpdatePanel();
////        addDeletePanel();
////        initImageDialog();
//        this.pack();
//        this.setVisible(true);
//        setContentPane(desktop);
//        setTitle("Restaurant Mate");
//        setSize(WIDTH, HEIGHT);
//    }
//
//    /*
//     * REQUIRES:
//     * EFFECTS: add the buttons
//     */
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
//        saveBtn = new JButton("Save");
//        saveBtn.addActionListener(this);
//        panel.add(saveBtn);
//
//        loadBtn = new JButton("Load");
//        loadBtn.addActionListener(this);
//        panel.add(loadBtn);
//
//        quitBtn = new JButton("Quit");
//        quitBtn.addActionListener(this);
//        panel.add(quitBtn);
//        this.getContentPane().add(panel);
//    }
//
//


//
//
//    private static final String FILE_DESCRIPTOR = "...file";
//    private static final String SCREEN_DESCRIPTOR = "...screen";
//
//
//
////    private JComboBox<String> printCombo;
////    private JDesktopPane desktop;
////    private JInternalFrame controlPanel;
//
//    /**
//     * Constructor sets up button panel, key pad and visual alarm status window.
//     */
//    public RestaurantMateUI() {
//        restaurent = new Restaurent("Loaf", "Cafe", new ArrayList<OrderForRestaurant>(), menu);
////        ac.addAlarmObserver(new AlarmSiren());
//
//        desktop = new JDesktopPane();
////        desktop.addMouseListener(new DesktopFocusAction());
//        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
//        controlPanel.setLayout(new BorderLayout());
//
//        setContentPane(desktop);
//        setTitle("Restaurant Mate");
//        setSize(WIDTH, HEIGHT);
//
////        addButtonPanel();
////        addMenu();
////        addKeyPad();
////        addAlarmDisplayPanel();
////
////        Remote r = new Remote(ac);
////        addRemote(r);
//
//        controlPanel.pack();
//        controlPanel.setVisible(true);
//        desktop.add(controlPanel);
//
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        centreOnScreen();
//        setVisible(true);
//    }
//
//    /**
//     * Helper to centre main application window on desktop
//     */
//    private void centreOnScreen() {
//        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
//        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
//        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
//    }
//
//}
