package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "aleqyan";

    public void createUsersTable() {
        try {
            UserDaoJDBCImpl.getInstance().createUsersTable();
        } catch (Exception e) {
                e.printStackTrace();
         }
    }

    public void incremantId() { //TODO
        String sql = """
                ALTER SEQUENCE users_id_seq RESTART WITH 1;

                """;
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUsers() {

        String sql = """
                Insert into users (firstName, lastName, age) values (?, ?, ?)
                """;
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {
            User[] users =
                    {
                            new User("Ani", "Sargsyan", (byte) 23),
                            new User("Mushegh", "Manukyan", (byte) 32),
                            new User("Samvel", "Nikoghosyan", (byte) 27),
                            new User("Anush", "Avdalyan", (byte) 25)

                    };
            for (User u : users) {

                pst.setString(1, u.getFirstName());
                pst.setString(2, u.getLastName());
                pst.setInt(3, u.getAge());
                pst.executeUpdate();
                System.out.println("add user:" + u.getFirstName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl.getInstance().dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl.getInstance().saveUser(name, lastName, age);
        addUsers();
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl.getInstance().removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserDaoJDBCImpl.getInstance().getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl.getInstance().cleanUsersTable();
    }
}
