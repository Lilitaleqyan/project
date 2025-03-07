package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    public static void utils() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "aleqyan";
        try (Connection driver = DriverManager.getConnection(url, user, password)) {
            Statement st = driver.createStatement();


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}