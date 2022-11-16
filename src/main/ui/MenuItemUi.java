//package ui;
//
//import model.MenuItem;
//import model.Restaurent;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MenuItemUi extends JFrame implements ActionListener {
//    private JFrame frame;
//    private JButton addDish;
//    private JButton deleteDish;
//    private JButton submit;
//    private JTextArea mainJta;
//    private Restaurent restaurent;
//    private Map<String, JTextField> menuItem = new HashMap<>();
//    private String itemToDelete;
//
//    public MenuItemUi(Restaurent restaurent, JFrame frame) {
//        this.restaurent = restaurent;
//        this.frame = frame;
//        openFoodPanel();
//    }
//
//    private void openFoodPanel() {
//        addFoodItemPanel();
//    }
//
//    /*
//     * REQUIRES:
//     * EFFECTS: add the add food item panel
//     */
//    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
//    private void addFoodItemPanel() {
//        JSplitPane splitPane = new JSplitPane();
//        JSplitPane splitPane1 = new JSplitPane();
//        JPanel panel3 = new JPanel();
//        JPanel panel2 = new JPanel();
//
//        addDish = new JButton("Add Food Item");
//        addDish.addActionListener(this);
//        splitPane.setRightComponent(addDish);
//
//        submit = new JButton("Submit");
//        submit.addActionListener(this);
//        splitPane.setRightComponent(submit);
//
//        JTextField name = new JTextField("enter name of dish here");
//        menuItem.put("name", name);
//        JTextField price = new JTextField("enter price of dish here");
//        menuItem.put("price", price);
//        JTextField allergy = new JTextField("enter allergy of dish here");
//        menuItem.put("allergy", allergy);
//        JTextField timeToMake = new JTextField("enter time to make dish here");
//        menuItem.put("timeToMake", timeToMake);
//        panel3.add(name, BorderLayout.CENTER);
//        panel3.add(price, BorderLayout.CENTER);
//        panel3.add(allergy, BorderLayout.CENTER);
//        panel3.add(timeToMake, BorderLayout.CENTER);
//        splitPane.setLeftComponent(panel3);
//
//        deleteDish = new JButton("Delete Food Item");
//        deleteDish.addActionListener(this);
//        splitPane1.setRightComponent(deleteDish);
//
//        JTextField id = new JTextField("enter ID of dish here");
//        itemToDelete = id.getText();
//
//        splitPane1.setLeftComponent(id);
//        panel2.add(splitPane, BorderLayout.CENTER);
//        panel2.add(splitPane1, BorderLayout.SOUTH);
//
//        mainJta = new JTextArea();
//        mainJta.setPreferredSize(new Dimension(800, 600));
//        mainJta.setEnabled(true);
//        frame.getContentPane().add(mainJta);
//
//        frame.getContentPane().add(BorderLayout.SOUTH, panel2);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JButton btn = (JButton) e.getSource();
//        if (addDish.equals(btn)) {
//            MenuItem item = createMenuItem();
//            this.restaurent.getMenu().addFoodItem(item);
//            displayMenu();
//        } else if (deleteDish.equals(btn)) {
//            deleteMenuItem();
//            displayMenu();
//        }
//    }
//
//    private void deleteMenuItem() {
//        this.restaurent.getMenu().deleteFoodItemFromMenu(itemToDelete);
//    }
//
//    private void displayMenu() {
//        String menu = this.restaurent.getMenu().getAllMenuItems();
//        mainJta.setText(menu);
//    }
//
//    private MenuItem createMenuItem() {
//        String name = menuItem.get("name").getText();
//        String stringPrice = menuItem.get("price").getText();
//        int price =  Integer.parseInt(stringPrice);
//        String allergy = menuItem.get("allergy").getText();
//        String stringTimeToMake = menuItem.get("timeToMake").getText();
//        int timeToMake =  Integer.parseInt(stringTimeToMake);
//        return new MenuItem(name, price, allergy, timeToMake);
//    }
//}
