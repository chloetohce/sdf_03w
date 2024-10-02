import java.util.ArrayList;

public class Cart {
    ArrayList<String> cart;

    public Cart() {
        cart = new ArrayList<>();
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
