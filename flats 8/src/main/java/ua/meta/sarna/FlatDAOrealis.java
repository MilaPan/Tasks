package ua.meta.sarna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FlatDAOrealis implements  FlatDAO{
    private final Connection conn;
    private List<Flat> res = new ArrayList();

    public FlatDAOrealis(Connection conn) { this.conn = conn;}

    @Override
    public String toString() {
        String s = "";
        for(Flat f : res){
            s+= "\n"+f.toString();
        }
        return "Flat:" + s;

    }

    public List<Flat> getAll() {
        //List<Flat> res = new ArrayList();
        try{
            try(Statement st = conn.createStatement()){
                try(ResultSet rs = st.executeQuery("SELECT * FROM flats")){
                    while(rs.next()){
                        Flat flat = new Flat();
                        flat.setFlat(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setAddress(rs.getString(3));
                        flat.setArea(rs.getInt(4));
                        flat.setRoomCount(rs.getInt(5));
                        flat.setPrice(rs.getInt(6));
                        res.add(flat);
                    }
                }
            }
            return res;
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }


    public List<Flat> getAll(String option, String value) {
        //List<Flat> res = new ArrayList();
        String x = "";
        try{
            try(Statement st = conn.createStatement()){
                if(option.equals("district")|option.equals("roomCount")) {
                    x = "=";
                }if(option.equals("area")){
                    x = ">";
                }if(option.equals("price")){
                    x = "<";
                }
                try(ResultSet rs = st.executeQuery("SELECT * FROM flats WHERE "+option+x+"'"+value+"'")){
                    while(rs.next()){
                        Flat flat = new Flat();
                        flat.setFlat(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setAddress(rs.getString(3));
                        flat.setArea(rs.getInt(4));
                        flat.setRoomCount(rs.getInt(5));
                        flat.setPrice(rs.getInt(6));
                        res.add(flat);
                    }
                }
            }if(res.isEmpty()){
                System.out.println("There are no apartments with such options");
                return  res;
            }
            return res;
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public List<String> getDistricts() {
        List<String> res = new ArrayList<>();
        try{
            try(Statement st = conn.createStatement()){
                try(ResultSet rs = st.executeQuery("select district from flats group by district")){
                    while(rs.next()){
                        String d = rs.getString(1);
                        res.add(d);
                    }
                }
            }
            return res;
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public void initDB(int n) throws SQLException {
        Random rng = new Random();
        conn.setAutoCommit(false);
        try {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS flats");
                    st.execute("CREATE TABLE flats (" +
                            "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                            "district VARCHAR(500) DEFAULT NULL," +
                            "address VARCHAR(500) NOT NULL," +
                            "area DOUBLE(5,1) UNSIGNED NOT NULL," +
                            "roomCount TINYINT UNSIGNED NOT NULL," +
                            "price DOUBLE(8,2) UNSIGNED DEFAULT NULL)");
                }
                try (PreparedStatement ps = conn.prepareStatement
                        ("INSERT INTO flats (district, address, area, roomCount, price) VALUES (?, ?, ?, ?, ?)")) {
                    for (int i = 0; i < n; i++) {
                        String district = "District";
                        for (int j = 0; j < rng.nextInt(3); j++) district += "district";
                        String address = district + " district, La";
                        for (int j = 0; j < rng.nextInt(3); j++) address += "la";
                        address += " street " + (rng.nextInt(30) + 1) + ", apartment " + (rng.nextInt(200) + 1);
                        int roomCount = rng.nextInt(4) + 1;
                        double area = roomCount * (rng.nextDouble() + 1) * 15;
                        double price = area * (rng.nextDouble() + 1) * 1000;
                        ps.setString(1, district);
                        ps.setString(2, address);
                        ps.setDouble(3, area);
                        ps.setInt(4, roomCount);
                        ps.setDouble(5, price);
                        ps.executeUpdate();
                    }
                    conn.commit();
                }
            } finally {
                conn.rollback();
            }
        } finally {
            conn.setAutoCommit(true);
        }
    }

}

