package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
   //   реализуйте алгоритм здесь

        Util.utils();
         UserServiceImpl userService = new UserServiceImpl();
         userService.createUsersTable();
         userService.addUsers();
         userService.getAllUsers();
         userService.saveUser("njk", "njb", (byte) 12);
         userService.getAllUsers();

    }
}
