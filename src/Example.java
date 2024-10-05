
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Example {
    

    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart");
        userInput();
    }

    public static void menu() {
        
        System.out.println("===============================");
        System.out.println("List items in the cart: enter list");
        System.out.println("Add items into the cart: enter add <items>");
        System.out.println("Delete item in the cart: enter delete <item no.>");
        System.out.println("Exit program: enter quit");
        System.out.println("===============================");
    }

    public static void userInput() {
        List<String> cartItems = new ArrayList<>();

        Console console = System.console();
        String keyboardInput = "";

        while (!keyboardInput.equals("quit")) {
            menu();
            keyboardInput = console.readLine("> ").toLowerCase();

            if (keyboardInput.equals("list")) {
                if (cartItems.size() > 0) {
                    for (String itm : cartItems) {
                        System.out.println(itm);
                    }
                } else {
                    System.out.println("Your cart is empty");
                }
            } else if (keyboardInput.startsWith("add")) {
                keyboardInput = keyboardInput.replace(',', ' ');
                Scanner scan = new Scanner(keyboardInput.substring(4));
                String tempStorage = "";
                
                while (scan.hasNext()) {
                    cartItems.add(scan.next());
                }
            } else if (keyboardInput.startsWith("delete")) {
                Scanner scan = new Scanner(keyboardInput.substring(0));
                String deleteIndex = scan.next();
                int indexToDelete = Integer.parseInt(deleteIndex);

                if (cartItems.size() >= indexToDelete) {
                    if (indexToDelete < 0) {
                        System.out.println("Position does not exist.");
                    } else {
                        cartItems.remove(indexToDelete);
                    }
                } else {
                    System.out.println("Index out of bounds. Delete operation will be cancelled.");
                }
            }

        }

        System.out.println("Exiting program.");
    }
}
