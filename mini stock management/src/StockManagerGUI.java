import services.StockManager;
import javax.swing.*;
import java.awt.*;

public class StockManagerGUI {

    private static StockManager manager = new StockManager();
    private static JTextArea outputArea;

    public static void main(String[] args) {
        manager.addFakeData();

        // Main Frame
        JFrame frame = new JFrame("Stock Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Action Panel
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Buttons for actions
        JButton addProductButton = createButton("Add Product");
        JButton listProductsButton = createButton("List Products");
        JButton addCustomerButton = createButton("Add Customer");
        JButton listCustomersButton = createButton("List Customers");
        JButton recordSaleButton = createButton("Record Sale");
        JButton listSalesButton = createButton("List Sales");
        JButton calculateTotalSalesButton = createButton("Calculate Total Sales");
        JButton exitButton = createButton("Exit");

        // Group related buttons
        actionPanel.add(createLabeledPanel("Product Actions", addProductButton, listProductsButton));
        actionPanel.add(createLabeledPanel("Customer Actions", addCustomerButton, listCustomersButton));
        actionPanel.add(createLabeledPanel("Sales Actions", recordSaleButton, listSalesButton, calculateTotalSalesButton));
        actionPanel.add(Box.createVerticalStrut(10));
        actionPanel.add(exitButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        frame.add(actionPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button Event Handlers
        addProductButton.addActionListener(e -> showAddProductDialog(frame));
        listProductsButton.addActionListener(e -> showListDialog("Product List", manager.listProducts(), new String[]{"ID", "Name", "Price", "Quantity"}));
        addCustomerButton.addActionListener(e -> showAddCustomerDialog(frame));
        listCustomersButton.addActionListener(e -> showListDialog("Customer List", manager.listCustomers(), new String[]{"ID", "Name", "Email", "Phone"}));
        recordSaleButton.addActionListener(e -> showRecordSaleDialog(frame));
        listSalesButton.addActionListener(e -> showListDialog("Sales List", manager.listSales(), new String[]{"Sale ID", "Product ID", "Customer ID", "Quantity", "Total Price"}));
        calculateTotalSalesButton.addActionListener(e -> outputArea.setText("Total Sales Amount: " + manager.calculateTotalSales()));
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private static JPanel createLabeledPanel(String label, JComponent... components) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(label));

        for (JComponent component : components) {
            panel.add(Box.createVerticalStrut(5));
            panel.add(component);
        }

        return panel;
    }

    private static void showAddProductDialog(JFrame parent) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        panel.add(new JLabel("Product Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        if (JOptionPane.showConfirmDialog(parent, panel, "Add Product", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                manager.addProduct(name, price, quantity);
                outputArea.setText("Product added successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showAddCustomerDialog(JFrame parent) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);

        if (JOptionPane.showConfirmDialog(parent, panel, "Add Customer", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            manager.addCustomer(name, email, phone);
            outputArea.setText("Customer added successfully.");
        }
    }

    private static void showRecordSaleDialog(JFrame parent) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JComboBox<String> productComboBox = new JComboBox<>(manager.listProductNames());
        JComboBox<String> customerComboBox = new JComboBox<>(manager.listCustomerNames());
        JTextField quantityField = new JTextField();

        panel.add(new JLabel("Select Product:"));
        panel.add(productComboBox);
        panel.add(new JLabel("Select Customer:"));
        panel.add(customerComboBox);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        if (JOptionPane.showConfirmDialog(parent, panel, "Record Sale", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                int productId = manager.getProductIdByName((String) productComboBox.getSelectedItem());
                int customerId = manager.getCustomerIdByName((String) customerComboBox.getSelectedItem());
                int quantity = Integer.parseInt(quantityField.getText());
                manager.addSale(productId, customerId, quantity);
                outputArea.setText("Sale recorded successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showListDialog(String title, String[][] data, String[] columns) {
        JTable table = new JTable(data, columns);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame dialog = new JFrame(title);
        dialog.add(scrollPane);
        dialog.setSize(600, 400);
        dialog.setVisible(true);
    }
}
