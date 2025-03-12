package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
//Service
public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        UserDaoHibernateImpl.getinstance().dropUsersTable();
        UserDaoHibernateImpl.getinstance().createUsersTable();
         UserDaoJDBCImpl.getInstance().createUsersTable();

    }

    public void dropUsersTable() {
        UserDaoHibernateImpl.getinstance().dropUsersTable();
        UserDaoJDBCImpl.getInstance().dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println("save user from UserDaoHibernateImpl");
        UserDaoHibernateImpl.getinstance().saveUser(name, lastName, age);
        System.out.println("save user from UserDaoJDBCImpl");
     //  UserDaoJDBCImpl.getInstance().saveUser(name, lastName, age);
       UserDaoHibernateImpl.getinstance().getAllUsers();


    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl.getinstance().removeUserById(id);
        UserDaoJDBCImpl.getInstance().removeUserById(id);
    }

    public List<User> getAllUsers() {
        System.out.println("save user from UserDaoJDBCImpl");
        List<User> userListJdbc = UserDaoJDBCImpl.getInstance().getAllUsers();
        System.out.println("save user from UserDaoHibernateImpl");
        List<User> userListHibernate = UserDaoHibernateImpl.getinstance().getAllUsers();

        return  userListHibernate;
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl.getinstance().cleanUsersTable();
        UserDaoJDBCImpl.getInstance().cleanUsersTable();
    }
}
