import java.io.Console;
import java.io.File;
import java.util.Scanner;

public class Main{

    public static void printMenu() {
        System.out.println("===================== MENU =====================");
        System.out.println("list: Display all items in your cart. ");
        System.out.println("add <items>: Add listed items to your cart. ");
        System.out.println("delete <num>: Delete the item at position num.");
        System.out.println("exit: Exit the program. ");
        System.out.println("================================================");
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
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

        // Handle Cart
        Cart cart = new Cart();
        Console console = System.console();

        System.out.println("Welcome to your shopping cart!");
        System.out.println();
        String input = "";

        while (!input.equals("exit")) {
            printMenu();
            input = console.readLine("> ").trim();

            // List
            if (input.equals("list")) {
                System.out.println(cart.toString());

            // Add items
            } else if (input.startsWith("add")) {
                String items = input.substring(4).replace(",", " ");
                Scanner s = new Scanner(items).useDelimiter(" ");
                while (s.hasNext()) {
                    String item = s.next();
                    if (cart.contains(item)) {
                        System.out.printf("You have %s in your cart\n", item);
                    } else {
                    cart = cart.add(item);
                    System.out.println(item + " added to cart");
                    }
                }

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

            // Unrecognised command
            } else {
                System.out.println("Unrecognised command. Please try again.");
            }
        }

        System.out.println("Exit successful.");
        System.out.println("Goodbye!");
        System.out.println();

    }
}