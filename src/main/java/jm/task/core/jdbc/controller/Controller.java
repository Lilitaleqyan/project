package jm.task.core.jdbc.controller;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

//controller
public class Controller implements UserService {
    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void createUsersTable() {
        userService.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        userService.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userService.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userService.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userService.cleanUsersTable();
    }
}
