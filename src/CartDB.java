import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class CartDB {
    private String db;
    private String user;
    private String filePath;
    private Cart initialCart;

    public CartDB(String db) {
        this.db = db;
        this.user = "";
        this.filePath = db + File.separator + user + ".txt";
        this.initialCart = new Cart();
    }

    public Cart loadCart(String user) throws IOException {
        this.user = user;
        this.filePath = db + File.separator + user + ".txt";
        File cartDB = new File(filePath);
        if (!cartDB.exists()) {
            cartDB.createNewFile();
            initialCart = new Cart();
            return initialCart;
        }

        // Loading cart if user exists in database
        initialCart = Cart.loadFile(cartDB);
        return initialCart;
    }

    public void saveCart(Cart cart) {
        if (!isLoggedIn()) {
            System.out.println("Save unsuccessful. Please log in.");
        } else {
            try {
                FileWriter fw = new FileWriter(filePath, false);
                BufferedWriter bw = new BufferedWriter(fw);

                // Overwriting file and adding everything from cart to file.
                List<String> items = cart.getItems();
                for (String item : items) {
                    bw.append(item);
                    bw.newLine();
                }

                bw.flush();
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.err.println("Unable to access database.");
            }
            System.out.println("Cart saved successfully.");
        }
    }

    public void getUsers() {
        File dir = new File(db);
        String[] files = dir.list((f, name) -> name.endsWith(".txt"));
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            int pos = fileName.lastIndexOf(".");
            String name = fileName.substring(0, pos);
            System.out.println(i +": " + name);
        }
    }

    private boolean isLoggedIn() {
        return user != "";
    }
}
