package ua.meta.sarna;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
public class Main {
    static final String dbConection = "jdbc:mysql://localhost:3306/orders?serverTimezone=Europe/Kiev";
    static final String dbLogin = "root";
    static final String dbPassword = "password";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        ConnectionFactory factory = new ConnectionFactory(dbConection, dbLogin, dbPassword);
        Connection conn = factory.getConnection();
        try {
            //initDB(conn);
            ClientDAOEx clientDao = new ClientDAOEx(conn, "clients");
            ProductDAOEx productDao = new ProductDAOEx(conn, "products");
            OrderDAOEx orderDao = new OrderDAOEx(conn, "orders");

            clientDao.init();
            productDao.init();
            orderDao.init();

            Client client1 = new Client("Alex", "0553189492", "Kiev");
            Client client2 = new Client("Julia", "0889183345", "Odessa");
            Client client3 = new Client("Vova", "0889364345", "Lviv");
            clientDao.add(client1);
            clientDao.add(client2);
            clientDao.add(client3);

            List<Client> clientsLst = clientDao.getAll(Client.class);
            for (Client c : clientsLst)
                System.out.println(c);

            Product product1 = new Product("comp", 30000.00);
            Product product2 = new Product("mouse", 819.00);
            Product product3 = new Product("keyboard", 2100.00);
            productDao.add(product1);
            productDao.add(product2);
            productDao.add(product3);

            List<Product> productList = productDao.getAll(Product.class);
            for (Product p : productList)
                System.out.println(p);

            Order order1 = new Order(client1.getId(), product1.getId(), 1);
            orderDao.add(order1);
            Order order2 = new Order(client2.getId(), product2.getId(), 2);
            orderDao.add(order2);
            Order order3 = new Order(client3.getId(), product3.getId(), 1);
            orderDao.add(order3);

            //productDao.addProduct();

            List<Order> list = orderDao.getAll(Order.class);
            for (Order odr : list)
                System.out.println(odr);

        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}







