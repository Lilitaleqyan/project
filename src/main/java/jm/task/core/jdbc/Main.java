package jm.task.core.jdbc;

import jm.task.core.jdbc.controller.Controller;
import jm.task.core.jdbc.model.User;


public class Main {

    public static void main(String[] args) {
        //   реализуйте алгоритм здесь
        Controller controller = new Controller();

        User[] users = {
                new User("Jivan", "Gasparyam", (byte) 23),
                new User("Arthur", "Mescyan", (byte) 32),
                new User("Arkadi", "Ter-Tadevosyam", (byte) 27),
                new User("Vazgen", "Sargsyan", (byte) 25)
        };

        controller.createUsersTable();
        for (User u : users) {
            controller.saveUser(u.getFirstName(), u.getLastName(), u.getAge());
        }
        controller.getAllUsers();
        controller.removeUserById(2);
        controller.getAllUsers();


    }
}
