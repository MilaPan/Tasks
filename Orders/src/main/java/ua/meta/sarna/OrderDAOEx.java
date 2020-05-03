package ua.meta.sarna;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

    public class OrderDAOEx extends AbstractDAO<Integer, Order> {
        public OrderDAOEx(Connection conn, String table) {
            super(conn, table);
        }

        public void addOrder() throws SQLException {
            ClientDAOEx clientDao = new ClientDAOEx(conn, "clients");
            List<Client> clients = clientDao.getAll(Client.class);
            for (Client c : clients) {
                System.out.println(c.toString());
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the client id:");
            int clientId = sc.nextInt();
            System.out.println("Please enter product id:");
            int productId = sc.nextInt();
            System.out.println("Please enter order quantity:");
            int quantity = sc.nextInt();
            Order o = new Order();
            o.setClientId(clientId);
            o.setProductId(productId);
            o.setQuantity(quantity);

            this.add(o);
        }
        @Override
        public void init() {
            try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS orders");
                    st.execute("CREATE TABLE orders (" +
                            "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                            "clientId INT NOT NULL," +
                            "productId INT NOT NULL," +
                            "quantity INT NOT NULL)");

            }catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            }
        }


