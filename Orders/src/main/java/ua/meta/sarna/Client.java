package ua.meta.sarna;
import java.util.Random;

public class Client {
    @Id
    private int id;
    private String name;
    private String phone;
    private String address;

    public Client() {
    }

    public Client(String name, String phone, String address) {
        Random r = new Random();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id = r.nextInt(1000000);
    }

    public int getId() { return id;  }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone;  }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
