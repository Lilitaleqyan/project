package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    private static UserDaoHibernateImpl INSTANCE;

    static {
        try {
            INSTANCE = new UserDaoHibernateImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory connectedTohibernate() {
        SessionFactory factory = Util.connectedTohibernate();
        if (factory == null) {
            throw new IllegalArgumentException();
        }
        return factory;

    }


    @Override
    public void createUsersTable() {

        try (Session session = connectedTohibernate().openSession()) {

            session.beginTransaction();
            session.createSQLQuery("""
                      create table if NOT EXISTS users(
                      id serial primary key,
                      firstName varchar(255) NOT null,
                      lastName varchar(255) NOT null,
                      age int NOT null
                      );
                    """).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException("!!!");
        }


    }

    @Override
    public void dropUsersTable() {
        try (Session session = connectedTohibernate().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table if exists users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = connectedTohibernate().openSession()) {
            session.beginTransaction();
            User[] users =
                    {
                            new User(name, lastName, age),

                    };
            for (User u : users) {

                session.createSQLQuery("""
                                insert into users(firstName,lastName,age) values(:firstName,:lastName,:age)
                                """)
                        .setParameter("firstName", u.getFirstName())
                        .setParameter("lastName", u.getLastName())
                        .setParameter("age", u.getAge())
                        .executeUpdate();
            }

            session.getTransaction().commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = connectedTohibernate().openSession()) {
            session.beginTransaction();
            int session1 = session.createSQLQuery("""
                    delete from users where id = :id
                    """).setParameter("id", id).executeUpdate();
            if (session1 > 0) {
                System.out.println("remove user by id = " + id);
                session.getTransaction().commit();
            } else {
                System.out.println("user by id not found");
                session.getTransaction().rollback();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List userList = new ArrayList<>();
        try (Session session = connectedTohibernate().openSession()) {
            session.beginTransaction();
            userList = session.createSQLQuery("""
                    select * from users
                    order by id asc
                    """).addEntity(User.class).list();

            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = connectedTohibernate().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("""
                    delete from users
                    """).addQueryHint("cleaned table");
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static UserDaoHibernateImpl getinstance() {
        return INSTANCE;
    }

}
