package ua.meta.sarna;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/flat8?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";*/

    public static void main(String[] args) throws SQLException {
        DbCon dbc = new DbCon();
        Factory factory = new Factory(
                dbc.getDbUrl(), dbc.getDbUser(), dbc.getDbPassword()
        );
        Connection conn = factory.getConnection();
        FlatDAO dao = new FlatDAOrealis(conn);
        Scanner sc = new Scanner(System.in);

        dao.initDB(10);
        while(true){
            System.out.println("If you want to see all flat: Enter all."+
                    "\n"+"If you want to select option: Enter option");
            String enter = sc.nextLine();
            if(enter.equals("all")) {
                dao.getAll();
                System.out.println(dao.toString());
                break;
            }if(enter.equals("option")){ start(dao); break;}
            else{System.out.println("try again");
            }
        }
    }

    public static void start(FlatDAO dao) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Select option:");
            System.out.println("For example : district, area, roomCount, price");
            String option = sc.nextLine();
            if(option.isEmpty()){ System.out.println(dao.getAll()); break;}
            if (option.equals("district")) {
                List<String> districts = dao.getDistricts();
                System.out.println("what district are you interested in? " + districts.toString());
                String value = sc.nextLine();
                dao.getAll(option, value);
                System.out.println(dao.toString());
                return;
            }
            if (option.equals("area")) {
                System.out.println("Enter the minimum area of the apartment:");
                String value = sc.nextLine();
                dao.getAll(option,value);
                System.out.println(dao.toString());
                return;
            }
            if (option.equals("roomCount")) {
                System.out.println("Enter the number of rooms:");
                String value = sc.nextLine();
                dao.getAll(option, value);
                System.out.println(dao.toString());
                return;
            }
            if (option.equals("price")) {
                System.out.println("Enter the maximum price of the apartment:");
                String value = sc.nextLine();
                dao.getAll(option, value);
                System.out.println(dao.toString());
                return;
            } else {
                System.out.println("Wrong option,try again");
            }
        }
    }



}

