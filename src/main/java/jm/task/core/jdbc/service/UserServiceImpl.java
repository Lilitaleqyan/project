package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "aleqyan";

    public void createUsersTable() {
        UserDaoHibernateImpl.getinstance().createUsersTable();
        try {

            UserDaoJDBCImpl.getInstance().createUsersTable();
        } catch (Exception e) {
                e.printStackTrace();
         }
    }

//    public void incremantId() { //TODO
//        String sql = """
//                ALTER SEQUENCE users_id_seq RESTART WITH 1;
//
//                """;
//        try (Connection con = DriverManager.getConnection(url, user, password);
//             Statement st = con.createStatement()) {
//            st.executeUpdate(sql);
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void dropUsersTable() {
        UserDaoHibernateImpl.getinstance().dropUsersTable();
        UserDaoJDBCImpl.getInstance().dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl.getinstance().saveUser(name, lastName, age);
        UserDaoJDBCImpl.getInstance().saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl.getinstance().removeUserById(id);
        UserDaoJDBCImpl.getInstance().removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> userListJdbc = UserDaoJDBCImpl.getInstance().getAllUsers();
        List<User> userListHibernate = UserDaoHibernateImpl.getinstance().getAllUsers();
        return  userListJdbc;
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl.getinstance().cleanUsersTable();
        UserDaoJDBCImpl.getInstance().cleanUsersTable();
    }
}
