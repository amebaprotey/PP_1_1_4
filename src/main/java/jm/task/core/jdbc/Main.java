package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 26);
        System.out.println("User c именем " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");
        userService.saveUser("Petr", "Petrov", (byte) 27);
        System.out.println("User c именем " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");
        userService.saveUser("Boris", "Borisov", (byte) 50);
        System.out.println("User c именем " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");
        userService.saveUser("Vasiliy", "Vasin", (byte) 20);
        System.out.println("User c именем " + userService.getAllUsers().get(userService.getAllUsers().size() - 1).getName() + " добавлен в базу данных");

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}
