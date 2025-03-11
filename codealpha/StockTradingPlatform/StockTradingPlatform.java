import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StockTradingPlatform {

    private Map<String, Double> stockPrices;
    private Map<Integer, User> users;
    private List<Transaction> transactions;

    public StockTradingPlatform() {
        stockPrices = new HashMap<>();
        // Initialize with some sample stock prices (replace with real API data)
        stockPrices.put("AAPL", 150.25);
        stockPrices.put("GOOG", 2500.75);
        stockPrices.put("MSFT", 300.50);

        users = new HashMap<>();
        // Initialize with some sample users
        users.put(1, new User(1, "Bob", 10000));
        users.put(2, new User(2, "Alice", 15000));

        transactions = new ArrayList<>();
    }

    // Display available stocks
    public void displayAvailableStocks() {
        System.out.println("Available Stocks:");
        for (Map.Entry<String, Double> entry : stockPrices.entrySet()) {
            System.out.println(entry.getKey() + " : $" + entry.getValue());
        }
        System.out.println();
    }

    // Simulate getting stock price (replace with API call)
    public double getStockPrice(String symbol) {
        // In real implementation, fetch from external API
        Random rand = new Random();
        double price = stockPrices.getOrDefault(symbol, 0.0); // Get current price
        double volatility = price * 0.01; // Simulate 1% volatility
        return price + (rand.nextDouble() * 2 - 1) * volatility;
    }

    public void placeBuyOrder(int userId, String symbol, int quantity, double price) {
        User user = users.get(userId);
        if (user != null) {
            double totalCost = quantity * price;
            if (user.getBalance() >= totalCost) {
                // In real implementation, execute order on exchange
                System.out.println("Buy order placed: " + symbol + ", " + quantity + " shares at $" + price);
                user.updateBalance(-totalCost); // Deduct cost
                transactions.add(new Transaction(userId, symbol, quantity, price, "BUY")); // Record transaction
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void placeSellOrder(int userId, String symbol, int quantity, double price) {
        User user = users.get(userId);
        if (user != null) {
            // In real implementation, check if user has enough shares to sell
            // ...

            // For simplicity, assume user has enough shares
            double totalValue = quantity * price;
            user.updateBalance(totalValue); // Add to balance
            transactions.add(new Transaction(userId, symbol, quantity, price, "SELL")); // Record transaction
            System.out.println("Sell order placed: " + symbol + ", " + quantity + " shares at $" + price);
        } else {
            System.out.println("User not found.");
        }
    }

    public static void main(String args [] ) {
        StockTradingPlatform platform = new StockTradingPlatform();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStock Trading Platform");
            System.out.println("1. Display Available Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    platform.displayAvailableStocks();
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    int userIdBuy = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter stock symbol: ");
                    String symbolBuy = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantityBuy = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter price: ");
                    double priceBuy = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    platform.placeBuyOrder(userIdBuy, symbolBuy, quantityBuy, priceBuy);
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    int userIdSell = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter stock symbol: ");
                    String symbolSell = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantitySell = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter price: ");
                    double priceSell = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    platform.placeSellOrder(userIdSell, symbolSell, quantitySell, priceSell);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class User {
    private int id;
    private String name;
    private double balance;
    // Add portfolio to store holdings

    // Constructor, getters, setters
    public User(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }
    // Add methods to manage portfolio
}

class Transaction {
    private int userId;
    private String symbol;
    private int quantity;
    private double price;
    private String type;

    public Transaction(int userId, String symbol, int quantity, double price, String type) {
        this.userId = userId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }
}