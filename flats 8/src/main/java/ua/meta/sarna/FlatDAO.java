package ua.meta.sarna;

import java.sql.SQLException;
import java.util.List;

public interface FlatDAO {
    List<Flat> getAll();
    List <Flat> getAll(String option , String value);
    String toString();
    void initDB(int n) throws SQLException;
    List<String> getDistricts();
}

