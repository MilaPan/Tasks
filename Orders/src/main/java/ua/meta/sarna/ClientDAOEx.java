package ua.meta.sarna;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClientDAOEx extends AbstractDAO<Integer, Client> {
    public ClientDAOEx(Connection conn, String table) { super(conn, table);  }

    public void addClient() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the client's name:");
        String name = sc.nextLine();
        System.out.println("Please enter the client's phone number:");
        String phone = sc.nextLine();
        try (Statement st = conn.createStatement()) {
            st.execute("INSERT INTO clients (name, phone) VALUES (\"" + name + "\", \"" + phone + "\")");
        }
        Client c = new Client();
        c.setName(name);
        c.setPhone(phone);

        this.add(c);
    }
    @Override
    public void init() {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS clients");
            st.execute("CREATE TABLE clients (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(30) DEFAULT NULL," +
                    "phone VARCHAR(10) NOT NULL," +
                    "address VARCHAR(20) NOT NULL)");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    }

