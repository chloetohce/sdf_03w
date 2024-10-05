import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main{

    public static void printMenu() {
        System.out.println("===================== MENU =====================");
        System.out.println("users: Get a list of all existing users.");
        System.out.println("login <name>: Login to your user account.");
        System.out.println("list: Display all items in your cart. ");
        System.out.println("add <items>: Add listed items to your cart. ");
        System.out.println("delete <num>: Delete the item at position num.");
        System.out.println("save: Save existing cart to database.");
        System.out.println("exit: Exit the program. ");
        System.out.println("================================================");
        System.out.println();
    }

    public static void handleInput(String db) throws IOException {
        Cart cart = new Cart();
        CartDB cartDB = new CartDB(db);
        Console console = System.console();
        String input = "";

        while (!input.equals("exit")) {
            printMenu();
            input = console.readLine("> ").trim();

            // List
            if (input.equals("list")) {
                System.out.println(cart.toString());
            
            // Display users
            } else if (input.equals("users")) {
                System.out.println("Existing users: ");
                cartDB.getUsers();
            
            // Save current cart
            } else if (input.equals("save")) {
                cartDB.saveCart(cart);

            // Add items
            } else if (input.startsWith("add")) {
                String items = input.substring(4).replace(",", " ");
                Scanner s = new Scanner(items);
                while (s.hasNext()) {
                    String item = s.next();
                    if (cart.contains(item)) {
                        System.out.printf("You have %s in your cart\n", item);
                    } else {
                    cart = cart.add(item);
                    System.out.println(item + " added to cart");
                    }
                }
                s.close();

            // Delete item
            } else if (input.startsWith("delete")) {
                int num = Integer.parseInt(input.substring(7));
                int index = num - 1;
                if (index > cart.getLength() - 1) { // checking if index is out of bounds
                    System.out.println("Incorrect item index");
                } else {
                    String removedItem = cart.getItem(index);
                    cart = cart.remove(index);
                    System.out.println(removedItem + " removed from cart");
                }

            // Exit program
            } else if (input.equals("exit")) {
                System.out.println("Exiting cart");
            
            // Login to user account and load cart
            } else if (input.startsWith("login")) {
                String user = input.substring(5);
                cart = cartDB.loadCart(user);
                System.out.println(user + " has logged in successfully.");

            // Unrecognised command
            } else {
                System.out.println("Unrecognised command. Please try again.");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // Database
        String db;
        try {
            db = args[0];
        } catch (IndexOutOfBoundsException e) {
            db = "db";
        }
        File dir = new File(db);
        if (!dir.exists()) {
            dir.mkdir();
        }

        System.out.println("Welcome to your shopping cart!");
        System.out.println();

        handleInput(db);

        System.out.println("Exit successful.");
        System.out.println("Goodbye!");
        System.out.println();

    }
}