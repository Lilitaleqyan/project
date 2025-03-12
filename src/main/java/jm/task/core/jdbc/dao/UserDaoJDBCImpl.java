package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final UserDaoJDBCImpl INSTANCE;

    static {

        try {
            INSTANCE = new UserDaoJDBCImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Connection connections() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "aleqyan";
        return DriverManager.getConnection(url, user, password);
    }


    private UserDaoJDBCImpl() throws SQLException {
    }

    public static UserDaoJDBCImpl getInstance() {
        return INSTANCE;
    }

    public void createUsersTable() {
        String sql = """
                 create table if not exists public.users (
                 id serial primary key,
                 firstName varchar(255) not null ,
                 lastName varchar(255) not null ,
                 age int not null
                );
                """;
        try (Statement st = connections().createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Create table for users ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        String sql = "drop table if exists public.users";

        try (
                Statement st = connections().createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Drop table ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {


        String sql = """
                insert into public.users(firstName, lastName, age) values (?, ?, ?)""";
        try (PreparedStatement pst = connections().prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setInt(3, age);

            System.out.println("add new user " + name);

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = """
                DELETE FROM public.users WHERE id = ?
                """;
        try (PreparedStatement pst = connections().prepareStatement(sql)) {
            pst.setLong(1, id);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("id deleted " + id);
            } else {
                System.out.println("id not found " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = """
                select * from  public.users
                order by id asc""";

        try (Statement st = connections().createStatement();
             ResultSet result = st.executeQuery(sql)) {

            while (result.next()) {
                long id = result.getLong("id");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                byte age = result.getByte("age");
                User user = new User(firstName, lastName, age);
                user.setId(id);
                userList.add(user);
            }
            userList.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;

    }

    public void cleanUsersTable() {
        String sql = "delete from \"users\"";
        try (Statement st = connections().createStatement()) {
            st.executeUpdate(sql);
            System.out.println("cleaned table");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

