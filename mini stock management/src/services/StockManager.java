package services;

import java.util.HashMap;
import java.util.Map;
import models.Product;
import models.Customer;
import models.Sale;

public class StockManager {
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Customer> customers = new HashMap<>();
    private final Map<Integer, Sale> sales = new HashMap<>();
    

    // Product management
    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        
        products.put(product.getId(), product);
        System.out.println("Product added: " + product);
    }

    public void updateProduct(int id, String name, double price, int quantity) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            System.out.println("Product updated: " + product);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public void removeProduct(int id) {
        Product removed = products.remove(id);
        if (removed != null) {
            System.out.println("Product removed: " + removed);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public String[][] listProducts() {
        if (products.isEmpty()) {
            return new String[][]{{"No products available", "", "", ""}};
        } else {
            String[][] data = new String[products.size()][4];
            int index = 0;
            for (Product product : products.values()) {
                data[index][0] = String.valueOf(product.getId());
                data[index][1] = product.getName();
                data[index][2] = String.valueOf(product.getPrice());
                data[index][3] = String.valueOf(product.getQuantity());
                index++;
            }
            return data;
        }
    }

    // List all product names
    public String[] listProductNames() {
        String[] productNames = new String[products.size()];
        int index = 0;
        for (Product product : products.values()) {
            productNames[index++] = product.getName();
        }
        return productNames;
    }

    // Customer management
    public void addCustomer(String name, String email, String phone) {
        Customer customer = new Customer(name, email, phone);
        
        customers.put(customer.getId(), customer);
        System.out.println("Customer added: " + customer);
    }

    public void updateCustomer(int id, String name, String email, String phone) {
        Customer customer = customers.get(id);
        if (customer != null) {
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            System.out.println("Customer updated: " + customer);
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }

    public void removeCustomer(int id) {
        Customer removed = customers.remove(id);
        if (removed != null) {
            System.out.println("Customer removed: " + removed);
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }

    public String[][] listCustomers() {
        if (customers.isEmpty()) {
            return new String[][]{{"No customers available", "", "", ""}};
        } else {
            String[][] data = new String[customers.size()][4];
            int index = 0;
            for (Customer customer : customers.values()) {
                data[index][0] = String.valueOf(customer.getId());
                data[index][1] = customer.getName();
                data[index][2] = customer.getEmail();
                data[index][3] = customer.getPhone();
                index++;
            }
            return data;
        }
    }

    // List all customer names
    public String[] listCustomerNames() {
        String[] customerNames = new String[customers.size()];
        int index = 0;
        for (Customer customer : customers.values()) {
            customerNames[index++] = customer.getName();
        }
        return customerNames;
    }

    // Sales management
    public void addSale(int productId, int customerId, int quantity) {
        Product product = products.get(productId);
        Customer customer = customers.get(customerId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient stock.");
            return;
        }

        // Calculate total price
        double totalPrice = product.getPrice() * quantity;

        // Deduct stock
        product.setQuantity(product.getQuantity() - quantity);

        // Create and store sale
        Sale sale = new Sale( productId, customerId, quantity, totalPrice);
        sales.put(sale.getId(), sale);

        System.out.println("Sale recorded: " + sale);
    }

    public String[][] listSales() {
        if (sales.isEmpty()) {
            return new String[][]{{"No sales recorded", "", "", "", ""}};
        } else {
            String[][] data = new String[sales.size()][5];
            int index = 0;
            for (Sale sale : sales.values()) {
                data[index][0] = String.valueOf(sale.getId());
                data[index][1] = String.valueOf(sale.getProductId());
                data[index][2] = String.valueOf(sale.getCustomerId());
                data[index][3] = String.valueOf(sale.getQuantity());
                data[index][4] = String.valueOf(sale.getTotalPrice());
                index++;
            }
            return data;
        }
    }

    public double calculateTotalSales() {
        return sales.values().stream().mapToDouble(Sale::getTotalPrice).sum();
    }

    // Get product ID by product name
    public int getProductIdByName(String name) {
        for (Product product : products.values()) {
            if (product.getName().equals(name)) {
                return product.getId();
            }
        }
        return -1;  // Return -1 if not found
    }

    // Get customer ID by customer name
    public int getCustomerIdByName(String name) {
        for (Customer customer : customers.values()) {
            if (customer.getName().equals(name)) {
                return customer.getId();
            }
        }
        return -1;  // Return -1 if not found
    }


    public void addFakeData() {
        // Add fake products
        addProduct("Laptop", 1500.0, 10);
        addProduct("Smartphone", 800.0, 20);
        addProduct("Headphones", 100.0, 50);
        addProduct("Smartwatch", 200.0, 15);



        // Add fake customers
        addCustomer("John Doe", "john@example.com", "123-456-7890");
        addCustomer("Jane Smith", "jane@example.com", "098-765-4321");
        addCustomer("Mike Johnson", "mike@example.com", "555-123-4567");
        addCustomer("Emily Davis", "emily@example.com", "555-987-6543");

        // Manually add specific sales
        addSale(getProductIdByName("Laptop"), getCustomerIdByName("Mike Johnson"), 1);
        addSale(getProductIdByName("Smartphone"), getCustomerIdByName("Emily Davis"), 2);
        addSale(getProductIdByName("Headphones"), getCustomerIdByName("John Doe"), 3);
    
        addSale(getProductIdByName("Keyboard"), getCustomerIdByName("John Doe"), 5);
    }

}
