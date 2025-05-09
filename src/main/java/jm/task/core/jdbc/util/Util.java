package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection connectedToJDBC() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "aleqyan";
        try (Connection driver = DriverManager.getConnection(url, user, password)) {
            Statement st = driver.createStatement();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static SessionFactory factory;

    public static SessionFactory connectedTohibernate() {

            if (factory == null) {
                throw new IllegalArgumentException();
            }
            return factory;
        }

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        try {
            factory = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .buildMetadata().
                    buildSessionFactory();
            return factory;

        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException();
        }
    }
}