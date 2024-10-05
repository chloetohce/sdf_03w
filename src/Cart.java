import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;

public class Cart {
    private ArrayList<String> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    private Cart(File f) {
        this.cart = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                cart.add(line);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println(e + ": File does not exist. Please try again.");
        }
    }

    public static Cart loadFile(File f) {
        return new Cart(f);
    }

    public Cart add(String item) {
        this.cart.add(item);
        return this;
    }

    public Cart remove(int num) {
        this.cart.remove(num);
        return this;
    }

    public boolean contains(String item) {
        return this.cart.contains(item);
    }

    public int getLength() {
        return cart.size();
    }

    public String getItem(int index) {
        return cart.get(index);
    }

    public ArrayList<String> getItems() {
        return this.cart;
    }

    @Override
    public String toString() {
        int index = 1;
        String output = "";
        if (cart.isEmpty()) {
            return "Your cart is empty";
        }
        for (String e : cart) {
            output += index + ". " + e + "\n";
            index++;
        }
        return output.substring(0,output.length() - 1);
    }
}
