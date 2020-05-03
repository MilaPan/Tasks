package ua.meta.sarna;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProductDAOEx extends AbstractDAO<Integer, Product> {
        public ProductDAOEx(Connection conn, String table) {
            super(conn, table);
        }

    public void addProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter product name:");
        String name = sc.nextLine();
        System.out.println("Please enter product price:");
        double price = sc.nextDouble();

        Product p = new Product();
        p.setName(name);
        p.setPrice(price);

        this.add(p);

    }

    @Override
    public void init() {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS products");
            st.execute("CREATE TABLE products (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(30) NOT NULL," +
                    "price DOUBLE(8,2) UNSIGNED DEFAULT NULL)");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

