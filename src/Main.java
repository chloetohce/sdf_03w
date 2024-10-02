import java.io.Console;

public class Main{
    public static void main(String[] args) {
        Cart cart = new Cart();
        Console console = System.console();

        System.out.println("Welcome to your shopping cart");
        while (true) {
            String input = console.readLine("> ");
            if (input.equals("list")) {
                System.out.println(cart.toString());

            } else if (input.startsWith("add")) {
                String item = input.substring(4);

                // Check if item already exists in cart
                if (cart.contains(item)) {
                    System.out.printf("You have %s in your cart\n", item);
                } else {
                cart = cart.add(item);
                System.out.println(item + " added to cart");
                }

            } else if (input.startsWith("delete")) {
                Integer num = Integer.parseInt(input.substring(7));
                Integer index = num - 1;

                // check if num is out of index
                if (num > cart.getLength()) {
                    System.out.println("Incorrect item index");
                } else {
                    String removedItem = cart.getItem(index);
                    cart = cart.remove(index);
                    System.out.println(removedItem + " removed from cart");
                }
            
            } else if (input.equals("done")) {
                System.out.println("exiting cart");
                break;
            } else {
                System.out.println("unrecognised command");
            }
        }
    }
}