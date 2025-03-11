package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {

    public static void main(String[] args) {
        User [] users = {
                new User("Jivan", "Gasparyam", (byte) 23),
                new User("Arthur", "Mescyan", (byte) 32),
                new User("Arkadi", "Ter-Tadevosyam", (byte) 27),
                new User("Vazgen", "Sargsyan", (byte) 25)
        };
        UserServiceImpl userService = new UserServiceImpl();

        for (User u: users) {
            userService.saveUser(u.getFirstName(), u.getLastName(), u.getAge());
        }
   //   реализуйте алгоритм здесь


       userService.createUsersTable();
       userService.getAllUsers();
       userService.removeUserById(340);
       userService.getAllUsers();
       userService.cleanUsersTable();

    }
}
